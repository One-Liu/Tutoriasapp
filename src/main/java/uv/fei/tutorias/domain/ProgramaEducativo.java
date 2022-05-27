package uv.fei.tutorias.domain;

// author @liu
public class ProgramaEducativo {
    private int id;
    private String nombre;

    // Constructors of uv.fei.tutorias.domain.ProgramaEducativo
    public ProgramaEducativo() {
        this.id = 0;
        this.nombre = "";
    }

    public ProgramaEducativo(String nombre) {
        this.id = 0;
        this.nombre = nombre;
    }
    
    public ProgramaEducativo(int idProgramaEducativo, String nombre) {
        this.id = idProgramaEducativo;
        this.nombre = nombre;
    }

    // Getters of uv.fei.tutorias.domain.ProgramaEducativo
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    // Setters of uv.fei.tutorias.domain.ProgramaEducativo
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ProgramaEducativo) {
            ProgramaEducativo tmpProgramaEducativo = (ProgramaEducativo)obj;
            if(this.id == tmpProgramaEducativo.getId() 
                    && this.nombre.equals(tmpProgramaEducativo.getNombre())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}