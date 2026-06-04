/*
=========================================
AUTOR: Ivan Hernandez
GRUPO: DAM2
EXAMEN JDBC AWS RDS
FECHA: 04/06/2026
=========================================
*/



package examen.ivan.hernandez.beans;

public class InformeIncidente {

    private int id;
    private boolean malwareDetectado;
    private int nivelSeveridad;
    private String conclusion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isMalwareDetectado() {
        return malwareDetectado;
    }

    public void setMalwareDetectado(boolean malwareDetectado) {
        this.malwareDetectado = malwareDetectado;
    }

    public int getNivelSeveridad() {
        return nivelSeveridad;
    }

    public void setNivelSeveridad(int nivelSeveridad) {
        this.nivelSeveridad = nivelSeveridad;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    @Override
    public String toString() {
        return "InformeIncidente{" +
                "id=" + id +
                ", malwareDetectado=" + malwareDetectado +
                ", nivelSeveridad=" + nivelSeveridad +
                ", conclusion='" + conclusion + '\'' +
                '}';
    }
}
