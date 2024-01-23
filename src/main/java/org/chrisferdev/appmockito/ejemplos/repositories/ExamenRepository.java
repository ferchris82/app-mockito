package org.chrisferdev.appmockito.ejemplos.repositories;

import org.chrisferdev.appmockito.ejemplos.models.Examen;

import java.util.List;

public interface ExamenRepository {
    List<Examen> findAll();
}
