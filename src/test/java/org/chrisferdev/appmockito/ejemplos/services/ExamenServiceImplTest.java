package org.chrisferdev.appmockito.ejemplos.services;

import org.chrisferdev.appmockito.ejemplos.models.Examen;
import org.chrisferdev.appmockito.ejemplos.repositories.ExamenRepositoryOtro;
import org.chrisferdev.appmockito.ejemplos.repositories.ExamenRepository;
import org.chrisferdev.appmockito.ejemplos.repositories.PreguntaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ExamenServiceImplTest {

    ExamenRepository repository;
    ExamenService service;
    PreguntaRepository preguntaRepository;

    @BeforeEach
    void setUp() {
        repository = mock(ExamenRepository.class);
        preguntaRepository = mock(PreguntaRepository.class);
        service = new ExamenServiceImpl(repository, preguntaRepository);
    }

    @Test
    void findExamenPorNombre() {


        when(repository.findAll()).thenReturn(Datos.EXAMENES);
        Optional<Examen> examen = service .findExamenPorNombre("Matemáticas");

        assertTrue(examen.isPresent());
        assertEquals(5, examen.orElseThrow().getId());
        assertEquals("Matemáticas", examen.get().getNombre());
    }

    @Test
    void findExamenPorNombreListaVacia() {
        List<Examen> datos = Collections.emptyList();

        when(repository.findAll()).thenReturn(datos);
        Optional<Examen> examen = service .findExamenPorNombre("Matemáticas");
        
        assertFalse(examen.isPresent());
    }

    @Test
    void testPreguntaExamen() {
        when(repository.findAll()).thenReturn(Datos.EXAMENES);
        when(preguntaRepository.findPreguntasPorExamenId(7L)).thenReturn(Datos.PREGUNTAS);
        Examen examen = service.findExamenPorNombreConPreguntas("Historia");
        assertEquals(5, examen.getPreguntas().size());
        assertTrue(examen.getPreguntas().contains("integrales"));
    }

}