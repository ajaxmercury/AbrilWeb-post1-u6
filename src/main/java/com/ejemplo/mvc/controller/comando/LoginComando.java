package com.ejemplo.mvc.controller.comando;

import com.ejemplo.mvc.model.Usuario;
import com.ejemplo.mvc.service.AutenticacionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginComando implements Comando {
    private final AutenticacionService autenticacionService;

    public LoginComando(AutenticacionService autenticacionService) {
        this.autenticacionService = autenticacionService;
    }

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            return "/WEB-INF/views/login.jsp";
        }

        String username = request.getParameter("username");
        String clave = request.getParameter("clave");

        Usuario usuario = autenticacionService.autenticar(username, clave);

        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioActual", usuario);
            session.setMaxInactiveInterval(1800); // 30 minutos
            response.sendRedirect(request.getContextPath() + "/app?comando=listar");
            return null;
        } else {
            request.setAttribute("errorLogin", "Usuario o contraseña incorrectos.");
            return "/WEB-INF/views/login.jsp";
        }
    }
}
