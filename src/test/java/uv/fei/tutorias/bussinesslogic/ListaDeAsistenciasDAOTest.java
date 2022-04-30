package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.Estudiante;
import uv.fei.tutorias.domain.ListaDeAsistencias;
import uv.fei.tutorias.domain.PeriodoEscolar;
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.ProgramaEducativo;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;
import uv.fei.tutorias.domain.TutorAcademico;

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
        Persona personaEstudiante1 = new Persona(1,"JOSHUA ELIUD","HERNÁNDEZ","SUÁREZ","zS20015728@estudiantes.uv.mx","lesterhero2002@gmail.com");
        Persona personaTutorAcademico1 = new Persona(5,"MAX WILLIAM","MILLAN","MARTINEZ","mmillan@uv.mx","maxMillan@gmail.com");
        TutorAcademico tutorAcademico1 = new TutorAcademico(1,personaTutorAcademico1);
        ProgramaEducativo programaEducativo1 = new ProgramaEducativo(1,"INGENIERÍA DE SOFTWARE");
        Estudiante estudiante1 = new Estudiante(1,"S20015728",personaEstudiante1,tutorAcademico1,programaEducativo1);
        PeriodoEscolar periodoEscolar1 = new PeriodoEscolar(1,"2022-02-01","2022-07-01");
        SesionDeTutoriaAcademica sesionDeTutoriaAcademica1 = new SesionDeTutoriaAcademica(1,"2022-03-09",periodoEscolar1);
        ListaDeAsistencias listaDeAsistenciasEsperada = new ListaDeAsistencias(idListaDeAsistencias, sesionDeTutoriaAcademica1, estudiante1);
        ListaDeAsistencias listaDeAsistenciasObtenida = listaDeAsistenciasDao.findListaDeAsistenciasById(idListaDeAsistencias);
        assertTrue(listaDeAsistenciasEsperada.equals(listaDeAsistenciasObtenida));
    }
    
    @Test
    public void testGetListaDeAsistencias() {
        System.out.println("getListaDeAsistencias");
        int idListaDeAsistencias = 1;
        Persona personaEstudiante = new Persona(1,"JOSHUA ELIUD","HERNÁNDEZ","SUÁREZ","zS20015728@estudiantes.uv.mx","lesterhero2002@gmail.com");
        Persona personaTutorAcademico = new Persona(5,"MAX WILLIAM","MILLAN","MARTINEZ","mmillan@uv.mx","maxMillan@gmail.com");
        TutorAcademico tutorAcademico = new TutorAcademico(1,personaTutorAcademico);
        ProgramaEducativo programaEducativo = new ProgramaEducativo(1,"INGENIERÍA DE SOFTWARE");
        Estudiante estudiante = new Estudiante(1,"S20015728",personaEstudiante,tutorAcademico,programaEducativo);
        PeriodoEscolar periodoEscolar = new PeriodoEscolar(1,"2022-02-01","2022-07-01");
        SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica(1,"2022-03-09",periodoEscolar);
        ListaDeAsistencias listaDeAsistenciasEsperada = new ListaDeAsistencias(idListaDeAsistencias, sesionDeTutoriaAcademica, estudiante);
        ListaDeAsistenciasDAO listaDeAsistenciasDao = new ListaDeAsistenciasDAO();
        ListaDeAsistencias listaDeAsistenciasObtenida = new ListaDeAsistencias();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT * FROM ListaDeAsistencias " +
                "WHERE idListaDeAsistencias = ?";
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
        Persona personaEstudiante = new Persona(0,"","","","","");
        Persona personaTutorAcademico = new Persona(0,"","","","","");
        TutorAcademico tutorAcademico = new TutorAcademico(0,personaTutorAcademico);
        ProgramaEducativo programaEducativo = new ProgramaEducativo(0,"");
        Estudiante estudiante = new Estudiante(0,"",personaEstudiante,tutorAcademico,programaEducativo);
        PeriodoEscolar periodoEscolar = new PeriodoEscolar(0,"","");
        SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica(0,"",periodoEscolar);
        ListaDeAsistencias listaDeAsistencias = new ListaDeAsistencias(0, sesionDeTutoriaAcademica, estudiante);
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
