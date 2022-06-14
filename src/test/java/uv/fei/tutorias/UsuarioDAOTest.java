package uv.fei.tutorias;

import org.junit.Before;
import org.junit.Test;
import uv.fei.tutorias.bussinesslogic.UsuarioDAO;
import uv.fei.tutorias.domain.Usuario;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UsuarioDAOTest {
    private Usuario usuario, usuario2;
    private UsuarioDAO usuarioDao;
    @Before
    public void inicio(){
        usuario = new Usuario("BurritoEstrella","zs123456@estudiantes.uv.mx");
        usuario2 = new Usuario("HTML2Semant","zs1212@estudiantes.uv.mx");
        usuarioDao = new UsuarioDAO();
    }

    @Test
    public void estaUsuarionEnCoordinador() throws SQLException {
        assertTrue(usuarioDao.estaIdUsuarionEnCoordinador(2));
    }
    @Test
    public void estaUsuarioenTutorAcademico() throws SQLException {
        assertTrue(usuarioDao.estaIdUsuarioEnTutorAcademico(21));
    }

}
