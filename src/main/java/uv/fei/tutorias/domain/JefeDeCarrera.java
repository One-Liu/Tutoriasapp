package uv.fei.tutorias.domain;

public class JefeDeCarrera extends Usuario {
    private int idJefeDeCarrera;

    public JefeDeCarrera() {
        super();
        this.idJefeDeCarrera = 0;
    }
    
    public JefeDeCarrera(Persona jefeDeCarrera){
        super(jefeDeCarrera);
        this.idJefeDeCarrera = 0;
    }
    
    public JefeDeCarrera(Persona jefeDeCarrera, Usuario usuario) {
        super(usuario.getIdUsuario(), usuario.getContrasena(), usuario.getCorreoInstitucional(), jefeDeCarrera);
        this.idJefeDeCarrera = 0;
    }

    public JefeDeCarrera(int idJefeDeCarrera, Persona jefeDeCarrera, Usuario usuario) {
        super(usuario.getIdUsuario(), usuario.getContrasena(), usuario.getCorreoInstitucional(), jefeDeCarrera);
        this.idJefeDeCarrera = idJefeDeCarrera;
    }
    
    public int getIdJefeDeCarrera() {
        return idJefeDeCarrera;
    }

    public void setIdJefeDeCarrera(int idJefeDeCarrera) {
        this.idJefeDeCarrera = idJefeDeCarrera;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof JefeDeCarrera) {
            JefeDeCarrera tmpJefeDeCarrera = (JefeDeCarrera)obj;
            if(this.idJefeDeCarrera == tmpJefeDeCarrera.getIdJefeDeCarrera()
                    && this.idUsuario == tmpJefeDeCarrera.getIdUsuario()
                    && this.contrasena.equals(tmpJefeDeCarrera.getContrasena())
                    && this.correoInstitucional.equals(tmpJefeDeCarrera.getCorreoInstitucional())
                    && this.idPersona == tmpJefeDeCarrera.getIdPersona()
                    && this.nombre.equals(tmpJefeDeCarrera.getNombre())
                    && this.apellidoPaterno.equals(tmpJefeDeCarrera.getApellidoPaterno())
                    && this.apellidoMaterno.equals(tmpJefeDeCarrera.getApellidoMaterno())
                    && this.idProgramaEducativo == tmpJefeDeCarrera.getIdProgramaEducativo()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "JefeDeCarrera{" +
                "id=" + idJefeDeCarrera +
                ", Persona="+getNombreCompleto()+
                '}';
    }
}
