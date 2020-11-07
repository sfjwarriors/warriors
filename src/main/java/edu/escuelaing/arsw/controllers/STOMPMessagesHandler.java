package edu.escuelaing.arsw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class STOMPMessagesHandler {

    @Autowired
    SimpMessagingTemplate msgt;

    @MessageMapping("/store")
    public void handleBuyEvent(@DestinationVariable String storeName/*, @DestinationVariable String functionDate, @DestinationVariable String movieName*/) throws Exception {
//        System.out.println("Nuevo asiento recibido en el servidor!:"+st);
//        System.out.println(cinemaName+functionDate.substring(0,10)+" "+functionDate.substring(10)+ movieName);
//        cinemaServices.buyTicket(st.getRow(), st.getCol(), cinemaName, functionDate.substring(0,10)+" "+functionDate.substring(10), movieName);
        System.out.println("storeName");
//        msgt.convertAndSend("/topic/buyticket."+cinemaName+"."+functionDate+"."+movieName+"hello", st);
    }

    @MessageMapping("/store.all")
    public void handleNewStore() throws Exception {
//        System.out.println("Nuevo asiento recibido en el servidor!:"+st);
//        System.out.println(cinemaName+functionDate.substring(0,10)+" "+functionDate.substring(10)+ movieName);
//        cinemaServices.buyTicket(st.getRow(), st.getCol(), cinemaName, functionDate.substring(0,10)+" "+functionDate.substring(10), movieName);
        System.out.println("new Store detected");
        msgt.convertAndSend("/app.all", "newStore");
    }
}