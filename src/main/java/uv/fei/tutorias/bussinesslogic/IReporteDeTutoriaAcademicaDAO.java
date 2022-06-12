package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import uv.fei.tutorias.domain.ReporteDeTutoriaAcademica;

public interface IReporteDeTutoriaAcademicaDAO {
    public boolean agregarReporteDeTutoriaAcademica(ReporteDeTutoriaAcademica reporteDeTutoriaAcademica) throws SQLException;
    public boolean eliminarReporteDeTutoriasAcademicasPorId(int idReporteDeTutoriaAcademicaBusqueda) throws SQLException;
    public ReporteDeTutoriaAcademica obtenerReporteDeTutoriaPorId(int idReporteDeTutoriaAcademicaBusqueda) throws SQLException;
    public ReporteDeTutoriaAcademica obtenerReporteDeTutoriaPorIdSesionTutoriaYIdTutor(ReporteDeTutoriaAcademica reporteDeTutoriaAcademicaBusqueda) throws SQLException;
}
