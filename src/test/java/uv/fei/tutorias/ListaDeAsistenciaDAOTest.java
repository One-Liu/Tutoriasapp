package uv.fei.tutorias;

import uv.fei.tutorias.bussinesslogic.ListaDeAsistenciaDAO;
import uv.fei.tutorias.domain.ListaDeAsistencia;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

// author @liu
public class ListaDeAsistenciaDAOTest {

    private ListaDeAsistencia listaDeAsistenciaNueva;
    private ListaDeAsistencia listaDeAsistencia1;
    private ListaDeAsistenciaDAO listaDeAsistenciaDAO;
    
    @Before
    public void inicializar() {
        listaDeAsistenciaNueva = new ListaDeAsistencia("",false,0,0);
        listaDeAsistencia1 = new ListaDeAsistencia("12:00",false,1,1);
        listaDeAsistenciaDAO = new ListaDeAsistenciaDAO();
    }

    @Test
    public void testObtenerListasDeAsistencia() throws SQLException {
        ArrayList<ListaDeAsistencia> listasDeAsistenciaEsperadas = new ArrayList<>();
        listasDeAsistenciaEsperadas.add(listaDeAsistencia1);
        ArrayList<ListaDeAsistencia> listasDeAsistenciaObtenidas = new ArrayList<>();
        listasDeAsistenciaObtenidas.addAll(listaDeAsistenciaDAO.obtenerListasDeAsistencia());
        assertTrue(listasDeAsistenciaEsperadas.equals(listasDeAsistenciaObtenidas));
    }

    @Test
    public void testModificarAsistencia() throws SQLException {
        
    }

    @Test
    public void testAgregarListaDeAsistencia() throws SQLException {
        assertTrue(listaDeAsistenciaDAO.agregarListaDeAsistencia(listaDeAsistenciaNueva));
    }
    
    @Test
    public void testValidarRegistroDeListasDeAsistencia() throws SQLException {
        
    }
    
    @Test
    public void testObtenerListaDeAsistenciasPorTutorYSesionDeTutoria() throws SQLException {
        
    }
}
