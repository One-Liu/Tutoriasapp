package uv.fei.tutorias;


import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import uv.fei.tutorias.bussinesslogic.ListaDeAsistenciaDAO;
import uv.fei.tutorias.domain.ListaDeAsistencia;

// author @liu
public class ListaDeAsistenciaDAOTest {
    
    public ListaDeAsistenciaDAOTest() {
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
    public void testFindListaDeAsistenciaById() {
        System.out.println("findListaDeAsistenciaById");
        int idListaDeAsistencia = 1;
        ListaDeAsistenciaDAO listaDeAsistenciaDao = new ListaDeAsistenciaDAO();
        ListaDeAsistencia listaDeAsistenciaEsperada = new ListaDeAsistencia(idListaDeAsistencia, "12:00", 1, 1);
        ListaDeAsistencia listaDeAsistenciaObtenida = listaDeAsistenciaDao.findListaDeAsistenciaById(idListaDeAsistencia);
        assertTrue(listaDeAsistenciaEsperada.equals(listaDeAsistenciaObtenida));
    }

    @Test
    public void testFindListasDeAsistenciaByIdEstudiante() {
        System.out.println("findListasDeAsistenciaByIdEstudiante");
        int idEstudiante = 0;
        ListaDeAsistenciaDAO listaDeAsistenciaDAO = new ListaDeAsistenciaDAO();
        ArrayList<ListaDeAsistencia> listasDeAsistenciaEsperadas = new ArrayList<>();
        ListaDeAsistencia listaDeAsistencia1 = new ListaDeAsistencia(1,"12:00",idEstudiante,1);
        listasDeAsistenciaEsperadas.add(listaDeAsistencia1);
        ArrayList<ListaDeAsistencia> listasDeAsistenciaObtenidas = listaDeAsistenciaDAO.findListasDeAsistenciaByIdEstudiante(idEstudiante);
        assertTrue(listasDeAsistenciaEsperadas.equals(listasDeAsistenciaObtenidas));
    }

    @Test
    public void testFindListasDeAsistenciaByIdSesionDeTutoriaAcademica() {
        System.out.println("findListasDeAsistenciaByIdSesionDeTutoriaAcademica");
        int idSesionDeTutoriaAcademica = 0;
        ListaDeAsistenciaDAO listaDeAsistenciaDAO = new ListaDeAsistenciaDAO();
        ArrayList<ListaDeAsistencia> listasDeAsistenciaEsperadas = new ArrayList<>();
        ListaDeAsistencia listaDeAsistencia1 = new ListaDeAsistencia(1, "12:00", 1, idSesionDeTutoriaAcademica);
        listasDeAsistenciaEsperadas.add(listaDeAsistencia1);
        ArrayList<ListaDeAsistencia> listasDeAsistenciaObtenidas = listaDeAsistenciaDAO.findListasDeAsistenciaByIdSesionDeTutoriaAcademica(idSesionDeTutoriaAcademica);
        assertTrue(listasDeAsistenciaEsperadas.equals(listasDeAsistenciaObtenidas));
    }


    @Test
    public void testAddListaDeAsistencia() {
        System.out.println("addListaDeAsistencia");
        ListaDeAsistencia listaDeAsistencia = new ListaDeAsistencia("12:00", 0, 0);
        ListaDeAsistenciaDAO listaDeAsistenciaDao = new ListaDeAsistenciaDAO();
        boolean result = listaDeAsistenciaDao.addListaDeAsistencia(listaDeAsistencia);
        assertTrue(result);
    }

    @Test
    public void testDeleteListaDeAsistenciaById() {
        System.out.println("deleteListaDeAsistenciaById");
        int idListaDeAsistencia = 0;
        ListaDeAsistenciaDAO listaDeAsistenciaDao = new ListaDeAsistenciaDAO();
        boolean result = listaDeAsistenciaDao.deleteListaDeAsistenciaById(idListaDeAsistencia);
        assertTrue(result);
    }
}
