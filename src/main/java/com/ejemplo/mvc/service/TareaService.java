package com.ejemplo.mvc.service;

import com.ejemplo.mvc.model.Tarea;
import com.ejemplo.mvc.model.TareaDAO;

import java.util.List;

public class TareaService {
    
    private final TareaDAO tareaDAO;

    public TareaService() {
        this.tareaDAO = new TareaDAO();
    }

    public List<Tarea> obtenerTodas() {
        return tareaDAO.findAll();
    }

    public Tarea obtenerPorId(Integer id) {
        return tareaDAO.findById(id);
    }

    public void guardar(Tarea tarea, int maxLongitudTitulo) {
        if (tarea.getTitulo() == null || tarea.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("El título es obligatorio.");
        }
        if (tarea.getTitulo().length() > maxLongitudTitulo) {
            throw new IllegalArgumentException("El título excede la longitud máxima permitida.");
        }
        tareaDAO.save(tarea);
    }

    public void completar(Integer id) {
        Tarea tarea = tareaDAO.findById(id);
        if (tarea != null) {
            tarea.setCompletada(true);
            // Ya es la misma instancia de la lista en memoria por lo que se actualizará.
        }
    }

    public void eliminar(Integer id) {
        tareaDAO.delete(id);
    }
}
