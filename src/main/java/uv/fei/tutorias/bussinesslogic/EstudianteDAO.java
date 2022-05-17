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
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT E.id, E.matricula, E.idTutorAcademico, E.idProgramaEducativo, P.nombre, P.apellidoPaterno, P.apellidoMaterno FROM estudiante E LEFT JOIN persona P ON P.id = E.idPersona WHERE CONCAT(P.nombre,\" \",P.apellidoPaterno,\" \",P.apellidoMaterno) LIKE ?";
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
            LOGGER.error(EstudianteDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return estudiantes;
        }
    }

    @Override
    public Estudiante findEstudianteById(int idEstudiante) {
        Estudiante estudiante = new Estudiante();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT E.id, E.matricula, E.idTutorAcademico, E.idProgramaEducativo, P.nombre, P.apellidoPaterno, P.apellidoMaterno FROM estudiante E LEFT JOIN persona P ON P.id = E.idPersona WHERE E.id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idEstudiante);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("No se ha encontrado al estudiante con el id " + idEstudiante);
            }
            estudiante = getEstudiante(resultSet);
        } catch(SQLException ex) {
            LOGGER.error(EstudianteDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return estudiante;
        }
    }

    @Override
        public Estudiante getEstudiante(ResultSet resultSet) {
        int idEstudiante = 0;
        String matricula = "";
        String nombreEstudiante = "";
        String apellidoPaternoEstudiante = "";
        String apellidoMaternoEstudiante = "";
        int idProgramaEducativo = 0;
        int idTutorAcademico = 0;
        try {
            idEstudiante = resultSet.getInt("id");
            matricula = resultSet.getString("matricula");
            nombreEstudiante = resultSet.getString("nombre");
            apellidoPaternoEstudiante = resultSet.getString("apellidoPaterno");
            apellidoMaternoEstudiante = resultSet.getString("apellidoMaterno");
            idTutorAcademico = resultSet.getInt("idTutorAcademico");
            idProgramaEducativo = resultSet.getInt("idProgramaEducativo");
        } catch(SQLException ex) {
            LOGGER.error(EstudianteDAO.class.getName(),ex);
        }
        Estudiante estudiante = new Estudiante(idEstudiante,matricula,nombreEstudiante,apellidoPaternoEstudiante,apellidoMaternoEstudiante,idTutorAcademico,idProgramaEducativo);
        return estudiante;
    }

    @Override
    public boolean addEstudiante(Estudiante estudiante) {
        PersonaDAO personaDao = new PersonaDAO();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean result = false;
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO estudiante (matricula, idTutorAcademico, idProgramaEducativo, idPersona) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, estudiante.getMatricula());
            statement.setInt(2, estudiante.getIdTutorAcademico());
            statement.setInt(3, estudiante.getIdProgramaEducativo());
            statement.setInt(4, personaDao.addPersonaReturnId(estudiante.getPersona()));
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: El estudiante no se ha agregado");
            }
            result = true;
        } catch(SQLException ex) {
            LOGGER.error(EstudianteDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return result;
        }
    }

    @Override
    public boolean deleteEstudianteById(int idEstudiante) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean result = false;
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "DELETE FROM estudiante WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idEstudiante);
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: No se ha eliminado al estudiante con el id " + idEstudiante);
            }
            result = true;
        } catch(SQLException ex) {
            LOGGER.error(EstudianteDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return result;
        }
    }
}