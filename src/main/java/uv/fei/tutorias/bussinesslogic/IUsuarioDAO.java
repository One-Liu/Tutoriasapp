package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.Usuario;

import java.sql.SQLException;

public interface IUsuarioDAO {

    int agregarUsuario(Usuario usuario) throws SQLException;

    Usuario buscarUsuarioPorCorreoYContrasena(Usuario usuario) throws SQLException;

    boolean estaIdUsuarioEnTutorAcademico(int searchId) throws SQLException;

    boolean estaIdUsarionEnJefeDeCarrera(int searchId) throws SQLException;

    boolean estaIdUsuarionEnCoordinador(int searchId) throws SQLException;
}
