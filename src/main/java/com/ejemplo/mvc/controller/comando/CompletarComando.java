package com.ejemplo.mvc.controller.comando;

import com.ejemplo.mvc.service.TareaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CompletarComando implements Comando {
    private final TareaService tareaService;

    public CompletarComando(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                tareaService.completar(Integer.parseInt(idStr));
            } catch (NumberFormatException e) {
                // Ignore o redirigir
            }
        }
        response.sendRedirect(request.getContextPath() + "/app?comando=listar");
        return null;
    }
}
