package edu.escuelaing.arsw.controllers;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import edu.escuelaing.arsw.model.Chat;

@Controller
public class ChatController {
	@MessageMapping("/chat.sendContent")
	@SendTo("/store/public")
	public Chat sendContent(@Payload Chat chat) {
		return chat;
	}
	@MessageMapping("/chat.addUser")
	@SendTo("/store/public")
	public Chat addUser(@Payload Chat chat, SimpMessageHeaderAccessor headerAccesor) {
		
		headerAccesor.getSessionAttributes().put("Username: ", chat.getType());
		return 	chat;
	}
}
