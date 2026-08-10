package org.example.chatweb.controller;

import org.example.chatweb.model.Sala;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.security.Principal;

@Controller
public class PaginaController {

    @GetMapping("/")
    public String raiz() {
        return "redirect:/salas";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/salas")
    public String salas(Model model) {
        model.addAttribute("salas", Sala.values());
        return "salas";
    }

    @GetMapping("/chat/{salaId}")
    public String chat(@PathVariable String salaId, Model model, Principal principal) {
        if (!Sala.existe(salaId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala inexistente");
        }

        Sala sala = Sala.porId(salaId);
        model.addAttribute("sala", sala);
        model.addAttribute("usuario", principal.getName());
        return "chat";
    }
}
