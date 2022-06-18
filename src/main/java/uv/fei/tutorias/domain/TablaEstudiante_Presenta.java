package uv.fei.tutorias.domain;

import javafx.scene.control.CheckBox;

public class TablaEstudiante_Presenta {
    private Estudiante estudiante;
    private CheckBox presenta;

    public TablaEstudiante_Presenta() {
        estudiante = new Estudiante();
        presenta = new CheckBox();
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
    
    public CheckBox getPresenta() {
        return presenta;
    }
}
