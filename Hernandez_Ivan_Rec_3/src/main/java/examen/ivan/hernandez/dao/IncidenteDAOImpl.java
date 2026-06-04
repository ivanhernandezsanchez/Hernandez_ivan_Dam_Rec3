/*
=========================================
AUTOR: Ivan Hernandez
GRUPO: DAM2
EXAMEN JDBC AWS RDS
FECHA: 04/06/2026
=========================================
*/




package examen.ivan.hernandez.dao;

import examen.ivan.hernandez.beans.Incidente;
import examen.ivan.hernandez.beans.InformeIncidente;
import examen.ivan.hernandez.beans.Soc;
import examen.ivan.hernandez.motores.MotorSQL;

import java.sql.ResultSet;
import java.util.ArrayList;

public class IncidenteDAOImpl extends AbstractDAO<Incidente>{

    private static final String SQL_FIND_ALL =
            "SELECT * " +
                    "FROM incidente " +
                    "ORDER BY id";


    private static final String SQL_FIND =
            "SELECT * " +
                    "FROM incidente " +
                    "WHERE id = ?";


    private static final String SQL_INSERT =
            "INSERT INTO incidente (codigoincidente, tipoincidente, fechadeteccion, estado, socid, autorexamen) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE incidente " +
                    "SET codigoincidente = ?, tipoincidente = ?, fechadeteccion = ?, estado = ? " +
                    "WHERE id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM incidente " +
                    "WHERE id = ?";


    private static final String SQL_FIN_BY_SOC =
            "SELECT * " +
                    "FROM incidente " +
                    "WHERE socid = ? " +
                    "ORDER BY id ";

    private static final String SQL_FIND_WITH_INFORME =
            "SELECT incidente.*, informeincidente.id AS informeid, informeincidente.malwaredetectado, informeincidente.nivelseveridad, informeincidente.conclusion " +
                    "FROM incidente " +
                    "INNER JOIN informeincidente ON informeincidente.incidenteid = incidente.id " +
                    "WHERE incidente.id = ?";


    private static final String SQL_INCIDENTES_CRITICOS =
            "SELECT incidente.*, " +
                    "soc.nombre AS socnombre, soc.pais, soc.nivelseguridad, " +
                    "informeincidente.id AS informeid, informeincidente.malwaredetectado, informeincidente.nivelseveridad, informeincidente.conclusion " +
                    "FROM incidente " +
                    "INNER JOIN soc ON soc.id = incidente.socid " +
                    "INNER JOIN informeincidente ON informeincidente.incidenteid = incidente.id " +
                    "WHERE informeincidente.malwaredetectado = true " +
                    "AND informeincidente.nivelseveridad > 90 " +
                    "AND soc.pais = 'España'";





    public IncidenteDAOImpl(MotorSQL motorSQL) {
        super(motorSQL);
    }

    @Override
    public void add(Incidente incidente) {
        try{
            motorSQL.connect();
            motorSQL.prepare(SQL_INSERT);
            motorSQL.getPs().setString(1, incidente.getCodigoIncidente());
            motorSQL.getPs().setString(2, incidente.getTipoIncidente());
            motorSQL.getPs().setString(3, incidente.getFechaDeteccion());
            motorSQL.getPs().setString(4, incidente.getEstado());
            motorSQL.getPs().setInt(5, incidente.getSoc().getId());
            motorSQL.getPs().setString(6,"HERNANDEZ_IVAN_DAM3");

            int rows = motorSQL.executeUpdate();
            System.out.println("INSERTADOS: " + rows);
        }catch (Exception e){
            printError(e);
        }finally {
            motorSQL.close();
        }
    }

    @Override
    public void update(int id, Incidente incidente) {
        try{
            motorSQL.connect();
            motorSQL.prepare(SQL_UPDATE);
            motorSQL.getPs().setString(1,incidente.getCodigoIncidente());
            motorSQL.getPs().setString(2, incidente.getTipoIncidente());
            motorSQL.getPs().setString(3, incidente.getFechaDeteccion());
            motorSQL.getPs().setString(4, incidente.getEstado());
            motorSQL.getPs().setInt(5,id);

            int rows = motorSQL.executeUpdate();
            System.out.println("ACTUALIZADOS:" + rows);
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
    public Incidente find(int id) {
        Incidente incidente = null;
        try{
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND);
            motorSQL.getPs().setInt(1,id);

            ResultSet rs = motorSQL.executeQuery();
            if (rs.next()) {
                incidente =mapIncidente((rs));
            }
        }catch (Exception e){
            printError(e);
        }finally {
            motorSQL.close();
        }
        return incidente;
    }

    @Override
    public ArrayList<Incidente> findAll() {
        ArrayList<Incidente> incidentes = new ArrayList<>();
        try{
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_ALL);

            ResultSet rs = motorSQL.executeQuery();
            while (rs.next()) {
                incidentes.add(mapIncidente(rs));
            }
        }catch (Exception e){
            printError(e);
        }finally {
            motorSQL.close();
        }
        return incidentes;
    }

    public ArrayList<Incidente> findByIncidente(int id) {
        ArrayList<Incidente> incidentes = new ArrayList<>();
        try{
            motorSQL.connect();
            motorSQL.prepare(SQL_FIN_BY_SOC);
            motorSQL.getPs().setInt(1,id);

            ResultSet rs = motorSQL.executeQuery();
            while (rs.next()) {
                incidentes.add(mapIncidente(rs));
            }
        }catch (Exception e){
            printError(e);
        }finally {
            motorSQL.close();
        }
        return incidentes;
    }

    public Incidente findWithInforme(int id) {
        Incidente incidente = null;
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_WITH_INFORME);
            motorSQL.getPs().setInt(1, id);
            ResultSet rs = motorSQL.executeQuery();
            if (rs.next()) {
                incidente = mapIncidente(rs);
                InformeIncidente informeIncidente = new InformeIncidente();
                informeIncidente.setId(rs.getInt("informeid"));
                informeIncidente.setMalwareDetectado(rs.getBoolean("malwaredetectado"));
                informeIncidente.setNivelSeveridad(rs.getInt("nivelseveridad"));
                informeIncidente.setConclusion(rs.getString("conclusion"));
                incidente.setInformeIncidente(informeIncidente);
            }

        }catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return  incidente;
    }

    public ArrayList<Incidente> findIncidentesCriticos() {
        ArrayList<Incidente> incidentes = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_INCIDENTES_CRITICOS);
            ResultSet rs = motorSQL.executeQuery();
            while (rs.next()) {
                Incidente incidente = mapIncidente(rs);

                Soc soc = new Soc();
                soc.setNombre(rs.getString("socnombre"));
                soc.setPais(rs.getString("pais"));
                incidente.setSoc(soc);

                InformeIncidente informeIncidente = new InformeIncidente();
                informeIncidente.setId(rs.getInt("informeid"));
                informeIncidente.setMalwareDetectado(rs.getBoolean("malwaredetectado"));
                informeIncidente.setNivelSeveridad(rs.getInt("nivelseveridad"));
                informeIncidente.setConclusion(rs.getString("conclusion"));
                incidente.setInformeIncidente(informeIncidente);

                incidentes.add(incidente);
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return incidentes;
    }


    private Incidente mapIncidente(ResultSet rs) throws Exception {
        Incidente incidente = new Incidente();
        incidente.setId(rs.getInt("id"));
        incidente.setCodigoIncidente(rs.getString("codigoincidente"));
        incidente.setTipoIncidente(rs.getString("tipoincidente"));
        incidente.setFechaDeteccion(rs.getString("fechadeteccion"));
        incidente.setEstado(rs.getString("estado"));
        return incidente;
    }
}
