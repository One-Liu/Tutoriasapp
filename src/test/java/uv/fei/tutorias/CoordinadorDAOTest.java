package uv.fei.tutorias;

import uv.fei.tutorias.bussinesslogic.CoordinadorDAO;
import uv.fei.tutorias.domain.Coordinador;
import uv.fei.tutorias.domain.Persona;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

// author @liu
public class CoordinadorDAOTest {
    
    private Coordinador coordinadorNuevo;
    private Persona persona1;
    private Coordinador coordinador1;
    private CoordinadorDAO coordinadorDAO;
    
    @Before
    public void inicializar() {
        coordinadorNuevo = new Coordinador();
        coordinadorNuevo.setIdPersona(0);
        coordinadorNuevo.setIdUsuario(0);
        persona1 = new Persona("MARÍA DE LOS ÁNGELES","ARENAS","VALDÉZ",4);
        coordinador1 = new Coordinador(1,persona1);
        coordinadorDAO = new CoordinadorDAO();
    }
    
    @Test
    public void testObtenerCoordinadores() throws SQLException {
        ArrayList<Coordinador> coordinadoresEsperados = new ArrayList<>();
        coordinadoresEsperados.add(coordinador1);
//        List<Coordinador> coordinadoresObtenidos = coordinadorDAO.obtenerCoordinadores();
//        assertTrue(coordinadoresEsperados.equals(coordinadoresObtenidos));
    }

    @Test
    public void testObtenerCoordinadorPorId() throws SQLException {
        int idCoordinador = 1;
        Coordinador coordinadorObtenido = coordinadorDAO.obtenerCoordinadorPorId(idCoordinador);
        assertTrue(coordinador1.equals(coordinadorObtenido));
    }
    
    @Test
    public void testModificarCoordinador() throws SQLException {
        assertTrue(coordinadorDAO.modificarCoordinador(coordinador1));
    }
    
    @Test
    public void testObtenerCoordinadorPorIdUsuario() throws SQLException {
        
    }
}

