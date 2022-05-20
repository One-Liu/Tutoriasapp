package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.TutorAcademico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// author @liu
public class TutorAcademicoDAO implements ITutorAcademicoDAO {
    private final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(PersonaDAO.class);

    @Override
    public ArrayList<TutorAcademico> findTutoresAcademicosByName(String searchName) {
        ArrayList<TutorAcademico> tutores = new ArrayList<>();
        String query = 
        "SELECT TA.idTutorAcademico, P.* " +
        "FROM TutorAcademico TA INNER JOIN Persona P ON P.idPersona = TA.idPersona " +
        "WHERE CONCAT(nombre, \" \", apellidoPaterno, \" \", apellidoMaterno) LIKE ?";
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,"%" + searchName + "%");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == false) {
                throw new SQLException("No se han encontrado tutores academicos con el nombre " + searchName);
            } else {
                do {
                    tutores.add(getTutorAcademico(resultSet));
                } while (resultSet.next());
            }
        } catch (SQLException ex) {
            LOGGER.warn(TutorAcademicoDAO.class.getName(), ex);
        }
        return tutores;
    }

    @Override
    public TutorAcademico findTutorAcademicoById(int idTutorAcademico) {
        TutorAcademico tutorAcademico = new TutorAcademico();
        String query = 
        "SELECT TA.idTutorAcademico, P.* " +
        "FROM TutorAcademico TA LEFT JOIN Persona P ON P.idPersona = TA.idPersona " +
        "WHERE idTutorAcademico = ?";
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idTutorAcademico);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == false) {
                throw new SQLException("No se ha encontrado el tutor academico con el id " + idTutorAcademico);
            }
            tutorAcademico = getTutorAcademico(resultSet);
        } catch (SQLException ex) {
            LOGGER.warn(TutorAcademicoDAO.class.getName(), ex);
        }
        return tutorAcademico;
    }

    private TutorAcademico getTutorAcademico(ResultSet resultSet) {
        int idTutorAcademico = 0;
        String nombre = "";
        String apellidoPaterno = "";
        String apellidoMaterno = "";
        try {
            idTutorAcademico = resultSet.getInt("idTutorAcademico");
            nombre = resultSet.getString("nombre");
            apellidoPaterno = resultSet.getString("apellidoPaterno");
            apellidoMaterno = resultSet.getString("apellidoMaterno");
        } catch (SQLException ex) {
            LOGGER.warn(TutorAcademicoDAO.class.getName(), ex);
        }
        Persona personaCoordinador = new Persona(nombre,apellidoPaterno,apellidoMaterno);
        TutorAcademico tutorAcademico = new TutorAcademico(idTutorAcademico,personaCoordinador);
        return tutorAcademico;
    }

    @Override
    public boolean addTutorAcademico(TutorAcademico tutorAcademico) {
        boolean result = false;
        PersonaDAO personaDAO = new PersonaDAO();
        Persona personaTutorAcademico = new Persona(tutorAcademico.getNombre(), tutorAcademico.getApellidoPaterno(), tutorAcademico.getApellidoMaterno());
        String query = "INSERT INTO tutor_academico(idPersona,idUsuario) VALUES(?,?)";
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, tutorAcademico.getIdProgramaEducativo());
            statement.setInt(2, personaDAO.addPersonaReturnId(personaTutorAcademico));
            statement.setInt(3, tutorAcademico.getUsuario().getId());
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: El coordinador no se ha agregado");
            }
            result = true;
        } catch (SQLException ex) {
            LOGGER.warn(TutorAcademicoDAO.class.getName(), ex);
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return result;
    }

    @Override
    public boolean deleteTutorAcademicoById(int idTutorAcademico) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean result = false;
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "DELETE FROM tutor_academico WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idTutorAcademico);
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: No se ha eliminado el tutor académico con el id " + idTutorAcademico);
            }
            result = true;
        } catch (SQLException ex) {
            LOGGER.warn(TutorAcademicoDAO.class.getName(), ex);
        }finally {
            dataBaseConnection.cerrarConexion();
            return result;
        }
    }
}