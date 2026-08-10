package org.example.chatweb.controller;

import jakarta.validation.Valid;
import org.example.chatweb.dto.RegistroForm;
import org.example.chatweb.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistroController {

    private final UsuarioService usuarioService;

    public RegistroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/registro")
    public String formulario(Model model) {
        if (!model.containsAttribute("registroForm")) {
            model.addAttribute("registroForm", new RegistroForm());
        }
        return "registro";
    }

    @PostMapping("/registro")
    public String cadastrar(@Valid @ModelAttribute("registroForm") RegistroForm registroForm,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "registro";
        }

        try {
            usuarioService.cadastrar(registroForm);
        } catch (IllegalArgumentException ex) {
            model.addAttribute("erroCadastro", ex.getMessage());
            return "registro";
        }

        return "redirect:/login?registrado";
    }
}
