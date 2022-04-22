package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.ReporteDeTutoriaAcademica;

public interface IReporteDeTutoriaAcademicaDAO {
    public boolean addReporteDeTutoriaAcademica(ReporteDeTutoriaAcademica reporteDeTutoriaAcademica);
    public boolean deleteReporteDeTutoriasAcademicasById(int searchId);
    public ReporteDeTutoriaAcademica findReporteDeTutoriaById(int searchId);

}
