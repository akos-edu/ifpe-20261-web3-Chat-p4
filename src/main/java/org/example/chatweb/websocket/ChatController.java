package org.example.chatweb.websocket;

import org.example.chatweb.dto.ChatMessageDTO;
import org.example.chatweb.model.Sala;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ChatController {

    private static final String DESTINO_SALA = "/topic/sala.";

    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.{salaId}.entrar")
    public void entrar(@DestinationVariable String salaId,
                        SimpMessageHeaderAccessor headerAccessor,
                        Principal principal) {
        if (!Sala.existe(salaId) || principal == null) {
            return;
        }

        // guarda sala e usuario na sessao WS para o listener de desconexao poder avisar a sala
        headerAccessor.getSessionAttributes().put("sala", salaId);
        headerAccessor.getSessionAttributes().put("usuario", principal.getName());

        ChatMessageDTO mensagem = new ChatMessageDTO(
                ChatMessageDTO.Tipo.JOIN, null, principal.getName(), salaId);
        messagingTemplate.convertAndSend(DESTINO_SALA + salaId, mensagem);
    }

    @MessageMapping("/chat.{salaId}.enviar")
    public void enviar(@DestinationVariable String salaId,
                        ChatMessageDTO mensagemRecebida,
                        Principal principal) {
        if (!Sala.existe(salaId) || principal == null) {
            return;
        }

        ChatMessageDTO mensagem = new ChatMessageDTO(
                ChatMessageDTO.Tipo.CHAT, mensagemRecebida.getConteudo(), principal.getName(), salaId);
        messagingTemplate.convertAndSend(DESTINO_SALA + salaId, mensagem);
    }
}
