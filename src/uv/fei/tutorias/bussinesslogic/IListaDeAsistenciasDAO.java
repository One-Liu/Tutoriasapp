package uv.fei.tutorias.bussinesslogic;

import java.sql.ResultSet;
import uv.fei.tutorias.domain.ListaDeAsistencias;

// author @liu
public interface IListaDeAsistenciasDAO {
    public ListaDeAsistencias findListaDeAsistenciasById(int idListaDeAsistencias);
    public ListaDeAsistencias getListaDeAsistencias(ResultSet resultSet);
    public boolean addListaDeAsistencias(ListaDeAsistencias listaDeAsistencias);
    public boolean deleteListaDeAsistenciasById(int idListaDeAsistencias);
}
