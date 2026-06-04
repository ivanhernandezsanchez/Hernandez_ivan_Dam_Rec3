/*
=========================================
AUTOR: Ivan Hernandez
GRUPO: DAM2
EXAMEN JDBC AWS RDS
FECHA: 04/06/2026
=========================================
*/





package examen.ivan.hernandez.dao;

import java.util.ArrayList;

public interface DAO<T> {
    void add(T object);
    void update(int id, T object);
    void delete(int id);
    T find(int id);
    ArrayList<T> findAll();
}
