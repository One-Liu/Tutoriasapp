package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.TutorAcademico;

// author @liu
public class TutorAcademicoDAO implements ITutorAcademicoDAO {
    
    static final Logger LOGGER = Logger.getLogger(TutorAcademicoDAO.class);
    
    @Override
    public List<TutorAcademico> findTutoresAcademicosByName(String searchName) {
        List<TutorAcademico> tutores = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT TA.idTutorAcademico, TA.idPersona AS idPersonaTutorAcademico, " + 
                "P.nombre AS nombreTutorAcademico, P.apellidoPaterno AS apellidoPaternoTutorAcademico, P.apellidoMaterno AS apellidoMaternoTutorAcademico, " +
                "P.correoInstitucional AS correoInstitucionalTutorAcademico, P.correoPersonal AS correoPersonalTutorAcademico " +
                "FROM TutorAcademico TA " +
                "LEFT JOIN Persona P ON P.idPersona = TA.idPersona " +
                "WHERE CONCAT(nombre,\" \", apellidoPaterno,\" \",apellidoMaterno) LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + searchName + "%");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("Tutor Academico not found");
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
            String query = "SELECT TA.idTutorAcademico, TA.idPersona AS idPersonaTutorAcademico, " + 
                "P.nombre AS nombreTutorAcademico, P.apellidoPaterno AS apellidoPaternoTutorAcademico, P.apellidoMaterno AS apellidoMaternoTutorAcademico, " +
                "P.correoInstitucional AS correoInstitucionalTutorAcademico, P.correoPersonal AS correoPersonalTutorAcademico " +
                "FROM TutorAcademico TA " +
                "LEFT JOIN Persona P ON P.idPersona = TA.idPersona " +
                "WHERE idTutorAcademico = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idTutorAcademico);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == false) {
                throw new SQLException("Tutor Academico not found");
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
        int idPersona = 0;
        String nombre = "";
        String apellidoPaterno = "";
        String apellidoMaterno = "";
        String correoInstitucional = "";
        String correoPersonal = "";
        try {
            idTutorAcademico = resultSet.getInt("idTutorAcademico");
            idPersona = resultSet.getInt("idPersonaTutorAcademico");
            nombre = resultSet.getString("nombreTutorAcademico");
            apellidoPaterno = resultSet.getString("apellidoPaternoTutorAcademico");
            apellidoMaterno = resultSet.getString("apellidoMaternoTutorAcademico");
            correoInstitucional = resultSet.getString("correoInstitucionalTutorAcademico");
            correoPersonal = resultSet.getString("correoPersonalTutorAcademico");
        } catch (SQLException ex) {
            LOGGER.error(TutorAcademicoDAO.class.getName(), ex);
        }
        Persona personaTutorAcademico = new Persona(idPersona,nombre,apellidoPaterno,apellidoMaterno,correoInstitucional,correoPersonal);
        TutorAcademico tutorAcademico = new TutorAcademico(idTutorAcademico,personaTutorAcademico);
        return tutorAcademico;
    }
    
    @Override
    public boolean addTutorAcademico(Persona tutorAcademico) {
        PersonaDAO personaDao = new PersonaDAO();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean result = false;
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO TutorAcademico (idPersona) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, personaDao.addPersonaReturnId(tutorAcademico));
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: Tutor Academico not added");
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
            String query = "DELETE FROM TutorAcademico WHERE idTutorAcademico = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idTutorAcademico);
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: Tutor Academico not deleted");
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