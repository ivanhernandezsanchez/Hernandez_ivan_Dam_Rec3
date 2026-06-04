package examen.ivan.hernandez.dao;

import examen.ivan.hernandez.beans.InformeIncidente;
import examen.ivan.hernandez.motores.MotorSQL;

import java.sql.ResultSet;
import java.util.ArrayList;

public class InformeIncidenteDAOImpl extends AbstractDAO<InformeIncidente> {

    private static final String SQL_FIND_ALL =
            "SELECT * " +
                    "FROM informeincidente " +
                    "ORDER BY id";

    private static final String SQL_FIND =
            "SELECT * " +
                    "FROM informeincidente " +
                    "WHERE id = ?";


    private static final String SQL_INSERT =
            "INSERT INTO informeincidente (malwaredetectado, nivelseveridad, conclusion, incidenteid, autorexamen) " +
                    "VALUES (?, ?, ?, ?, ?)";


    public static final String SQL_UPDATE =
            "UPDATE informeincidente " +
                    "SET malwaredetectado = ?, nivelseveridad = ?, conclusion = ?, incidenteid = ?, autorexamen = ? " +
                    "WHERE id = ?";


    private static final String SQL_DELETE =
            "DELETE FROM informeincidente " +
                    "WHERE id = ?";
    public InformeIncidenteDAOImpl(MotorSQL motorSQL) {
        super(motorSQL);
    }

    @Override
    public void add(InformeIncidente informeIncidente) {
        try{
            motorSQL.connect();
            motorSQL.prepare(SQL_INSERT);
            motorSQL.getPs().setBoolean(1, informeIncidente.isMalwareDetectado());
            motorSQL.getPs().setInt(2, informeIncidente.getNivelSeveridad());
            motorSQL.getPs().setString(3, informeIncidente.getConclusion());
            motorSQL.getPs().setString(4, "HERNANDEZ_IVAN_DAM3");


            int rows = motorSQL.executeUpdate();
            System.out.println(
                    "INSERTADOS: " +
                            rows);

        }catch (Exception e){

            printError(e);

        }finally {

            motorSQL.close();
        }
    }

    @Override
    public void update(int id, InformeIncidente informeIncidente) {
        try{
            motorSQL.connect();
            motorSQL.prepare(SQL_UPDATE);
            motorSQL.getPs().setBoolean(1, informeIncidente.isMalwareDetectado());
            motorSQL.getPs().setInt(2, informeIncidente.getNivelSeveridad());
            motorSQL.getPs().setString(3, informeIncidente.getConclusion());
            motorSQL.getPs().setInt(4, id);

            int rows = motorSQL.executeUpdate();
            System.out.println(
                    "ACTUALIZADOS: " +
                            rows);

        }catch (Exception e){

            printError(e);

        }finally {

            motorSQL.close();
        }
    }

    @Override
    public void delete(int id) {
        try{
            motorSQL.connect();
            motorSQL.prepare(SQL_DELETE);
            motorSQL.getPs().setInt(1,id);

            int rows = motorSQL.executeUpdate();
            System.out.println("BORRADO: " + rows);
        }catch (Exception e){
            printError(e);
        }finally {
            motorSQL.close();
        }

    }

    @Override
    public InformeIncidente find(int id) {
        InformeIncidente informeIncidente = null;
        try{
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND);
            motorSQL.getPs().setInt(1,id);



            ResultSet rs = motorSQL.executeQuery();
            if (rs.next()) {
                informeIncidente = mapInformeIncidente(rs);
            }

        }catch (Exception e){

            printError(e);

        }finally {

            motorSQL.close();
        }
        return informeIncidente;
    }

    @Override
    public ArrayList<InformeIncidente> findAll() {
        ArrayList<InformeIncidente> informeIncidentes = new ArrayList<>();
        try{
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_ALL);



            ResultSet rs = motorSQL.executeQuery();
            while (rs.next()) {
                informeIncidentes.add(mapInformeIncidente(rs));
            }

        }catch (Exception e){

            printError(e);

        }finally {

            motorSQL.close();
        }
        return informeIncidentes;
    }

    private InformeIncidente mapInformeIncidente(ResultSet rs) throws Exception{
        InformeIncidente informeIncidente = new InformeIncidente();
        informeIncidente.setId(rs.getInt("id"));
        informeIncidente.setMalwareDetectado(rs.getBoolean("malwaredetectado"));
        informeIncidente.setNivelSeveridad(rs.getInt("nivelseveridad"));
        informeIncidente.setConclusion(rs.getString("conclusion"));
        return informeIncidente;
    }
}
