package uv.fei.tutorias.bussinesslogic;

import java.sql.ResultSet;
import java.util.ArrayList;
import uv.fei.tutorias.domain.ListaDeAsistencia;

// author @liu
public interface IListaDeAsistenciaDAO {
    public ListaDeAsistencia findListaDeAsistenciaById(int idListaDeAsistencia);
    public ArrayList<ListaDeAsistencia> findListasDeAsistenciaByIdEstudiante(int idEstudiante);
    public ArrayList<ListaDeAsistencia> findListasDeAsistenciaByIdSesionDeTutoriaAcademica(int idSesionDeTutoriaAcademica);
    public boolean addListaDeAsistencia(ListaDeAsistencia listaDeAsistencia);
    public boolean deleteListaDeAsistenciaById(int idListaDeAsistencia);
}