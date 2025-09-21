package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.math.BigDecimal;
import java.util.List;

public class Activity06_AgruparLibrosPorGenero {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // Consulta JPQL: agrupar por género
            TypedQuery<Object[]> query = entityManager.createQuery(
                    "SELECT l.genero, COUNT(l), AVG(l.precio), SUM(l.stock) " +
                            "FROM Libro l " +
                            "GROUP BY l.genero " +
                            "ORDER BY l.genero ASC",
                    Object[].class
            );

            List<Object[]> resultados = query.getResultList();


            System.out.println("Género | COUNT | AVG Precio | SUM Stock");
            for (Object[] fila : resultados) {
                String genero = (String) fila[0];
                Long count = (Long) fila[1];
                Double avgPrecio = (Double) fila[2];
                Long sumStock = (Long) fila[3];

                System.out.printf("%-10s | %5d | %10.2f | %9d%n",
                        genero, count, avgPrecio, sumStock);
            }

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
