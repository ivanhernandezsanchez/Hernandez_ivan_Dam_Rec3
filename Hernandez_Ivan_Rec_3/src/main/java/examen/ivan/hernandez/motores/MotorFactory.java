package examen.ivan.hernandez.motores;

public class MotorFactory {
    public static final String POSTGRE = "POSTGRE";
    public static MotorSQL create(String motor){
        switch (motor){
            case POSTGRE:
                return new PostgreMotorSQL();
            default:
                throw new IllegalArgumentException("Motor No SOportado");
        }
    }
}
