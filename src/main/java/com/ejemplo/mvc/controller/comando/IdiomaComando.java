package com.ejemplo.mvc.controller.comando;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class IdiomaComando implements Comando {

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idioma = request.getParameter("lang");
        if (idioma != null && (idioma.equals("es") || idioma.equals("en"))) {
            Cookie cookie = new Cookie("idiomaPreferido", idioma);
            cookie.setMaxAge(30 * 24 * 60 * 60); // 30 días
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        String referer = request.getHeader("Referer");
        if (referer != null) {
            response.sendRedirect(referer);
        } else {
            response.sendRedirect(request.getContextPath() + "/app");
        }
        return null;
    }
}
