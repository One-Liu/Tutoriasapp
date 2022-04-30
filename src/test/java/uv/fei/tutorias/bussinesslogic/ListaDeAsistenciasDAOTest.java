package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.ListaDeAsistencias;

// author @liu
public class ListaDeAsistenciasDAOTest {
    
    public ListaDeAsistenciasDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testFindListaDeAsistenciasById() {
        System.out.println("findListaDeAsistenciasById");
        int idListaDeAsistencias = 1;
        ListaDeAsistenciasDAO listaDeAsistenciasDao = new ListaDeAsistenciasDAO();
        ListaDeAsistencias listaDeAsistenciasEsperada = new ListaDeAsistencias(idListaDeAsistencias, 1, 1);
        ListaDeAsistencias listaDeAsistenciasObtenida = listaDeAsistenciasDao.findListaDeAsistenciasById(idListaDeAsistencias);
        assertTrue(listaDeAsistenciasEsperada.equals(listaDeAsistenciasObtenida));
    }

    @Test
    public void testFindListaDeAsistenciasByIdEstudiante() {
        System.out.println("findListaDeAsistenciasByIdEstudiante");
        int idEstudiante = 0;
        ListaDeAsistenciasDAO listaDeAsistenciasDAO = new ListaDeAsistenciasDAO();
        ArrayList<ListaDeAsistencias> listasDeAsistenciasEsperadas = new ArrayList<>();
        ListaDeAsistencias listaDeAsistencias1 = new ListaDeAsistencias(1,idEstudiante,1);
        listasDeAsistenciasEsperadas.add(listaDeAsistencias1);
        ArrayList<ListaDeAsistencias> listasDeAsistenciasObtenidas = listaDeAsistenciasDAO.findListaDeAsistenciasByIdEstudiante(idEstudiante);
        assertTrue(listasDeAsistenciasEsperadas.equals(listasDeAsistenciasObtenidas));
    }

    @Test
    public void testFindListaDeAsistenciasByIdSesionDeTutoriaAcademica() {
        System.out.println("findListaDeAsistenciasByIdSesionDeTutoriaAcademica");
        int idSesionDeTutoriaAcademica = 0;
        ListaDeAsistenciasDAO listaDeAsistenciasDAO = new ListaDeAsistenciasDAO();
        ArrayList<ListaDeAsistencias> listasDeAsistenciasEsperadas = new ArrayList<>();
        ListaDeAsistencias listaDeAsistencias1 = new ListaDeAsistencias(1,1,idSesionDeTutoriaAcademica);
        listasDeAsistenciasEsperadas.add(listaDeAsistencias1);
        ArrayList<ListaDeAsistencias> listasDeAsistenciasObtenidas = listaDeAsistenciasDAO.findListaDeAsistenciasByIdSesionDeTutoriaAcademica(idSesionDeTutoriaAcademica);
        assertTrue(listasDeAsistenciasEsperadas.equals(listasDeAsistenciasObtenidas));
    }

    @Test
    public void testGetListaDeAsistencias() {
        System.out.println("getListaDeAsistencias");
        int idListaDeAsistencias = 1;
        ListaDeAsistencias listaDeAsistenciasEsperada = new ListaDeAsistencias(idListaDeAsistencias, 1, 1);
        ListaDeAsistenciasDAO listaDeAsistenciasDao = new ListaDeAsistenciasDAO();
        ListaDeAsistencias listaDeAsistenciasObtenida = new ListaDeAsistencias();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT * FROM ListaDeAsistencias WHERE idListaDeAsistencias = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idListaDeAsistencias);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("ListaDeAsistencias not found");
            }
            listaDeAsistenciasObtenida = listaDeAsistenciasDao.getListaDeAsistencias(resultSet);
        } catch(SQLException ex) {
            Logger.getLogger(ListaDeAsistenciasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        assertTrue(listaDeAsistenciasEsperada.equals(listaDeAsistenciasObtenida));
    }
    
    @Test
    public void testAddListaDeAsistencias() {
        System.out.println("addListaDeAsistencias");
        ListaDeAsistencias listaDeAsistencias = new ListaDeAsistencias(0, 0);
        ListaDeAsistenciasDAO listaDeAsistenciasDao = new ListaDeAsistenciasDAO();
        boolean result = listaDeAsistenciasDao.addListaDeAsistencias(listaDeAsistencias);
        assertTrue(result);
    }

    @Test
    public void testDeleteListaDeAsistenciasById() {
        System.out.println("deleteListaDeAsistenciasById");
        int idListaDeAsistencias = 0;
        ListaDeAsistenciasDAO listaDeAsistenciasDao = new ListaDeAsistenciasDAO();
        boolean result = listaDeAsistenciasDao.deleteListaDeAsistenciasById(idListaDeAsistencias);
        assertTrue(result);
    }
}
