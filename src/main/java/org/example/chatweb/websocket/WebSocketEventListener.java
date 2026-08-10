package org.example.chatweb.websocket;

import org.example.chatweb.dto.ChatMessageDTO;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketEventListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void aoDesconectar(SessionDisconnectEvent event) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());

        String usuario = (String) headerAccessor.getSessionAttributes().get("usuario");
        String sala = (String) headerAccessor.getSessionAttributes().get("sala");

        if (usuario != null && sala != null) {
            ChatMessageDTO mensagem = new ChatMessageDTO(
                    ChatMessageDTO.Tipo.LEAVE, null, usuario, sala);
            messagingTemplate.convertAndSend("/topic/sala." + sala, mensagem);
        }
    }
}
