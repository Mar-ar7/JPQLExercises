package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

/*
ENUNCIADO:
Imprime libros paginando de 5 en 5, recorriendo todas las páginas.
Ordena por id ASC.
*/
public class Activity13_PaginacionLibros {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            int pageSize = 5;
            int offset = 0;
            List<Libro> libros;

            do {
                libros = entityManager.createQuery(
                                "SELECT l FROM Libro l ORDER BY l.id ASC",
                                Libro.class)
                        .setFirstResult(offset)
                        .setMaxResults(pageSize)
                        .getResultList();

                if (!libros.isEmpty()) {
                    System.out.println("Página con offset " + offset + ":");
                    for (Libro libro : libros) {
                        System.out.println(libro);
                    }
                    System.out.println("----------------------------");
                }

                offset += pageSize;
            } while (!libros.isEmpty());

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
