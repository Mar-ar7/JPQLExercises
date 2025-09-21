package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

/*
ENUNCIADO:
Usa la NamedQuery "Cliente.buscarPorEmail" para buscar un cliente por correo.
Imprime el resultado o un mensaje si no existe.
*/
public class Activity08_BuscarClienteNamedQuery {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String emailABuscar = "ana.gomez@email.com";

            try {
                Cliente cliente = entityManager
                        .createNamedQuery("Cliente.buscarPorEmail", Cliente.class)
                        .setParameter("email", emailABuscar)
                        .getSingleResult();

                System.out.println("Cliente encontrado: " + cliente);
            } catch (NoResultException e) {
                System.out.println("No se encontró ningún cliente con el email: " + emailABuscar);
            }

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
