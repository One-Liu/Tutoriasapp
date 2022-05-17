package uv.fei.tutorias.domain;

// author @liu
public class Coordinador {
    private int idCoordinador;
    private Persona persona;
    private int idProgramaEducativo;
    private int idUsuario;

    // Constructors of uv.fei.tutorias.domain.Coordinador
    public Coordinador() {
        this.idCoordinador = 0;
        this.persona = new Persona();
        this.idProgramaEducativo = 0;
        this.idUsuario = 0;
    }

    public Coordinador(Persona persona, int idProgramaEducativo, int idUsuario) {
        this.idCoordinador = 0;
        this.persona = persona;
        this.idProgramaEducativo = idProgramaEducativo;
        this.idUsuario = idUsuario;
    }

    public Coordinador(int idCoordinador, Persona persona, int idProgramaEducativo, int idUsuario) {
        this.idCoordinador = idCoordinador;
        this.persona = persona;
        this.idProgramaEducativo = idProgramaEducativo;
        this.idUsuario = idUsuario;
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

    public int getIdUsuario() {
        return idUsuario;
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

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Coordinador) {
            Coordinador tmpCoordinador = (Coordinador)obj;
            if(this.idCoordinador == tmpCoordinador.getIdCoordinador()
                    && this.persona.equals(tmpCoordinador.getPersona())
                    && this.idProgramaEducativo == tmpCoordinador.getIdProgramaEducativo()
                    && this.idUsuario == tmpCoordinador.getIdUsuario()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Coordinador{" +
                "idCoordinador=" + idCoordinador +
                ", persona=" + persona +
                ", idProgramaEducativo=" + idProgramaEducativo +
                ", idUsuario=" + idUsuario +
                '}';
    }
}