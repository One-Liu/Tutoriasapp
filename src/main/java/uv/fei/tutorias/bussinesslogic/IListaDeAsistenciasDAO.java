package uv.fei.tutorias.bussinesslogic;

import java.sql.ResultSet;
import java.util.ArrayList;
import uv.fei.tutorias.domain.ListaDeAsistencias;

// author @liu
public interface IListaDeAsistenciasDAO {
    public ListaDeAsistencias findListaDeAsistenciasById(int idListaDeAsistencias);
    public ArrayList<ListaDeAsistencias> findListaDeAsistenciasByIdEstudiante(int idEstudiante);
    public ArrayList<ListaDeAsistencias> findListaDeAsistenciasByIdSesionDeTutoriaAcademica(int idSesionDeTutoriaAcademica);
    public ListaDeAsistencias getListaDeAsistencias(ResultSet resultSet);
    public boolean addListaDeAsistencias(ListaDeAsistencias listaDeAsistencias);
    public boolean deleteListaDeAsistenciasById(int idListaDeAsistencias);
}
