package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import java.util.ArrayList;
import uv.fei.tutorias.domain.Coordinador;

// author @liu
public interface ICoordinadorDAO {
    public ArrayList<Coordinador> obtenerCoordinadores() throws SQLException;
    public Coordinador obtenerCoordinadorPorId(int idCoordinador) throws SQLException;
    public boolean agregarCoordinador(Coordinador coordinador) throws SQLException;
    public boolean eliminarCoordinadorPorId(int idCoordinador) throws SQLException;
    public boolean modificarCoordinador(Coordinador coordinador) throws SQLException;
}
