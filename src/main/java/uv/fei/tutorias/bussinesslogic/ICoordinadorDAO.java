package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import java.util.List;
import uv.fei.tutorias.domain.Coordinador;

// author @liu
public interface ICoordinadorDAO {
    public List<Coordinador> obtenerCoordinadores() throws SQLException;
    public Coordinador obtenerCoordinadorPorId(int idCoordinador) throws SQLException;
    public boolean modificarCoordinador(Coordinador coordinador) throws SQLException;
    public Coordinador obtenerCoordinadorPorIdUsuario(int idUsuario) throws SQLException;
}
