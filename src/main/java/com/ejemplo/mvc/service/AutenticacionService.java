package com.ejemplo.mvc.service;

import com.ejemplo.mvc.model.Usuario;
import com.ejemplo.mvc.model.UsuarioDAO;

public class AutenticacionService {
    private final UsuarioDAO usuarioDAO;

    public AutenticacionService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public Usuario autenticar(String username, String clave) {
        Usuario usuario = usuarioDAO.findByUsername(username);
        if (usuario != null && usuario.getClave().equals(clave)) {
            return usuario;
        }
        return null;
    }
}
