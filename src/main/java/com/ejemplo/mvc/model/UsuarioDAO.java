package com.ejemplo.mvc.model;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private static final List<Usuario> usuarios = new ArrayList<>();

    static {
        // En producción las claves van con hash (bcrypt)
        usuarios.add(new Usuario("admin", "Admin123!", "Administrador General", "ADMIN"));
        usuarios.add(new Usuario("maria", "Maria2026!", "María Fernanda Rojas", "USER"));
    }

    public Usuario findByUsername(String username) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }
}
