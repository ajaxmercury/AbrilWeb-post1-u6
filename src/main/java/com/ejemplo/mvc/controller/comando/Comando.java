package com.ejemplo.mvc.controller.comando;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Comando {
    /**
     * Ejecuta el comando y devuelve la ruta de la vista (forward) o null si ya respondió con sendRedirect.
     */
    String ejecutar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
