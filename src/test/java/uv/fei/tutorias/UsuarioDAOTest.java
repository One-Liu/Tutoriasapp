package uv.fei.tutorias;

import org.junit.Before;
import org.junit.Test;
import uv.fei.tutorias.bussinesslogic.UsuarioDAO;
import uv.fei.tutorias.domain.Usuario;

import static org.junit.Assert.assertTrue;

public class UsuarioDAOTest {
    private Usuario usuario;
    private UsuarioDAO usuarioDao;
    @Before
    public void inicio(){
        usuario = new Usuario("BurritEstrella","zs123456@estudiantes.uv.mx");
        usuarioDao = new UsuarioDAO();
    }

    @Test
    public void AgregarUnUsuario(){
        assertTrue(usuarioDao.addUsuario(usuario));

    }
}
