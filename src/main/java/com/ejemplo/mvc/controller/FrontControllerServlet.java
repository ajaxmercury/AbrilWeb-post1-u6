package com.ejemplo.mvc.controller;

import com.ejemplo.mvc.controller.comando.*;
import com.ejemplo.mvc.service.TareaService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/app")
public class FrontControllerServlet extends HttpServlet {
    private Map<String, Comando> comandos;

    @Override
    public void init() throws ServletException {
        // Inicializar servicios
        TareaService tareaService = new TareaService();

        // Cargar context-params
        String nombreApp = getServletContext().getInitParameter("app.nombre");
        String maxLongitudTituloStr = getServletContext().getInitParameter("app.maxLongitudTitulo");
        int maxLongitudTitulo = 80;
        if (maxLongitudTituloStr != null) {
            try {
                maxLongitudTitulo = Integer.parseInt(maxLongitudTituloStr);
            } catch (NumberFormatException e) {
                // Default 80
            }
        }
        
        getServletContext().setAttribute("nombreApp", nombreApp);
        getServletContext().setAttribute("maxLongitudTitulo", maxLongitudTitulo);

        // Registrar comandos
        comandos = new HashMap<>();
        comandos.put("listar", new ListarComando(tareaService));
        comandos.put("formulario", new FormularioComando());
        comandos.put("guardar", new GuardarComando(tareaService));
        comandos.put("eliminar", new EliminarComando(tareaService));
        comandos.put("completar", new CompletarComando(tareaService));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        procesar(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        procesar(request, response);
    }

    private void procesar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String nombreComando = request.getParameter("comando");
        if (nombreComando == null || nombreComando.isEmpty()) {
            nombreComando = "listar";
        }

        Comando comando = comandos.get(nombreComando);
        
        if (comando == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Comando no encontrado");
            return;
        }

        String vista = comando.ejecutar(request, response);
        if (vista != null) {
            request.getRequestDispatcher(vista).forward(request, response);
        }
    }
}
