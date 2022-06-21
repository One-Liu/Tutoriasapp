package uv.fei.tutorias.utilidades;

import javafx.scene.control.CheckBox;
import uv.fei.tutorias.domain.Estudiante;

public class TablaEstudiante_Asistencia_EnRiesgo {
    private Estudiante estudiante;
    private CheckBox asistencia;
    private CheckBox enRiesgo;
    
    public TablaEstudiante_Asistencia_EnRiesgo() {
        this.estudiante = new Estudiante();
        this.asistencia = new CheckBox();
        this.enRiesgo = new CheckBox();
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
    
    public String getNombre() {
        return this.estudiante.getNombreCompleto();
    }

    public CheckBox getAsistencia() {
        return asistencia;
    }

    public CheckBox getEnRiesgo() {
        return enRiesgo;
    }
}
