package uv.fei.tutorias.domain;

// author @liu

public class Coordinador {
    private int idCoordinador;
    private int idPersona;
    private int idProgramaEducativo;

    // Getters of uv.fei.tutorias.domain.Coordinador
    public int getIdCoordinador() {
        return idCoordinador;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public int getIdProgramaEducativo() {
        return idProgramaEducativo;
    }

    // Setters of uv.fei.tutorias.domain.Coordinador
    public void setIdCoordinador(int idCoordinador) {
        this.idCoordinador = idCoordinador;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
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