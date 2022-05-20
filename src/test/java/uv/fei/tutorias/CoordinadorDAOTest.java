package uv.fei.tutorias;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import uv.fei.tutorias.bussinesslogic.CoordinadorDAO;
import uv.fei.tutorias.domain.Coordinador;
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.Usuario;

// author @liu
public class CoordinadorDAOTest {
    private Coordinador coordinadorNuevo;
    private Persona persona1;
    private Coordinador coordinador1;
    private Usuario usuario1;
    private Coordinador coordinadorWithUsuario1;
    private CoordinadorDAO coordinadorDAO;
    
    @Before
    public void init() {
        coordinadorNuevo = new Coordinador();
        persona1 = new Persona("MARÍA DE LOS ÁNGELES","ARENAS","VALDÉZ");
        coordinador1 = new Coordinador(persona1,1);
        usuario1 = new Usuario("aarenas","aarenas@uv.mx");
        coordinadorWithUsuario1 = new Coordinador(persona1,1,usuario1);
        coordinadorDAO = new CoordinadorDAO();
    }
    
    @Test
    public void testFindCoordinadorByName() {
        String searchName = "MARÍA";
        ArrayList<Coordinador> coordinadoresEsperados = new ArrayList<>();
        coordinadoresEsperados.add(coordinador1);
        ArrayList<Coordinador> coordinadoresObtenidos = coordinadorDAO.findCoordinadorByName(searchName);
        assertTrue(coordinadoresEsperados.equals(coordinadoresObtenidos));
    }

    @Test
    public void testFindCoordinadorById() {
        int idCoordinador = 1;
        Coordinador coordinadorObtenido = coordinadorDAO.findCoordinadorById(idCoordinador);
        assertTrue(coordinador1.equals(coordinadorObtenido));
    }

    @Test
    public void testAddCoordinador() {
        assertTrue(coordinadorDAO.addCoordinador(coordinadorNuevo));

    }

    @Test
    public void testDeleteCoordinadorById() {
        int idCoordinador = 2;
        assertFalse(coordinadorDAO.deleteCoordinadorById(idCoordinador));
    }
}
