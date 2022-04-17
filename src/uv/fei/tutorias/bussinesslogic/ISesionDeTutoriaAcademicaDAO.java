package uv.fei.tutorias.bussinesslogic;

import java.util.List;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

// author @liu
public interface ISesionDeTutoriaAcademicaDAO {
    public List<SesionDeTutoriaAcademica> findSesionesDeTutoriaAcademicaByFecha(String searchDate);
    public SesionDeTutoriaAcademica findSesionDeTutoriaAcademicaById(int idSesionDeTutoriaAcademica);
    public boolean addSesionDeTutoriaAcademica(SesionDeTutoriaAcademica sesionDeTutoriaAcademica);
    public boolean deleteSesionDeTutoriaAcademicaById(int idSesionDeTutoriaAcademica);
}
