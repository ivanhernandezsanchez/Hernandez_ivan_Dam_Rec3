
/*
=========================================
AUTOR: Ivan Hernandez
GRUPO: DAM2
EXAMEN JDBC AWS RDS
FECHA: 04/06/2026
=========================================
*/




package examen.ivan.hernandez.beans;

public class Incidente {
    private int id;
    private String codigoIncidente;
    private String tipoIncidente;
    private String fechaDeteccion;
    private String estado;
    private Soc soc;
    private InformeIncidente informeIncidente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoIncidente() {
        return codigoIncidente;
    }

    public void setCodigoIncidente(String codigoIncidente) {
        this.codigoIncidente = codigoIncidente;
    }

    public String getTipoIncidente() {
        return tipoIncidente;
    }

    public void setTipoIncidente(String tipoIncidente) {
        this.tipoIncidente = tipoIncidente;
    }

    public String getFechaDeteccion() {
        return fechaDeteccion;
    }

    public void setFechaDeteccion(String fechaDeteccion) {
        this.fechaDeteccion = fechaDeteccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Soc getSoc() {
        return soc;
    }

    public void setSoc(Soc soc) {
        this.soc = soc;
    }

    public InformeIncidente getInformeIncidente() {
        return informeIncidente;
    }

    public void setInformeIncidente(InformeIncidente informeIncidente) {
        this.informeIncidente = informeIncidente;
    }

    @Override
    public String toString() {
        return "Incidente{" +
                "id=" + id +
                ", codigoIncidente='" + codigoIncidente + '\'' +
                ", tipoIncidente='" + tipoIncidente + '\'' +
                ", fechaDeteccion='" + fechaDeteccion + '\'' +
                ", estado='" + estado + '\'' +
                ", soc=" + soc +
                ", informeIncidente=" + informeIncidente +
                '}';
    }
}
