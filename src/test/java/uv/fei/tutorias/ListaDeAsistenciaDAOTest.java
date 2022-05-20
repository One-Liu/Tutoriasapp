package uv.fei.tutorias;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import uv.fei.tutorias.bussinesslogic.ListaDeAsistenciaDAO;
import uv.fei.tutorias.domain.ListaDeAsistencia;

// author @liu
public class ListaDeAsistenciaDAOTest {
    private ListaDeAsistencia listaDeAsistenciaNueva;
    private ListaDeAsistencia listaDeAsistencia1;
    private ListaDeAsistenciaDAO listaDeAsistenciaDAO;
    
    @Before
    public void init() {
        listaDeAsistenciaNueva = new ListaDeAsistencia("",0,0);
        listaDeAsistencia1 = new ListaDeAsistencia("12:00",1,1);
        listaDeAsistenciaDAO = new ListaDeAsistenciaDAO();
    }

    @Test
    public void testFindListaDeAsistenciaById() {
        int idListaDeAsistencia = 1;
        ListaDeAsistencia listaDeAsistenciaObtenida = listaDeAsistenciaDAO.findListaDeAsistenciaById(idListaDeAsistencia);
        assertTrue(listaDeAsistencia1.equals(listaDeAsistenciaObtenida));
    }

    @Test
    public void testFindListasDeAsistenciaByIdEstudiante() {
        int idEstudiante = 0;
        ArrayList<ListaDeAsistencia> listasDeAsistenciaEsperadas = new ArrayList<>();
        listasDeAsistenciaEsperadas.add(listaDeAsistencia1);
        ArrayList<ListaDeAsistencia> listasDeAsistenciaObtenidas = listaDeAsistenciaDAO.findListasDeAsistenciaByIdEstudiante(idEstudiante);
        assertTrue(listasDeAsistenciaEsperadas.equals(listasDeAsistenciaObtenidas));
    }

    @Test
    public void testFindListasDeAsistenciaByIdSesionDeTutoriaAcademica() {
        int idSesionDeTutoriaAcademica = 0;
        ArrayList<ListaDeAsistencia> listasDeAsistenciaEsperadas = new ArrayList<>();
        listasDeAsistenciaEsperadas.add(listaDeAsistencia1);
        ArrayList<ListaDeAsistencia> listasDeAsistenciaObtenidas = listaDeAsistenciaDAO.findListasDeAsistenciaByIdSesionDeTutoriaAcademica(idSesionDeTutoriaAcademica);
        assertTrue(listasDeAsistenciaEsperadas.equals(listasDeAsistenciaObtenidas));
    }


    @Test
    public void testAddListaDeAsistencia() {
        assertTrue(listaDeAsistenciaDAO.addListaDeAsistencia(listaDeAsistenciaNueva));
    }

    @Test
    public void testDeleteListaDeAsistenciaById() {
        int idListaDeAsistencia = 0;
        assertTrue(listaDeAsistenciaDAO.deleteListaDeAsistenciaById(idListaDeAsistencia));
    }
}
