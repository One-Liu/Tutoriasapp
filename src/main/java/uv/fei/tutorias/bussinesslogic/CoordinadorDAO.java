package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.Coordinador;
import uv.fei.tutorias.domain.Persona;

// author @liu
public class CoordinadorDAO implements ICoordinadorDAO {

    private final Logger LOGGER = Logger.getLogger(CoordinadorDAO.class);

    @Override
    public ArrayList<Coordinador> findCoordinadorByName(String searchName) {
        ArrayList<Coordinador> coordinadores = new ArrayList<>();
        String query =
                "SELECT C.id, C.idProgramaEducativo, P.nombre, P.apellidoPaterno, P.apellidoMaterno " +
                        "FROM coordinador C INNER JOIN persona P ON P.id = C.idPersona " +
                        "WHERE CONCAT(P.nombre,\" \",P.apellidoPaterno,\" \",P.apellidoMaterno) LIKE ?";
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + searchName + "%");
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()) {
                throw new SQLException("No se han encontrado coordinadores con el nombre " + searchName);
            } else {
                do {
                    coordinadores.add(getCoordinador(resultSet));
                }while(resultSet.next());
            }
        } catch(SQLException ex) {
            LOGGER.warn(CoordinadorDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return coordinadores;
    }

    @Override
    public Coordinador findCoordinadorById(int idCoordinador) {
        Coordinador coordinador = new Coordinador();
        String query =
                "SELECT C.id, C.idProgramaEducativo, P.nombre, P.apellidoPaterno, P.apellidoMaterno " +
                        "FROM coordinador C INNER JOIN persona P ON P.id = C.idPersona " +
                        "WHERE C.id = ?";
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCoordinador);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("No se ha encontrado el coordinador con el id " + idCoordinador);
            }
            coordinador = getCoordinador(resultSet);
        } catch(SQLException ex) {
            LOGGER.warn(CoordinadorDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return coordinador;
    }

    private Coordinador getCoordinador(ResultSet resultSet) {
        int idCoordinador = 0;
        String nombre = "";
        String apellidoPaterno = "";
        String apellidoMaterno = "";
        int idProgramaEducativo = 0;
        try {
            idCoordinador = resultSet.getInt("id");
            nombre = resultSet.getString("nombre");
            apellidoPaterno = resultSet.getString("apellidoPaterno");
            apellidoMaterno = resultSet.getString("apellidoMaterno");
            idProgramaEducativo = resultSet.getInt("idProgramaEducativo");
        } catch(SQLException ex) {
            LOGGER.warn(CoordinadorDAO.class.getName(),ex);
        }
        Persona personaCoordinador = new Persona(nombre,apellidoPaterno,apellidoMaterno);
        Coordinador coordinador = new Coordinador(idCoordinador,personaCoordinador,idProgramaEducativo);
        return coordinador;
    }

    @Override
    public boolean addCoordinador(Coordinador coordinador) {
        boolean result = false;
        PersonaDAO personaDAO = new PersonaDAO();
        Persona personaCoordinador = new Persona(coordinador.getNombre(), coordinador.getApellidoPaterno(), coordinador.getApellidoMaterno());
        String query = "INSERT INTO coordinador (idProgramaEducativo,idPersona,idUsuario) VALUES (?,?,?)";
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, coordinador.getIdProgramaEducativo());
            statement.setInt(2, personaDAO.addPersonaReturnId(personaCoordinador));
            statement.setInt(3, coordinador.getUsuario().getId());
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: El coordinador no se ha agregado");
            }
            result = true;
        } catch(SQLException ex) {
            LOGGER.warn(CoordinadorDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return result;
    }

    @Override
    public boolean deleteCoordinadorById(int idCoordinador) {
        boolean result = false;
        String query = "DELETE FROM coordinador WHERE id = ?";
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCoordinador);
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: No se ha eliminado el coordinador con el id " + idCoordinador);
            }
            result = true;
        } catch(SQLException ex) {
            LOGGER.warn(CoordinadorDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return result;
    }
}