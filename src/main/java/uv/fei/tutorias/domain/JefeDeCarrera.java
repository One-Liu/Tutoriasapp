package uv.fei.tutorias.domain;

public class JefeDeCarrera extends Usuario{
    private int id;



    public JefeDeCarrera() {

    }
    public JefeDeCarrera(Persona persona){
        super(persona.getNombre(),persona.getApellidoPaterno(),persona.getApellidoPaterno());
    }

    public JefeDeCarrera(String contrasena, String correoInstitucional, int id) {
        super(contrasena, correoInstitucional);
        this.id = id;
    }

    public JefeDeCarrera(String nombre, String apellidoPaterno, String apellidoMaterno, String contrasena, String correoInstitucional) {
        super(nombre, apellidoPaterno, apellidoMaterno, contrasena, correoInstitucional);
    }

    public int getIdJefeDeCarrera() {
        return id;
    }

    public void setIdJefeDeCarrera(int id) {
        this.id = id;
    }



}
