package uv.fei.tutorias.domain;

import uv.fei.tutorias.bussinesslogic.IHorarioDeSesionDeTutoriaDAO;
import uv.fei.tutorias.bussinesslogic.PersonaDAO;
import uv.fei.tutorias.dataaccess.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HorarioDeSesionDeTutoria {
    private int id;
    private String hora;
    private Estudiante estudiante;
    private SesionDeTutoriaAcademica sesionDeTutoriaAcademica;

    public HorarioDeSesionDeTutoria(String hora, Estudiante estudiante, SesionDeTutoriaAcademica sesionDeTutoriaAcademica) {
        this.hora = hora;
        this.estudiante = estudiante;
        this.sesionDeTutoriaAcademica = sesionDeTutoriaAcademica;
    }

    public HorarioDeSesionDeTutoria() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public SesionDeTutoriaAcademica getSesionDeTutoriaAcademica() {
        return sesionDeTutoriaAcademica;
    }

    public void setSesionDeTutoriaAcademica(SesionDeTutoriaAcademica sesionDeTutoriaAcademica) {
        this.sesionDeTutoriaAcademica = sesionDeTutoriaAcademica;
    }


}
