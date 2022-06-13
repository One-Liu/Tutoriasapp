package uv.fei.tutorias.domain;

import javafx.scene.control.CheckBox;

public class TBLEstudiante_Presenta {
    private Estudiante estudiante;
    private CheckBox presenta;

    public TBLEstudiante_Presenta() {
        estudiante = new Estudiante();
        presenta = new CheckBox();
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
    
    public CheckBox getPresenta() {
        return presenta;
    }

    public void setPresenta(CheckBox presenta) {
        this.presenta = presenta;
    }
}
