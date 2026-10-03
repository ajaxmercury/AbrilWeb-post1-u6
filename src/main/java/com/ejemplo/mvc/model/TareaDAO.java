package com.ejemplo.mvc.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TareaDAO {
    private static final List<Tarea> tareas = new ArrayList<>();
    private static int contador = 1;

    static {
        // Tareas precargadas
        long currentTime = System.currentTimeMillis();
        long unDia = 24 * 60 * 60 * 1000L;
        
        tareas.add(new Tarea(contador++, "Diseñar el diagrama de clases MVC", "Diseño", "Alta", new Date(currentTime + (2 * unDia)), false));
        tareas.add(new Tarea(contador++, "Implementar el Front Controller", "Desarrollo", "Alta", new Date(currentTime + (4 * unDia)), false));
        tareas.add(new Tarea(contador++, "Redactar el README con decisiones de diseño", "Documentación", "Media", new Date(currentTime + (7 * unDia)), false));
    }

    public List<Tarea> findAll() {
        return Collections.unmodifiableList(tareas);
    }

    public Tarea findById(Integer id) {
        for (Tarea t : tareas) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    public void save(Tarea tarea) {
        if (tarea.getId() == null) {
            tarea.setId(contador++);
            tareas.add(tarea);
        } else {
            // Update exist logic si fuera necesario, para simplificar solo add (guardado)
            for (int i = 0; i < tareas.size(); i++) {
                if (tareas.get(i).getId().equals(tarea.getId())) {
                    tareas.set(i, tarea);
                    return;
                }
            }
            tareas.add(tarea);
        }
    }

    public void delete(Integer id) {
        tareas.removeIf(t -> t.getId().equals(id));
    }
}
