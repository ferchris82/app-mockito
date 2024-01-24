package org.chrisferdev.appmockito.ejemplos.services;

import org.chrisferdev.appmockito.ejemplos.models.Examen;

import java.util.Optional;

public interface ExamenService {
    Optional<Examen> findExamenPorNombre(String nombre);
}
