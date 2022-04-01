package uv.fei.tutorias.domain;

// author @liu
public class Coordinador {
    private int idCoordinador;
    private Persona persona;
    private ProgramaEducativo programaEducativo;

    // Getters of uv.fei.tutorias.domain.Coordinador
    public int getIdCoordinador() {
        return idCoordinador;
    }

    public Persona getPersona() {
        return persona;
    }

    public ProgramaEducativo getProgramaEducativo() {
        return programaEducativo;
    }

    // Setters of uv.fei.tutorias.domain.Coordinador
    public void setIdCoordinador(int idCoordinador) {
        this.idCoordinador = idCoordinador;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void setProgramaEducativo(ProgramaEducativo programaEducativo) {
        this.programaEducativo = programaEducativo;
    }
}