package com.darwinruiz.ejercicios;

import com.darwinruiz.dto.LibroResumenDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

/*
ENUNCIADO:
Proyecta Libros a LibroResumenDto (id, titulo, precio) usando:
SELECT new com.darwinruiz.dto.LibroResumenDto(l.id, l.titulo, l.precio)
Ordena por id ASC e imprime.
*/
public class Activity07_ProyeccionLibrosDto {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // Crear TypedQuery con proyección a DTO
            TypedQuery<LibroResumenDto> query = entityManager.createQuery(
                    "SELECT new com.darwinruiz.dto.LibroResumenDto(l.id, l.titulo, l.precio) " +
                            "FROM Libro l ORDER BY l.id ASC",
                    LibroResumenDto.class
            );

            List<LibroResumenDto> resultados = query.getResultList();

            // Imprimir resultados
            for (LibroResumenDto dto : resultados) {
                System.out.println(dto);
            }

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
