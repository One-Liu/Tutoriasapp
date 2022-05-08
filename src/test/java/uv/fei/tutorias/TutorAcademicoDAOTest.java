package uv.fei.tutorias;

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

import uv.fei.tutorias.bussinesslogic.TutorAcademicoDAO;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.TutorAcademico;

// author @liu
public class TutorAcademicoDAOTest {
    
    public TutorAcademicoDAOTest() {
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
    public void testFindTutoresAcademicosByName() {
        System.out.println("findTutoresAcademicosByName");
        String searchName = "A";
        TutorAcademicoDAO tutorAcademicoDao = new TutorAcademicoDAO();
        ArrayList<TutorAcademico> tutoresAcademicosEsperados = new ArrayList<>();
        Persona personaTutorAcademico1 = new Persona(5,"MAX WILLIAM","MILLAN","MARTINEZ","mmillan@uv.mx","maxMillan@gmail.com");
        TutorAcademico tutorAcademico1 = new TutorAcademico(1,personaTutorAcademico1);
        tutoresAcademicosEsperados.add(tutorAcademico1);
        Persona personaTutorAcademico2 = new Persona(6,"ANGEL JUAN","SANCHEZ","GARCIA","angesanchez@uv.mx","angelSanchez@gmail.com");
        TutorAcademico tutorAcademico2 = new TutorAcademico(2,personaTutorAcademico2);
        tutoresAcademicosEsperados.add(tutorAcademico2);
        ArrayList<TutorAcademico> tutoresAcademicosObtenidos = tutorAcademicoDao.findTutoresAcademicosByName(searchName);
        assertTrue(tutoresAcademicosEsperados.equals(tutoresAcademicosObtenidos));
    }
    
    @Test
    public void testFindTutorAcademicoById() {
        System.out.println("findTutorAcademicoById");
        int idTutorAcademico = 2;
        TutorAcademicoDAO tutorAcademicoDao = new TutorAcademicoDAO();
        Persona personaTutorAcademicoEsperado = new Persona(6,"ANGEL JUAN","SANCHEZ","GARCIA","angesanchez@uv.mx","angelSanchez@gmail.com");
        TutorAcademico tutorAcademicoEsperado = new TutorAcademico(idTutorAcademico,personaTutorAcademicoEsperado);
        TutorAcademico tutorAcademicoObtenido = tutorAcademicoDao.findTutorAcademicoById(idTutorAcademico);
        assertTrue(tutorAcademicoEsperado.equals(tutorAcademicoObtenido));
    }
    
    @Test
    public void testGetTutorAcademico() {
        System.out.println("getTutorAcademico");
        int idTutorAcademico = 2;
        Persona personaTutorAcademicoEsperado = new Persona(6,"ANGEL JUAN","SANCHEZ","GARCIA","angesanchez@uv.mx","angelSanchez@gmail.com");
        TutorAcademico tutorAcademicoEsperado = new TutorAcademico(idTutorAcademico,personaTutorAcademicoEsperado);
        TutorAcademicoDAO tutorAcademicoDao = new TutorAcademicoDAO();
        TutorAcademico tutorAcademicoObtenido = new TutorAcademico();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT * FROM TutorAcademico TA LEFT JOIN Persona P ON P.idPersona = TA.idPersona WHERE idTutorAcademico = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idTutorAcademico);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == false) {
                throw new SQLException("Tutor Academico not found");
            }
            tutorAcademicoObtenido = tutorAcademicoDao.getTutorAcademico(resultSet);
        } catch (SQLException ex) {
            Logger.getLogger(TutorAcademicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        assertTrue(tutorAcademicoEsperado.equals(tutorAcademicoObtenido));
    }
    
    @Test
    public void testAddTutorAcademico() {
        System.out.println("addTutorAcademico");
        Persona personaTutorAcademico = new Persona("TUTOR1","DE","PRUEBA","tutor1@uv.mx","tutorPrueba1@gmail.com");
        TutorAcademicoDAO tutorAcademicoDao = new TutorAcademicoDAO();
        boolean result = tutorAcademicoDao.addTutorAcademico(personaTutorAcademico);
        assertTrue(result);
    }

    @Test
    public void testDeleteTutorAcademicoById() {
        System.out.println("deleteTutorAcademicoById");
        int idTutorAcademico = 7;
        TutorAcademicoDAO tutorAcademicoDao = new TutorAcademicoDAO();
        boolean result = tutorAcademicoDao.deleteTutorAcademicoById(idTutorAcademico);
        assertFalse(result);
    }
}
