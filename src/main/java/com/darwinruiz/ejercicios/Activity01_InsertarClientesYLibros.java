package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Cliente;
import com.darwinruiz.models.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Activity01_InsertarClientesYLibros {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            // ---------------------
            // CREAR CLIENTES
            // ---------------------
            Cliente cliente1 = new Cliente();
            cliente1.setNombre("Juan Pirir");
            cliente1.setEmail("juan.pirir@email.com");
            cliente1.setCiudad("Guatemala");
            cliente1.setEdad(22);
            cliente1.setFechaRegistro(LocalDate.of(2025, 1, 10));
            entityManager.persist(cliente1);

            Cliente cliente2 = new Cliente();
            cliente2.setNombre("Ana Gómez");
            cliente2.setEmail("ana.gomez@email.com");
            cliente2.setCiudad("Quiche");
            cliente2.setEdad(25);
            cliente2.setFechaRegistro(LocalDate.of(2025, 3, 15));
            entityManager.persist(cliente2);

            Cliente cliente3 = new Cliente();
            cliente3.setNombre("Luis Fernández");
            cliente3.setEmail("luis.fernandez@email.com");
            cliente3.setCiudad("Huehuetenango");
            cliente3.setEdad(40);
            cliente3.setFechaRegistro(LocalDate.of(2025, 2, 20));
            entityManager.persist(cliente3);

            // ---------------------
            // CREAR LIBROS
            // ---------------------
            Libro libro1 = new Libro();
            libro1.setTitulo("Aprendiendo Java");
            libro1.setAutorNombre("Carlos Ruiz");
            libro1.setGenero("Programación");
            libro1.setPrecio(new BigDecimal("29.99"));
            libro1.setStock(10);
            libro1.setFechaPublicacion(LocalDate.of(2024, 5, 10));
            libro1.setDescripcion("Libro introductorio a Java");
            entityManager.persist(libro1);

            Libro libro2 = new Libro();
            libro2.setTitulo("Hibernate Avanzado");
            libro2.setAutorNombre("Laura Martínez");
            libro2.setGenero("Programación");
            libro2.setPrecio(new BigDecimal("39.99"));
            libro2.setStock(0); // stock 0
            libro2.setFechaPublicacion(LocalDate.of(2023, 9, 15));
            libro2.setDescripcion("Guía completa de Hibernate");
            entityManager.persist(libro2);

            Libro libro3 = new Libro();
            libro3.setTitulo("Aprendiendo SQL");
            libro3.setAutorNombre("Miguel López");
            libro3.setGenero("Base de Datos");
            libro3.setPrecio(new BigDecimal("24.50"));
            libro3.setStock(5);
            libro3.setFechaPublicacion(LocalDate.of(2022, 12, 1));
            libro3.setDescripcion(null); // descripción null
            entityManager.persist(libro3);

            // ---------------------
            // COMMIT
            // ---------------------
            entityManager.getTransaction().commit();

            // ---------------------
            // IMPRIMIR IDS
            // ---------------------
            System.out.println("Clientes creados:");
            System.out.println("ID Cliente1: " + cliente1.getId());
            System.out.println("ID Cliente2: " + cliente2.getId());
            System.out.println("ID Cliente3: " + cliente3.getId());

            System.out.println("\nLibros creados:");
            System.out.println("ID Libro1: " + libro1.getId());
            System.out.println("ID Libro2: " + libro2.getId());
            System.out.println("ID Libro3: " + libro3.getId());

        } catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw exception;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
