package com.example.qlthitracnghiem.interfaces;

public interface CrudInterface<T> {
    int create(T t); // Create a new record in the database

    int update(T t); // Update an existing record in the database

    int delete(int id); // Delete a record from the database

    T read(int id); // Read a record from the database
}
