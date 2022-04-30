package uv.fei.tutorias.bussinesslogic;

import java.util.ArrayList;
import java.sql.ResultSet;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

// author @liu
public interface ISesionDeTutoriaAcademicaDAO {
    public ArrayList<SesionDeTutoriaAcademica> findSesionesDeTutoriaAcademicaByFecha(String searchDate);
    public SesionDeTutoriaAcademica findSesionDeTutoriaAcademicaById(int idSesionDeTutoriaAcademica);
    public SesionDeTutoriaAcademica getSesionDeTutoriaAcademica(ResultSet resultSet);
    public boolean addSesionDeTutoriaAcademica(SesionDeTutoriaAcademica sesionDeTutoriaAcademica);
    public boolean deleteSesionDeTutoriaAcademicaById(int idSesionDeTutoriaAcademica);
}
