package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.ProgramaEducativo;
import uv.fei.tutorias.domain.TutorAcademico;

// author @liu
public class EstudianteDAOTest {
    
    public EstudianteDAOTest() {
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
    public void testFindEstudianteByName() {
        System.out.println("findEstudianteByName");
        String searchName = "MAX";
        Persona personaEstudiante1 = new Persona(1,"JOSHUA ELIUD","HERNÁNDEZ","SUÁREZ","zS20015728@estudiantes.uv.mx","lesterhero2002@gmail.com");
        Persona personaTutorAcademico1 = new Persona(5,"MAX WILLIAM","MILLAN","MARTINEZ","mmillan@uv.mx","maxMillan@gmail.com");
        TutorAcademico tutorAcademico1 = new TutorAcademico(1,personaTutorAcademico1);
        ProgramaEducativo programaEducativo1 = new ProgramaEducativo(1,"INGENIERÍA DE SOFTWARE");
        Estudiante estudianteEsperado1 = new Estudiante(1,"S20015728", personaEstudiante1, tutorAcademico1, programaEducativo1);
        List<Estudiante> estudiantesEsperados = new ArrayList<>();
        estudiantesEsperados.add(estudianteEsperado1);
        EstudianteDAO estudianteDao = new EstudianteDAO();
        List<Estudiante> estudiantesObtenidos = estudianteDao.findEstudianteByName(searchName);
        assertTrue(estudiantesEsperados.equals(estudiantesObtenidos));
    }
    
    @Test
    public void testFindEstudianteById() {
        System.out.println("findEstudianteById");
        int idEstudiante = 1;
        Persona personaEstudiante = new Persona(1,"JOSHUA ELIUD","HERNÁNDEZ","SUÁREZ","zS20015728@estudiantes.uv.mx","lesterhero2002@gmail.com");
        Persona personaTutorAcademico = new Persona(5,"MAX WILLIAM","MILLAN","MARTINEZ","mmillan@uv.mx","maxMillan@gmail.com");
        TutorAcademico tutorAcademico = new TutorAcademico(1,personaTutorAcademico);
        ProgramaEducativo programaEducativo = new ProgramaEducativo(1,"INGENIERÍA DE SOFTWARE");
        Estudiante estudianteEsperado = new Estudiante(idEstudiante,"S20015728", personaEstudiante, tutorAcademico, programaEducativo);
        EstudianteDAO estudianteDao = new EstudianteDAO();
        Estudiante estudianteObtenido = estudianteDao.findEstudianteById(idEstudiante);
        assertTrue(estudianteEsperado.equals(estudianteObtenido));
    }
    
    @Test
    public void testGetEstudiante() {
        System.out.println("getCoordinador");
        int idEstudiante = 1;
        Persona personaEstudiante = new Persona(1,"JOSHUA ELIUD","HERNÁNDEZ","SUÁREZ","zS20015728@estudiantes.uv.mx","lesterhero2002@gmail.com");
        Persona personaTutorAcademico = new Persona(5,"MAX WILLIAM","MILLAN","MARTINEZ","mmillan@uv.mx","maxMillan@gmail.com");
        TutorAcademico tutorAcademico = new TutorAcademico(1,personaTutorAcademico);
        ProgramaEducativo programaEducativo = new ProgramaEducativo(1,"INGENIERÍA DE SOFTWARE");
        Estudiante estudianteEsperado = new Estudiante(idEstudiante,"S20015728", personaEstudiante, tutorAcademico, programaEducativo);
        EstudianteDAO estudianteDao = new EstudianteDAO();
        Estudiante estudianteObtenido = new Estudiante();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT E.idEstudiante, E.matricula, P.idPersona AS idPersonaEstudiante, P.nombre AS nombreEstudiante, " +
                "P.apellidoPaterno AS apellidoPaternoEstudiante, P.apellidoMaterno AS apellidoMaternoEstudiante, " +
                "P.correoInstitucional AS correoInstitucionalEstudiante, P.correoPersonal AS correoPersonalEstudiante, " +
                "E.idTutorAcademico, E.idProgramaEducativo " +
                "FROM Estudiante E " +
                "LEFT JOIN Persona P ON P.idPersona = E.idPersona " +
                "WHERE idEstudiante = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idEstudiante);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("Estudiante not found");
            }
            estudianteObtenido = estudianteDao.getEstudiante(resultSet);
        } catch(SQLException ex) {
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        assertTrue(estudianteEsperado.equals(estudianteObtenido));
    }
    
    @Test
    public void testAddEstudiante() {
        System.out.println("addEstudiante");
        Persona personaEstudiante = new Persona("","","","","");
        Persona personaTutorAcademico = new Persona(0,"","","","","");
        TutorAcademico tutorAcademico = new TutorAcademico(0, personaTutorAcademico);
        ProgramaEducativo programaEducativo = new ProgramaEducativo(0, "");
        Estudiante estudiante = new Estudiante("", personaEstudiante, tutorAcademico, programaEducativo);
        EstudianteDAO estudianteDao = new EstudianteDAO();
        boolean result = estudianteDao.addEstudiante(estudiante);
        assertTrue(result);
    }
    
    @Test
    public void testDeleteEstudianteById() {
        System.out.println("deleteEstudianteById");
        int idEstudiante = 7;
        EstudianteDAO estudianteDao = new EstudianteDAO();
        boolean result = estudianteDao.deleteEstudianteById(idEstudiante);
        assertFalse(result);
    }
}
