package uv.fei.tutorias;

import org.junit.Before;
import org.junit.Test;
import uv.fei.tutorias.bussinesslogic.JefeDeCarreraDAO;
import uv.fei.tutorias.domain.JefeDeCarrera;

import static org.junit.Assert.assertTrue;

public class JefeDeCarreraDAOTest {
    private JefeDeCarreraDAO jefeDeCarreraDAO;
    private JefeDeCarrera jefeDeCarrera;
    @Before
    public void init(){
        jefeDeCarreraDAO = new JefeDeCarreraDAO();
        jefeDeCarrera = new JefeDeCarrera();
        jefeDeCarrera.setIdPersona(40);
        jefeDeCarrera.getUsuario().setId(1);
    }
    @Test
    public void addJefeDeCarrera(){
        assertTrue(jefeDeCarreraDAO.agregarJefeDeCarrera(jefeDeCarrera));
    }

}
