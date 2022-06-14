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
    public void testObtenerListaDeAsistenciaPorId() throws SQLException {
        int idListaDeAsistencia = 1;
        ListaDeAsistencia listaDeAsistenciaObtenida = listaDeAsistenciaDAO.obtenerListaDeAsistenciaPorId(idListaDeAsistencia);
        assertTrue(listaDeAsistencia1.equals(listaDeAsistenciaObtenida));
    }

    @Test
    public void testObtenerListasDeAsistenciaPorIdEstudiante() throws SQLException {
        int idEstudiante = 0;
        ArrayList<ListaDeAsistencia> listasDeAsistenciaEsperadas = new ArrayList<>();
        listasDeAsistenciaEsperadas.add(listaDeAsistencia1);
        ArrayList<ListaDeAsistencia> listasDeAsistenciaObtenidas = new ArrayList<>();
        listasDeAsistenciaObtenidas.addAll(listaDeAsistenciaDAO.obtenerListasDeAsistenciaPorIdEstudiante(idEstudiante));
        assertTrue(listasDeAsistenciaEsperadas.equals(listasDeAsistenciaObtenidas));
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
    public void testAgregarListaDeAsistencia() throws SQLException {
        assertTrue(listaDeAsistenciaDAO.agregarListaDeAsistencia(listaDeAsistenciaNueva));
    }

    @Test
    public void testEliminarListaDeAsistenciaPorId() throws SQLException {
        int idListaDeAsistencia = 0;
        assertTrue(listaDeAsistenciaDAO.eliminarListaDeAsistenciaPorId(idListaDeAsistencia));
    }

    @Test
    public void testModificarListaDeAsistencia() throws SQLException {
        assertTrue(listaDeAsistenciaDAO.modificarListaDeAsistencia(listaDeAsistencia1));
    }
}
