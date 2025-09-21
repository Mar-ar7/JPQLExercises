package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Activity05_FiltrosOrdenPaginacionClientes {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {

            TypedQuery<Cliente> query = entityManager.createQuery(
                    "SELECT c FROM Cliente c WHERE LOWER(c.nombre) LIKE :patron ORDER BY c.ciudad ASC",
                    Cliente.class
            );
            query.setParameter("patron", "%a%"); // nombres que contengan 'a'


            query.setFirstResult(3);
            query.setMaxResults(3);


            List<Cliente> clientes = query.getResultList();


            System.out.println("Clientes con 'a' en el nombre (página 2):");
            for (Cliente c : clientes) {
                System.out.println(c);
            }

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
