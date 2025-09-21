package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

public class Activity02_ActualizarClientePorEmail {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            // Email del cliente a buscar
            String emailBuscado = "ana.gomez@email.com";

            try {
                // Usar NamedQuery
                Cliente cliente = entityManager.createNamedQuery("Cliente.buscarPorEmail", Cliente.class)
                        .setParameter("email", emailBuscado)
                        .getSingleResult();

                // Actualizar campos
                cliente.setNombre("Nuevo Nombre");
                cliente.setCiudad("Nueva Ciudad");

                // Guardar cambios
                entityManager.merge(cliente);
                System.out.println("Cliente actualizado: " + cliente);

            } catch (NoResultException e) {
                System.out.println("No se encontró un cliente con el email: " + emailBuscado);
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
