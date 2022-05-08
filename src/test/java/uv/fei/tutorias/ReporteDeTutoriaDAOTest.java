package uv.fei.tutorias;

import org.junit.Test;
import uv.fei.tutorias.bussinesslogic.ReporteDeTutoriaAcademicaDAO;
import uv.fei.tutorias.domain.ReporteDeTutoriaAcademica;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ReporteDeTutoriaDAOTest {
    @Test
    public void addReporteDeTutoria(){
        ReporteDeTutoriaAcademica reporteDeTutoriaAcademica = new ReporteDeTutoriaAcademica(1, "El alumno va bien en sus EE", 1, 1);
        ReporteDeTutoriaAcademicaDAO reporteDeTutoriaAcademicaDAO = new ReporteDeTutoriaAcademicaDAO();
        assertTrue(reporteDeTutoriaAcademicaDAO.addReporteDeTutoriaAcademica(reporteDeTutoriaAcademica));
    }

    @Test
    public void findReporteDetutoriaById(){
        ReporteDeTutoriaAcademica reporteDeTutoriaAcademica = new ReporteDeTutoriaAcademica();
        ReporteDeTutoriaAcademicaDAO reporteDeTutoriaAcademicaDAO = new ReporteDeTutoriaAcademicaDAO();
        assertNotNull(reporteDeTutoriaAcademicaDAO.findReporteDeTutoriaById(2));
    }

    @Test
    public void DeleteReporteDeTutoriaAcademicaById(){
        ReporteDeTutoriaAcademicaDAO reporteDeTutoriaAcademicaDAO = new ReporteDeTutoriaAcademicaDAO();
        assertTrue(reporteDeTutoriaAcademicaDAO.deleteReporteDeTutoriasAcademicasById(2));
    }
}
