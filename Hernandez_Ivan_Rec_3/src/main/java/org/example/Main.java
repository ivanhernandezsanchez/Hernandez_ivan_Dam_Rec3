/*
=========================================
AUTOR: Ivan Hernandez
GRUPO: DAM2
EXAMEN JDBC AWS RDS
FECHA: 04/06/2026
=========================================
*/




package org.example;

import examen.ivan.hernandez.beans.Incidente;
import examen.ivan.hernandez.beans.Soc;
import examen.ivan.hernandez.dao.IncidenteDAOImpl;
import examen.ivan.hernandez.motores.MotorFactory;

public class Main {
    public static void main(String[] args) {
        IncidenteDAOImpl incidenteDAO = new IncidenteDAOImpl(MotorFactory.create(MotorFactory.POSTGRE));

        Soc soc = new Soc();
        soc.setId(1);
        Incidente incidente = new Incidente();
        incidente.setCodigoIncidente("1029");
        incidente.setTipoIncidente("Informatica");
        incidente.setFechaDeteccion("10/23/20");
        incidente.setEstado("Activo");
        incidente.setSoc(soc);


        // 1 - 5 //
        incidenteDAO.add(incidente);
        incidenteDAO.update(1,incidente);
        System.out.println(incidenteDAO.find(2));
        System.out.println(incidenteDAO.findAll());
        System.out.println(incidenteDAO.findByIncidente(3));
        System.out.println(incidenteDAO.findWithInforme(2));

        // 6 //
        System.out.println(incidenteDAO.findIncidentesCriticos());

    }
}