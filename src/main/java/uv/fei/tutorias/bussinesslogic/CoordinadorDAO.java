package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.Coordinador;
import uv.fei.tutorias.domain.Persona;

// author @liu
public class CoordinadorDAO implements ICoordinadorDAO {

    private final Logger LOGGER = Logger.getLogger(CoordinadorDAO.class);

    @Override
    public List<Coordinador> obtenerCoordinadores() throws SQLException {
        List<Coordinador> coordinadores = new ArrayList<>();
        String consulta =
                "SELECT C.id, P.nombre, P.apellidoPaterno, P.apellidoMaterno, P.idProgramaEducativo " +
                "FROM coordinador C INNER JOIN persona P ON P.id = C.idPersona";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            if(!resultado.next()) {
                LOGGER.warn(CoordinadorDAO.class.getName(), new SQLException());
                throw new SQLException("No hay conexion a la base de datos");
            } else {
                do {
                    coordinadores.add(getCoordinador(resultado));
                }while(resultado.next());
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return coordinadores;
    }

    @Override
    public Coordinador obtenerCoordinadorPorId(int idCoordinador) throws SQLException {
        Coordinador coordinador;
        String consulta =
                "SELECT C.id, P.nombre, P.apellidoPaterno, P.apellidoMaterno, P.idProgramaEducativo " +
                "FROM coordinador C INNER JOIN persona P ON P.id = C.idPersona " +
                "WHERE C.id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idCoordinador);
            ResultSet resultado = sentencia.executeQuery();
            if(!resultado.next()) {
                LOGGER.warn(CoordinadorDAO.class.getName(), new SQLException());
                throw new SQLException("No hay conexión con la base de datos");
            } else {
                coordinador = getCoordinador(resultado);
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return coordinador;
    }

    private Coordinador getCoordinador(ResultSet resultado) throws SQLException {
        int idCoordinador;
        String nombre;
        String apellidoPaterno;
        String apellidoMaterno;
        int idProgramaEducativo;

        idCoordinador = resultado.getInt("id");
        nombre = resultado.getString("nombre");
        apellidoPaterno = resultado.getString("apellidoPaterno");
        apellidoMaterno = resultado.getString("apellidoMaterno");
        idProgramaEducativo = resultado.getInt("idProgramaEducativo");

        Persona personaCoordinador = new Persona(nombre, apellidoPaterno, apellidoMaterno, idProgramaEducativo);
        Coordinador coordinador = new Coordinador(idCoordinador, personaCoordinador);
        return coordinador;
    }

    @Override
    public boolean agregarCoordinador(Coordinador coordinador) throws SQLException {
        String consulta = "INSERT INTO coordinador (idPersona,idUsuario) VALUES (?,?)";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, coordinador.getIdPersona());
            sentencia.setInt(2, coordinador.getIdUsuario());
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                LOGGER.warn(CoordinadorDAO.class.getName(), new SQLException());
                throw new SQLException("No hay conexión con la base de datos");
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return true;
    }

    @Override
    public boolean eliminarCoordinadorPorId(int idCoordinador) throws SQLException {
        String consulta = "DELETE FROM coordinador WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idCoordinador);
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                LOGGER.warn(CoordinadorDAO.class.getName(), new SQLException());
                throw new SQLException("No hay conexión con la base de datos");
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return true;
    }

    @Override
    public boolean modificarCoordinador(Coordinador coordinador) throws SQLException {
        String consulta =
                "UPDATE coordinador " +
                "SET idPersona = ?, " +
                "SET idUsuario = ? " +
                "WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, coordinador.getIdPersona());
            sentencia.setInt(2, coordinador.getIdUsuario());
            sentencia.setInt(3, coordinador.getIdCoordinador());
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                LOGGER.warn(CoordinadorDAO.class.getName(), new SQLException());
                throw new SQLException("No hay conexión con la base de datos");
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return true;
    }
    @Override
    public Coordinador obtenerCoordinadorPorIdUsuario(int idUsuario) throws SQLException {
        Coordinador coordinador;
        String query =
                "SELECT CR.id AS idCoordinador, P.idProgramaEducativo, P.nombre, P.apellidoPaterno, P.apellidoMaterno " +
                        "FROM coordinador CR INNER JOIN persona P ON P.id = TA.idPersona " +
                        "WHERE idUsuario = ?";
        ConexionBD dataBaseConnection = new ConexionBD();
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idUsuario);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                LOGGER.warn(TutorAcademicoDAO.class.getName(), new SQLException());
                throw new SQLException("No hay conexion a la base de datos");
            }else {
                coordinador = getCoordinador(resultSet);
            }
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return coordinador;
    }
}