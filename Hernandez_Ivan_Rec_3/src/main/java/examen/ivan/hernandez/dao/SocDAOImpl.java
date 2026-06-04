/*
=========================================
AUTOR: Ivan Hernandez
GRUPO: DAM2
EXAMEN JDBC AWS RDS
FECHA: 04/06/2026
=========================================
*/




package examen.ivan.hernandez.dao;

import examen.ivan.hernandez.beans.Soc;
import examen.ivan.hernandez.motores.MotorSQL;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SocDAOImpl extends AbstractDAO<Soc> {

    private static final String SQL_FIND_ALL =
            "SELECT * " +
                    "FROM soc " +
                    "ORDER BY id";

    private static final String SQL_FIND =
            "SELECT * " +
                    "FROM soc " +
                    "WHERE id = ?";

    private static final String SQL_INSERT =
            "INSERT INTO soc (nombre, pais, nivelseguridad, autorexamen) " +
                    "VALUES (?, ?, ?, ?)";


    private static final String SQL_UPDATE =
            "UPDATE soc " +
                    "SET nombre = ?, pais = ?, nivelseguridad = ? " +
                    "WHERE id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM soc " +
                    "WHERE id = ?";

    public SocDAOImpl(MotorSQL motorSQL) {
        super(motorSQL);
    }

    @Override
    public void add(Soc soc) {
       try{
           motorSQL.connect();
           motorSQL.prepare(SQL_INSERT);
           motorSQL.getPs().setString(1, soc.getNombre());
           motorSQL.getPs().setString(2, soc.getPais());
           motorSQL.getPs().setInt(3, soc.getNivelSeguridad());
           motorSQL.getPs().setString(4, "HERNANDEZ_IVAN_DAM3");

           int rows = motorSQL.executeUpdate();
           System.out.println("INSERTADOS: " + rows);
       }catch (Exception e){
           printError(e);
       }finally {
           motorSQL.close();
       }

    }

    @Override
    public void update(int id, Soc soc) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_UPDATE);
            motorSQL.getPs().setString(1, soc.getNombre());
            motorSQL.getPs().setString(2, soc.getPais());
            motorSQL.getPs().setInt(3, soc.getNivelSeguridad());
            motorSQL.getPs().setInt(4, id);

            int rows = motorSQL.executeUpdate();
            System.out.println("ACTUALIZADOS: " + rows);

        }catch (Exception e){
            printError(e);
        }finally {
            motorSQL.close();
        }

    }

    @Override
    public void delete(int id) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_DELETE);
            motorSQL.getPs().setInt(1, id);

            int rows = motorSQL.executeUpdate();
            System.out.println("BORRADO: " + rows);

        }catch (Exception e){
            printError(e);
        }finally {
            motorSQL.close();
        }
    }

    @Override
    public Soc find(int id) {
       Soc soc = null;
       try{
           motorSQL.connect();
           motorSQL.prepare(SQL_FIND);
           motorSQL.getPs().setInt(1,id);

           ResultSet rs = motorSQL.executeQuery();
           if (rs.next()){
               soc = mapSoc(rs);
           }
       }catch (Exception e){
           printError(e);
       }finally {
           motorSQL.close();
       }
       return soc;
    }

    @Override
    public ArrayList<Soc> findAll() {
        ArrayList<Soc> socs = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_ALL);
            ResultSet rs = motorSQL.executeQuery();
            while(rs.next()){
                socs.add(mapSoc(rs));
            }
        }catch (Exception e){
            printError(e);
        }finally {
            motorSQL.close();
        }
        return socs;
    }

    private Soc mapSoc(ResultSet rs) throws Exception{
        Soc soc = new Soc();
        soc.setId(rs.getInt("id"));
        soc.setNombre(rs.getString("nombre"));
        soc.setPais(rs.getString("pais"));
        soc.setNivelSeguridad(rs.getInt("nivelseguridad"));
        return soc;
    }
}
