package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

/*
ENUNCIADO:
Listar libros publicados entre dos fechas (inclusive) con BETWEEN sobre fechaPublicacion.
Ordena por fechaPublicacion DESC.
Sugerencia: usa un rango amplio para garantizar resultados.
*/
public class Activity12_RangoFechasPublicacionLibros {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // Rango amplio para incluir todos los libros
            LocalDate inicio = LocalDate.now().minusYears(10);
            LocalDate fin = LocalDate.now().plusDays(1);

            List<Libro> libros = entityManager.createQuery(
                            "SELECT l FROM Libro l " +
                                    "WHERE l.fechaPublicacion BETWEEN :inicio AND :fin " +
                                    "ORDER BY l.fechaPublicacion DESC",
                            Libro.class)
                    .setParameter("inicio", inicio)
                    .setParameter("fin", fin)
                    .getResultList();

            if (libros.isEmpty()) {
                System.out.println("No se encontraron libros en el rango de fechas indicado.");
            } else {
                System.out.println("Libros publicados entre " + inicio + " y " + fin + ":");
                for (Libro libro : libros) {
                    System.out.println(libro);
                }
            }
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
