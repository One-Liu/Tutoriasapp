package uv.fei.tutorias.domain;

// author @liu
public class ProgramaEducativo {
    private int idProgramaEducativo;
    private String nombre;
    
    // Constructors of uv.fei.tutorias.domain.ProgramaEducativo
    public ProgramaEducativo() {
        this.idProgramaEducativo = 0;
        this.nombre = "";
    }
    
    public ProgramaEducativo(int idProgramaEducativo, String nombre) {
        this.idProgramaEducativo = idProgramaEducativo;
        this.nombre = nombre;
    }
    
    public ProgramaEducativo(String nombre) {
        this.idProgramaEducativo = 0;
        this.nombre = nombre;
    }
    
    // Getters of uv.fei.tutorias.domain.ProgramaEducativo
    public int getIdProgramaEducativo() {
        return idProgramaEducativo;
    }

    public String getNombre() {
        return nombre;
    }

    // Setters of uv.fei.tutorias.domain.ProgramaEducativo
    public void setIdProgramaEducativo(int idProgramaEducativo) {
        this.idProgramaEducativo = idProgramaEducativo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ProgramaEducativo) {
            ProgramaEducativo tmpProgramaEducativo = (ProgramaEducativo)obj;
            if(this.idProgramaEducativo == tmpProgramaEducativo.getIdProgramaEducativo() 
                    && this.nombre.equals(tmpProgramaEducativo.getNombre())) {
                return true;
            }
        }
        return false;
    }
}
