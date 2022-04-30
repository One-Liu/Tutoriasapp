package uv.fei.tutorias.domain;

// author @liu
public class Coordinador {
    private int idCoordinador;
    private Persona persona;
    private int idProgramaEducativo;

    // Constructors of uv.fei.tutorias.domain.Coordinador
    public Coordinador() {
        this.idCoordinador = 0;
        this.persona = new Persona();
        this.idProgramaEducativo = 0;
    }

    public Coordinador(Persona persona, int idProgramaEducativo) {
        this.idCoordinador = 0;
        this.persona = persona;
        this.idProgramaEducativo = idProgramaEducativo;
    }
    
    public Coordinador(int idCoordinador, Persona persona, int idProgramaEducativo) {
        this.idCoordinador = idCoordinador;
        this.persona = persona;
        this.idProgramaEducativo = idProgramaEducativo;
    }
    
    // Getters of uv.fei.tutorias.domain.Coordinador
    public int getIdCoordinador() {
        return idCoordinador;
    }

    public Persona getPersona() {
        return persona;
    }

    public int getIdProgramaEducativo() {
        return idProgramaEducativo;
    }

    // Setters of uv.fei.tutorias.domain.Coordinador
    public void setIdCoordinador(int idCoordinador) {
        this.idCoordinador = idCoordinador;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void setIdProgramaEducativo(int idProgramaEducativo) {
        this.idProgramaEducativo = idProgramaEducativo;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Coordinador) {
            Coordinador tmpCoordinador = (Coordinador)obj;
            if(this.idCoordinador == tmpCoordinador.getIdCoordinador()
                    && this.persona.equals(tmpCoordinador.getPersona())
                    && this.idProgramaEducativo == tmpCoordinador.getIdProgramaEducativo()) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return persona.getNombreCompleto();
    }
}