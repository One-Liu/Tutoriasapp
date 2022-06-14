package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.Usuario;

import java.sql.SQLException;

public interface IUsarioDAO {

    int agregarUsuario(Usuario usuario) throws SQLException;

    Usuario buscarUsuarioPorCorreoYContrasena(Usuario usuario) throws SQLException;

    boolean estaIdUsuarioEnTutorAcademico(int searchId) throws SQLException;

    boolean estaIdUsarionEnJefeDeCarrera(int searchId);

    boolean estaIdUsuarionEnCoordinador(int searchId) throws SQLException;
}
