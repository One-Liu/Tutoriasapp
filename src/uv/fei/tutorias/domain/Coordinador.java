package uv.fei.tutorias.domain;

// author @liu
public class Coordinador {
    private int idCoordinador;
    private Persona persona;
    private ProgramaEducativo programaEducativo;

    // Constructors of uv.fei.tutorias.domain.Coordinador
    public Coordinador() {
        this.idCoordinador = 0;
        this.persona = null;
        this.programaEducativo = null;
    }
    
    public Coordinador(int idCoordinador, Persona persona, ProgramaEducativo programaEducativo) {
        this.idCoordinador = idCoordinador;
        this.persona = persona;
        this.programaEducativo = programaEducativo;
    }

    public Coordinador(Persona persona, ProgramaEducativo programaEducativo) {
        this.idCoordinador = 0;
        this.persona = persona;
        this.programaEducativo = programaEducativo;
    }
    
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
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Coordinador) {
            Coordinador tmpCoordinador = (Coordinador)obj;
            if(this.idCoordinador == tmpCoordinador.getIdCoordinador()
                    && this.persona.equals(tmpCoordinador.getPersona())
                    && this.programaEducativo.equals(tmpCoordinador.getProgramaEducativo())) {
                return true;
            }
        }
        return false;
    }
}