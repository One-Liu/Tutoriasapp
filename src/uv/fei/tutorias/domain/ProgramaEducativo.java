package uv.fei.tutorias.domain;

// author @liu
public class ProgramaEducativo {
    private int idProgramaEducativo;
    private String nombreProgramaEducativo;
    
    // Constructors of uv.fei.tutorias.domain.ProgramaEducativo
    public ProgramaEducativo() {
        this.nombreProgramaEducativo = "";
    }
    
    public ProgramaEducativo(String nombreProgramaEducativo) {
        this.nombreProgramaEducativo = nombreProgramaEducativo;
    }
    
    public ProgramaEducativo(int idProgramaEducativo, String nombreProgramaEducativo) {
        this.idProgramaEducativo = idProgramaEducativo;
        this.nombreProgramaEducativo = nombreProgramaEducativo;
    }
    
    // Getters of uv.fei.tutorias.domain.ProgramaEducativo
    public int getIdProgramaEducativo() {
        return idProgramaEducativo;
    }

    public String getNombreProgramaEducativo() {
        return nombreProgramaEducativo;
    }

    // Setters of uv.fei.tutorias.domain.ProgramaEducativo
    public void setIdProgramaEducativo(int idProgramaEducativo) {
        this.idProgramaEducativo = idProgramaEducativo;
    }

    public void setNombreProgramaEducativo(String nombreProgramaEducativo) {
        this.nombreProgramaEducativo = nombreProgramaEducativo;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ProgramaEducativo) {
            ProgramaEducativo tmpProgramaEducativo = (ProgramaEducativo)obj;
            if(this.idProgramaEducativo == tmpProgramaEducativo.getIdProgramaEducativo() 
                    && this.nombreProgramaEducativo.equals(tmpProgramaEducativo.getNombreProgramaEducativo())) {
                return true;
            }
        }
        return false;
    }
}
