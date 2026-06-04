package examen.ivan.hernandez.dao;

import examen.ivan.hernandez.motores.MotorSQL;

import java.util.ArrayList;

public abstract class AbstractDAO<T> implements DAO<T> {

    protected MotorSQL motorSQL;
    public AbstractDAO(MotorSQL motorSQL){
        this.motorSQL = motorSQL;
    }

    protected void printError(Exception e){
        System.out.println("[ERROR]" + e.getMessage());
    }


}
