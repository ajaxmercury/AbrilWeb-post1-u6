package com.ejemplo.mvc.controller.comando;

import com.ejemplo.mvc.model.Tarea;
import com.ejemplo.mvc.service.TareaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

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

        Map<String, String> errores = new LinkedHashMap<>();

        Integer maxLongitudTitulo = (Integer) request.getServletContext().getAttribute("maxLongitudTitulo");
        if (maxLongitudTitulo == null) {
            maxLongitudTitulo = 80;
        }

        if (titulo == null || titulo.trim().isEmpty()) {
            errores.put("titulo", "El título es obligatorio.");
        } else if (titulo.length() > maxLongitudTitulo) {
            errores.put("titulo", "El título excede la longitud máxima (" + maxLongitudTitulo + ").");
        }

        if (categoria == null || categoria.trim().isEmpty()) {
            errores.put("categoria", "La categoría es obligatoria.");
        }

        if (prioridad == null || (!prioridad.equals("Alta") && !prioridad.equals("Media") && !prioridad.equals("Baja"))) {
            errores.put("prioridad", "La prioridad no es válida.");
        }

        Date fechaLimite = null;
        if (fechaStr == null || fechaStr.isEmpty()) {
            errores.put("fechaLimite", "La fecha límite es obligatoria.");
        } else {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false);
                fechaLimite = sdf.parse(fechaStr);
                
                Calendar calHoy = Calendar.getInstance();
                calHoy.set(Calendar.HOUR_OF_DAY, 0);
                calHoy.set(Calendar.MINUTE, 0);
                calHoy.set(Calendar.SECOND, 0);
                calHoy.set(Calendar.MILLISECOND, 0);
                Date hoy = calHoy.getTime();
                
                if (fechaLimite.before(hoy)) {
                    errores.put("fechaLimite", "La fecha límite no puede estar en el pasado.");
                }
            } catch (ParseException e) {
                errores.put("fechaLimite", "El formato de la fecha no es válido (yyyy-MM-dd).");
            }
        }

        if (!errores.isEmpty()) {
            request.setAttribute("errores", errores);
            request.setAttribute("titulo", titulo);
            request.setAttribute("categoria", categoria);
            request.setAttribute("prioridad", prioridad);
            request.setAttribute("fechaLimite", fechaStr);
            return "/WEB-INF/views/formulario.jsp";
        }

        Tarea tarea = new Tarea(null, titulo, categoria, prioridad, fechaLimite, false);
        try {
            tareaService.guardar(tarea, maxLongitudTitulo);
            response.sendRedirect(request.getContextPath() + "/app?comando=listar");
            return null;
        } catch (IllegalArgumentException e) {
            errores.put("general", e.getMessage());
            request.setAttribute("errores", errores);
            request.setAttribute("titulo", titulo);
            request.setAttribute("categoria", categoria);
            request.setAttribute("prioridad", prioridad);
            request.setAttribute("fechaLimite", fechaStr);
            return "/WEB-INF/views/formulario.jsp";
        }
    }
}
