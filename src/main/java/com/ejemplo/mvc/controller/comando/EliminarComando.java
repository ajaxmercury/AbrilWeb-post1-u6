package com.ejemplo.mvc.controller.comando;

import com.ejemplo.mvc.model.Usuario;
import com.ejemplo.mvc.service.TareaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class EliminarComando implements Comando {
    private final TareaService tareaService;

    public EliminarComando(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // HttpSession session = request.getSession(false); // Garantizado por FrontController
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioActual");
        
        if (usuario == null || !"ADMIN".equals(usuario.getRol())) {
            request.setAttribute("error", "Solo un administrador puede eliminar tareas.");
            request.setAttribute("tareas", tareaService.obtenerTodas());
            return "/WEB-INF/views/lista.jsp";
        }

        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                tareaService.eliminar(Integer.parseInt(idStr));
            } catch (NumberFormatException e) {
                // Ignore o redirigir
            }
        }
        response.sendRedirect(request.getContextPath() + "/app?comando=listar");
        return null;
    }
}
