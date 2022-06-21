package uv.fei.tutorias.utilidades;

import javafx.scene.control.CheckBox;
import uv.fei.tutorias.domain.Estudiante;

public class TablaEstudiante_asistioEnRiesgo {
    private CheckBox asistio;
    private CheckBox enRiesgo;
    public Estudiante estudiante;

    public TablaEstudiante_asistioEnRiesgo() {
        this.asistio = new CheckBox();
        this.enRiesgo = new CheckBox();
        this.estudiante = new Estudiante();
    }

    public CheckBox getAsistio() {
        return asistio;
    }

    public void setAsistio(CheckBox asistio) {
        this.asistio = asistio;
    }

    public CheckBox getEnRiesgo() {
        return enRiesgo;
    }

    public void setEnRiesgo(CheckBox enRiesgo) {
        this.enRiesgo = enRiesgo;
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
}
