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

    private final Logger LOGGER = Logger.getLogger(PersonaDAO.class);

    @Override
    public ArrayList<Coordinador> findCoordinadorByName(String searchName) {
        ArrayList<Coordinador> coordinadores = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT C.id, C.idProgramaEducativo, P.nombre, P.apellidoPaterno, P.apellidoMaterno, FROM coordinador C LEFT JOIN persona P ON P.id = C.idPersona WHERE CONCAT(P.nombre,\" \",P.apellidoPaterno,\" \",P.apellidoMaterno) LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + searchName + "%");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("No se han encontrado coordinadores con el nombre " + searchName);
            } else {
                do {
                    coordinadores.add(getCoordinador(resultSet));
                }while(resultSet.next());
            }
        } catch(SQLException ex) {
            LOGGER.error(CoordinadorDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return coordinadores;
        }
    }

    @Override
    public Coordinador findCoordinadorById(int idCoordinador) {
        Coordinador coordinador = new Coordinador();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT C.id, C.idProgramaEducativo, P.nombre, P.apellidoPaterno, P.apellidoMaterno, FROM coordinador C LEFT JOIN persona P ON P.id = C.idPersona WHERE C.id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCoordinador);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("No se ha encontrado el coordinador con el id " + idCoordinador);
            }
            coordinador = getCoordinador(resultSet);
        } catch(SQLException ex) {
            LOGGER.error(CoordinadorDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return coordinador;
        }
    }

    @Override
    public Coordinador getCoordinador(ResultSet resultSet) {
        int idCoordinador = 0;
        String nombreCoordinador = "";
        String apellidoPaternoCoordinador = "";
        String apellidoMaternoCoordinador = "";
        int idProgramaEducativo = 0;
        try {
            idCoordinador = resultSet.getInt("id");
            nombreCoordinador = resultSet.getString("nombre");
            apellidoPaternoCoordinador = resultSet.getString("apellidoPaterno");
            apellidoMaternoCoordinador = resultSet.getString("apellidoMaterno");
            idProgramaEducativo = resultSet.getInt("idProgramaEducativo");
        } catch(SQLException ex) {
            LOGGER.error(CoordinadorDAO.class.getName(),ex);
        }
        Coordinador coordinador = new Coordinador(idCoordinador,nombreCoordinador,apellidoPaternoCoordinador,apellidoMaternoCoordinador,idProgramaEducativo);
        return coordinador;
    }

    @Override
    public ArrayList<Coordinador> findCoordinadorWithUsuarioByName(String searchName) {
        ArrayList<Coordinador> coordinadores = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT C.id, C.idProgramaEducativo, P.nombre, P.apellidoPaterno, P.apellidoMaterno, C.idUsuario FROM coordinador C LEFT JOIN persona P ON P.id = C.idPersona WHERE CONCAT(P.nombre,\" \",P.apellidoPaterno,\" \",P.apellidoMaterno) LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + searchName + "%");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("No se han encontrado coordinadores con el nombre " + searchName);
            } else {
                do {
                    coordinadores.add(getCoordinador(resultSet));
                }while(resultSet.next());
            }
        } catch(SQLException ex) {
            LOGGER.error(CoordinadorDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return coordinadores;
        }
    }

    @Override
    public Coordinador findCoordinadorWithUsuarioById(int idCoordinador) {
        Coordinador coordinador = new Coordinador();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT C.id, C.idProgramaEducativo, P.nombre, P.apellidoPaterno, P.apellidoMaterno, C.idUsuario FROM coordinador C LEFT JOIN persona P ON P.id = C.idPersona WHERE C.id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCoordinador);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("No se ha encontrado el coordinador con el id " + idCoordinador);
            }
            coordinador = getCoordinador(resultSet);
        } catch(SQLException ex) {
            LOGGER.error(CoordinadorDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return coordinador;
        }
    }

    @Override
    public Coordinador getCoordinadorWithUsuario(ResultSet resultSet) {
        int idCoordinador = 0;
        String nombreCoordinador = "";
        String apellidoPaternoCoordinador = "";
        String apellidoMaternoCoordinador = "";
        int idProgramaEducativo = 0;
        int idUsuario = 0;
        try {
            idCoordinador = resultSet.getInt("id");
            nombreCoordinador = resultSet.getString("nombre");
            apellidoPaternoCoordinador = resultSet.getString("apellidoPaterno");
            apellidoMaternoCoordinador = resultSet.getString("apellidoMaterno");
            idProgramaEducativo = resultSet.getInt("idProgramaEducativo");
            idUsuario = resultSet.getInt("idUsuario");
        } catch(SQLException ex) {
            LOGGER.error(CoordinadorDAO.class.getName(),ex);
        }
        Coordinador coordinador = new Coordinador(idCoordinador,nombreCoordinador,apellidoPaternoCoordinador,apellidoMaternoCoordinador,idProgramaEducativo,idUsuario);
        return coordinador;
    }

    @Override
    public boolean addCoordinador(Coordinador coordinador) {
        PersonaDAO personaDao = new PersonaDAO();
        Persona personaCoordinador = new Persona(coordinador.getNombre(),coordinador.getApellidoPaterno(),coordinador.getApellidoMaterno());
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean result = false;
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO coordinador (idProgramaEducativo,idPersona,idUsuario) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, coordinador.getIdProgramaEducativo());
            statement.setInt(2, personaDao.addPersonaReturnId(personaCoordinador));
            statement.setInt(3, coordinador.getIdUsuario());
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: El coordinador no se ha agregado");
            }
            result = true;
        } catch(SQLException ex) {
            LOGGER.error(CoordinadorDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return result;
        }
    }

    @Override
    public boolean deleteCoordinadorById(int idCoordinador) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean result = false;
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "DELETE FROM coordinador WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCoordinador);
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: No se ha eliminado el coordinador con el id " + idCoordinador);
            }
            result = true;
        } catch(SQLException ex) {
            LOGGER.error(CoordinadorDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return result;
        }
    }
}