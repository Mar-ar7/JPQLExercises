package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Activity04_ListarClientesYFind {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // 1) Listar todos los clientes ordenados por nombre
            List<Cliente> clientes = entityManager
                    .createQuery("SELECT c FROM Cliente c ORDER BY c.nombre", Cliente.class)
                    .getResultList();

            System.out.println("Clientes ordenados por nombre:");
            for (Cliente c : clientes) {
                System.out.println(c);
            }

            // 2) Tomar el primero y buscarlo por ID usando entityManager.find()
            if (!clientes.isEmpty()) {
                Cliente primerCliente = clientes.get(0);
                Cliente encontrado = entityManager.find(Cliente.class, primerCliente.getId());

                System.out.println("\nCliente encontrado por ID:");
                System.out.println(encontrado);
            } else {
                System.out.println("No hay clientes en la base de datos.");
            }
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
