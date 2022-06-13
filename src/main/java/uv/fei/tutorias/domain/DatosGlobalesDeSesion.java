package uv.fei.tutorias.domain;

// author @liu
public class DatosGlobalesDeSesion {
    private static DatosGlobalesDeSesion instancia;
    private Usuario usuario;
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    private DatosGlobalesDeSesion() {
    }
    
    public static DatosGlobalesDeSesion getDatosGlobalesDeSesion() {
        if(instancia == null) {
            instancia = new DatosGlobalesDeSesion();
        }
        return instancia;
    }
}
