package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.Usuario;

import java.sql.SQLException;

public interface IUsuarioDAO {

    public int agregarUsuario(Usuario usuario) throws SQLException;

    public Usuario buscarUsuarioPorCorreoYContrasena(Usuario usuario) throws SQLException;

    public boolean estaIdUsuarioEnTutorAcademico(int searchId) throws SQLException;

    public boolean estaIdUsarionEnJefeDeCarrera(int searchId) throws SQLException;

    public boolean estaIdUsuarionEnCoordinador(int searchId) throws SQLException;
    
    public boolean validarUsuarioRegistrado(String correoInstitucional) throws SQLException;
}
