package uv.fei.tutorias;

import org.junit.Before;
import org.junit.Test;
import uv.fei.tutorias.bussinesslogic.ReporteDeTutoriaAcademicaDAO;
import uv.fei.tutorias.domain.ReporteDeTutoriaAcademica;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ReporteDeTutoriaDAOTest {
    private ReporteDeTutoriaAcademica reporteDeTutoriaAcademica;
    private ReporteDeTutoriaAcademicaDAO reporteDeTutoriaAcademicaDAO;

    @Before
    public void incializador(){
        reporteDeTutoriaAcademicaDAO = new ReporteDeTutoriaAcademicaDAO();
        reporteDeTutoriaAcademica = new ReporteDeTutoriaAcademica("El alumno va bien en custiones academicas",0,0,0);

    }
    @Test
    public void agregarReporteDeTutoria(){
        assertTrue(reporteDeTutoriaAcademicaDAO.addReporteDeTutoriaAcademica(reporteDeTutoriaAcademica));
    }

    @Test
    public void buscarReporteDetutoriaPorId(){
        assertNotNull(reporteDeTutoriaAcademicaDAO.findReporteDeTutoriaById(3));
    }

    @Test
    public void EliminarReporteDeTutoriaAcademicaPorId(){
        assertTrue(reporteDeTutoriaAcademicaDAO.deleteReporteDeTutoriasAcademicasById(3));
    }
}
