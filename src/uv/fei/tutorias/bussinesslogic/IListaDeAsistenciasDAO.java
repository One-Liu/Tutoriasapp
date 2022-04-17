package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.ListaDeAsistencias;

// author @liu
public interface IListaDeAsistenciasDAO {
    public ListaDeAsistencias findListaDeAsistenciasById(int idListaDeAsistencias);
    public boolean addListaDeAsistencias(ListaDeAsistencias listaDeAsistencias);
    public boolean deleteListaDeAsistenciasById(int idListaDeAsistencias);
}
