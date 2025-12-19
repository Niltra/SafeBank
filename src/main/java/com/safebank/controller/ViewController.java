package com.safebank.controller;

import com.safebank.entity.Transaccion;
import com.safebank.entity.Usuario;
import com.safebank.service.TransaccionService;
import com.safebank.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ViewController {

    private final UsuarioService usuarioService;
    private final TransaccionService transaccionService;

    public ViewController(UsuarioService usuarioService, TransaccionService transaccionService) {
        this.usuarioService = usuarioService;
        this.transaccionService = transaccionService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }

    @GetMapping({ "/", "/dashboard" })
    public String dashboard(Model model, Principal principal) {
        // Principal contiene el usuario autenticado (email)
        String email = principal.getName();
        Usuario usuario = usuarioService.buscarPorEmail(email);
        List<Transaccion> transacciones = transaccionService.obtenerHistorial(email);

        model.addAttribute("usuario", usuario);
        model.addAttribute("transacciones", transacciones);

        return "dashboard";
    }
}
