package uv.fei.tutorias;

import domain.Coordinador;
import domain.Persona;
import domain.Usuario;
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
    private Usuario usuario1;
    private Coordinador coordinadorWithUsuario1;
    private CoordinadorDAO coordinadorDAO;
    
    @Before
    public void inicializar() {
        coordinadorNuevo = new Coordinador();
        persona1 = new Persona("MARÍA DE LOS ÁNGELES","ARENAS","VALDÉZ");
        coordinador1 = new Coordinador(persona1,1);
        usuario1 = new Usuario("aarenas","aarenas@uv.mx");
        coordinadorWithUsuario1 = new Coordinador(persona1,1,usuario1);
        coordinadorDAO = new CoordinadorDAO();
    }
    
    @Test
    public void testObtenerCoordinadores() throws SQLException {
        ArrayList<Coordinador> coordinadoresEsperados = new ArrayList<>();
        coordinadoresEsperados.add(coordinador1);
        ArrayList<Coordinador> coordinadoresObtenidos = coordinadorDAO.obtenerCoordinadores();
        assertTrue(coordinadoresEsperados.equals(coordinadoresObtenidos));
    }

    @Test
    public void testObtenerCoordinadorPorId() throws SQLException {
        int idCoordinador = 1;
        Coordinador coordinadorObtenido = coordinadorDAO.obtenerCoordinadorPorId(idCoordinador);
        assertTrue(coordinador1.equals(coordinadorObtenido));
    }

    @Test
    public void testAgregarCoordinador() throws SQLException {
        assertTrue(coordinadorDAO.agregarCoordinador(coordinadorNuevo));

    }

    @Test
    public void testEliminarCoordinadorById() throws SQLException {
        int idCoordinador = 2;
        assertFalse(coordinadorDAO.eliminarCoordinadorPorId(idCoordinador));
    }
    
    @Test
    public void testModificarCoordinador() throws SQLException {
        assertTrue(coordinadorDAO.modificarCoordinador(coordinador1));
    }
}

