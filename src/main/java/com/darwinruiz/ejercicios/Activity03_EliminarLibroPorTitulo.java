package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

public class Activity03_EliminarLibroPorTitulo {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            String tituloBuscado = "Aprendiendo Java";

            try {
                Libro libro = entityManager
                        .createQuery("SELECT l FROM Libro l WHERE l.titulo = :titulo", Libro.class)
                        .setParameter("titulo", tituloBuscado)
                        .getSingleResult();

                entityManager.remove(libro);
                System.out.println("Libro eliminado: " + libro);

            } catch (NoResultException e) {
                System.out.println("No se encontró un libro con el título: " + tituloBuscado);
            }

            entityManager.getTransaction().commit();
        } catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw exception;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
