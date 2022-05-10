package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.TutorAcademico;

// author @liu
public class TutorAcademicoDAO implements ITutorAcademicoDAO {

    private final Logger LOGGER = Logger.getLogger(TutorAcademicoDAO.class);

    @Override
    public ArrayList<TutorAcademico> findTutoresAcademicosByName(String searchName) {
        ArrayList<TutorAcademico> tutores = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT TA.id, TA.idPersona, P.nombre, P.apellidoPaterno, P.apellidoMaterno, TA.idUsuario FROM tutor_academico TA INNER JOIN persona P ON P.id = TA.idPersona WHERE CONCAT(P.nombre,\" \",P.apellidoPaterno,\" \",P.apellidoMaterno) LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + searchName + "%");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("No se han encontrado tutores académicos con el nombre " + searchName);
            } else {
                do {
                    tutores.add(getTutorAcademico(resultSet));
                } while (resultSet.next());
            }
        } catch (SQLException ex) {
            LOGGER.error(TutorAcademicoDAO.class.getName(), ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return tutores;
        }
    }

    @Override
    public TutorAcademico findTutorAcademicoById(int idTutorAcademico) {
        TutorAcademico tutorAcademico = new TutorAcademico();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT TA.id, TA.idPersona, P.nombre, P.apellidoPaterno, P.apellidoMaterno, TA.idUsuario FROM tutor_academico TA INNER JOIN persona P ON P.id = TA.idPersona WHERE TA.id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idTutorAcademico);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == false) {
                throw new SQLException("No se ha encontrado el tutor académico con el id " + idTutorAcademico);
            }
            tutorAcademico = getTutorAcademico(resultSet);
        } catch (SQLException ex) {
            LOGGER.error(TutorAcademicoDAO.class.getName(), ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return tutorAcademico;
        }
    }

    @Override
    public TutorAcademico getTutorAcademico(ResultSet resultSet) {
        int idTutorAcademico = 0;
        int idPersonaTutorAcademico = 0;
        String nombreTutorAcademico = "";
        String apellidoPaternoTutorAcademico = "";
        String apellidoMaternoTutorAcademico = "";
        int idUsuario = 0;
        try {
            idTutorAcademico = resultSet.getInt("idTutorAcademico");
            idPersonaTutorAcademico = resultSet.getInt("idPersona");
            nombreTutorAcademico = resultSet.getString("nombre");
            apellidoPaternoTutorAcademico = resultSet.getString("apellidoPaterno");
            apellidoMaternoTutorAcademico = resultSet.getString("apellidoMaterno");
            idUsuario = resultSet.getInt("idUsuario");
        } catch (SQLException ex) {
            LOGGER.error(TutorAcademicoDAO.class.getName(), ex);
        }
        Persona personaTutorAcademico = new Persona(idPersonaTutorAcademico,nombreTutorAcademico,apellidoPaternoTutorAcademico,apellidoMaternoTutorAcademico);
        TutorAcademico tutorAcademico = new TutorAcademico(idTutorAcademico,personaTutorAcademico,idUsuario);
        return tutorAcademico;
    }

    @Override
    public boolean addTutorAcademico(Persona tutorAcademico) {
        PersonaDAO personaDao = new PersonaDAO();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean result = false;
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO tutor_academico (idPersona,idUsuario) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, personaDao.addPersonaReturnId(tutorAcademico));
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: El tutor académico no se ha agregado");
            }
            result = true;
        } catch (SQLException ex) {
            LOGGER.error(TutorAcademicoDAO.class.getName(), ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return result;
        }

    }

    @Override
    public boolean deleteTutorAcademicoById(int idTutorAcademico) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean result = false;
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "DELETE FROM sesion_de_tutoria_academica WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idTutorAcademico);
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: No se ha eliminado el tutor académico con el id " + idTutorAcademico);
            }
            result = true;
        } catch (SQLException ex) {
            LOGGER.error(TutorAcademicoDAO.class.getName(), ex);
        }finally {
            dataBaseConnection.cerrarConexion();
            return result;
        }
    }
}
