package edu.escuelaing.arsw.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import edu.escuelaing.arsw.model.Chat;
import edu.escuelaing.arsw.model.UserStateChat;

@Component
public class WebSocketEvent {
	private static final Logger logger = LoggerFactory.getLogger(WebSocketEvent.class);
	
	@Autowired
	private SimpMessageSendingOperations message;
	
	@EventListener
	public void handleWebSocketConnect(SessionConnectedEvent event) {
		
		logger.info("New web socket connection");
	}
	
	@EventListener 
	public void handleWebSocketDisconnect(SessionDisconnectEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		 String username = (String) headerAccessor.getSessionAttributes().get("username");
	        if(username != null) {
	            logger.info("User Disconnected : " + username);

	            Chat chatMessage = new Chat();
	            chatMessage.setType(UserStateChat.LEAVE);
	            chatMessage.setUser(username);

	            message.convertAndSend("/store/public", chatMessage);
	        }
		
	}
}
