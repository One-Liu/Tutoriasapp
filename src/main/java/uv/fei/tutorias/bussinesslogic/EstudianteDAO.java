package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.Estudiante;

// author @liu
public class EstudianteDAO implements IEstudianteDAO {

    private final Logger LOGGER = Logger.getLogger(EstudianteDAO.class);

    @Override
    public ArrayList<Estudiante> findEstudianteByName(String searchName) {
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        String query = 
        "SELECT E.id, E.matricula, E.idTutorAcademico, E.idProgramaEducativo, P.nombre, P.apellidoPaterno, P.apellidoMaterno " +
        "FROM estudiante E LEFT JOIN persona P ON P.id = E.idPersona " +
        "WHERE CONCAT(P.nombre,\" \",P.apellidoPaterno,\" \",P.apellidoMaterno) LIKE ?";
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + searchName + "%");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("No se han encontrado estudiantes con el nombre " + searchName);
            } else {
                do {
                    estudiantes.add(getEstudiante(resultSet));
                }while(resultSet.next());
            }
        } catch(SQLException ex) {
            LOGGER.warn(EstudianteDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return estudiantes;
    }

    @Override
    public Estudiante findEstudianteById(int idEstudiante) {
        Estudiante estudiante = new Estudiante();
        String query = 
        "SELECT E.id, E.matricula, E.idTutorAcademico, E.idProgramaEducativo, P.nombre, P.apellidoPaterno, P.apellidoMaterno " +
        "FROM estudiante E LEFT JOIN persona P ON P.id = E.idPersona " +
        "WHERE E.id = ?";
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idEstudiante);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("No se ha encontrado al estudiante con el id " + idEstudiante);
            }
            estudiante = getEstudiante(resultSet);
        } catch(SQLException ex) {
            LOGGER.warn(EstudianteDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return estudiante;
    }

    private Estudiante getEstudiante(ResultSet resultSet) {
        int idEstudiante = 0;
        String matricula = "";
        String nombre = "";
        String apellidoPaterno = "";
        String apellidoMaterno = "";
        int idProgramaEducativo = 0;
        int idTutorAcademico = 0;
        try {
            idEstudiante = resultSet.getInt("id");
            matricula = resultSet.getString("matricula");
            nombre = resultSet.getString("nombre");
            apellidoPaterno = resultSet.getString("apellidoPaterno");
            apellidoMaterno = resultSet.getString("apellidoMaterno");
            idTutorAcademico = resultSet.getInt("idTutorAcademico");
            idProgramaEducativo = resultSet.getInt("idProgramaEducativo");
        } catch(SQLException ex) {
            LOGGER.warn(EstudianteDAO.class.getName(),ex);
        }
        Persona personaEstudiante = new Persona(nombre,apellidoPaterno,apellidoMaterno);
        Estudiante estudiante = new Estudiante(idEstudiante,matricula,personaEstudiante,idTutorAcademico,idProgramaEducativo);
        return estudiante;
    }

    @Override
    public boolean addEstudiante(Estudiante estudiante) {
        boolean result = false;
        PersonaDAO personaDao = new PersonaDAO();
        Persona personaEstudiante = new Persona(estudiante.getNombre(), estudiante.getApellidoPaterno(), estudiante.getApellidoMaterno());
        String query = "INSERT INTO estudiante (matricula, idTutorAcademico, idProgramaEducativo, idPersona) VALUES (?,?,?,?)";
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, estudiante.getMatricula());
            statement.setInt(2, estudiante.getIdTutorAcademico());
            statement.setInt(3, estudiante.getIdProgramaEducativo());
            statement.setInt(4, personaDao.addPersonaReturnId(personaEstudiante));
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: El estudiante no se ha agregado");
            }
            result = true;
        } catch(SQLException ex) {
            LOGGER.warn(EstudianteDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return result;
    }

    @Override
    public boolean deleteEstudianteById(int idEstudiante) {
        boolean result = false;
        String query = "DELETE FROM estudiante WHERE id = ?";
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idEstudiante);
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: No se ha eliminado al estudiante con el id " + idEstudiante);
            }
            result = true;
        } catch(SQLException ex) {
            LOGGER.warn(EstudianteDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return result;
    }
}