package uv.fei.tutorias.bussinesslogic;

import java.sql.ResultSet;
import java.util.ArrayList;
import uv.fei.tutorias.domain.Coordinador;

// author @liu
public interface ICoordinadorDAO {
    public ArrayList<Coordinador> findCoordinadorByName(String searchName);
    public Coordinador findCoordinadorById(int idCoordinador);
    public Coordinador getCoordinador(ResultSet resultSet);
    public ArrayList<Coordinador> findCoordinadorWithUsuarioByName(String searchName);
    public Coordinador findCoordinadorWithUsuarioById(int idCoordinador);
    public Coordinador getCoordinadorWithUsuario(ResultSet resultSet);
    public boolean addCoordinador(Coordinador coordinador);
    public boolean deleteCoordinadorById(int idCoordinador);
}
