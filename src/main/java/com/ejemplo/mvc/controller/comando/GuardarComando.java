package com.ejemplo.mvc.controller.comando;

import com.ejemplo.mvc.model.Tarea;
import com.ejemplo.mvc.service.TareaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GuardarComando implements Comando {
    private final TareaService tareaService;

    public GuardarComando(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String titulo = request.getParameter("titulo");
        String categoria = request.getParameter("categoria");
        String prioridad = request.getParameter("prioridad");
        String fechaStr = request.getParameter("fechaLimite");

        Date fechaLimite = null;
        if (fechaStr != null && !fechaStr.isEmpty()) {
            try {
                fechaLimite = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr);
            } catch (ParseException e) {
                // Ignore para la parte 1
            }
        }

        Tarea tarea = new Tarea(null, titulo, categoria, prioridad, fechaLimite, false);
        
        Integer maxLongitudTitulo = (Integer) request.getServletContext().getAttribute("maxLongitudTitulo");
        if (maxLongitudTitulo == null) {
            maxLongitudTitulo = 80;
        }

        try {
            tareaService.guardar(tarea, maxLongitudTitulo);
            response.sendRedirect(request.getContextPath() + "/app?comando=listar");
            return null;
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            return "/WEB-INF/views/formulario.jsp";
        }
    }
}
