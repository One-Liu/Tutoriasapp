package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.TutorAcademico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// author @liu
public class TutorAcademicoDAO implements ITutorAcademicoDAO {
    @Override
    public List<TutorAcademico> findTutoresAcademicosByName(String searchName) {
        List<TutorAcademico> tutores = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT P.nombre AS nombreTutorAcademico, P.apellidoPaterno AS apellidoPaternoTutorAcademico, P.apellidoMaterno AS apellidoMaternoTutorAcademico,\n" +
                "P.correoInstitucional AS correoInstitucionalTutorAcademico, P.correoPersonal AS correoPersonalTutorAcademico\n" +
                "FROM TutorAcademico TA \n" +
                "LEFT JOIN Persona P ON P.idPersona = TA.idPersona \n" +
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
            Logger.getLogger(TutorAcademicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tutores;
    }
    
    @Override
    public TutorAcademico findTutorAcademicoById(int idTutorAcademico) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT P.nombre AS nombreTutorAcademico, P.apellidoPaterno AS apellidoPaternoTutorAcademico, P.apellidoMaterno AS apellidoMaternoTutorAcademico,\n" +
                "P.correoInstitucional AS correoInstitucionalTutorAcademico, P.correoPersonal AS correoPersonalTutorAcademico\n" +
                "FROM TutorAcademico TA \n" +
                "LEFT JOIN Persona P ON P.idPersona = TA.idPersona \n" +
                "WHERE idTutorAcademico = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idTutorAcademico);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == false) {
                throw new SQLException("Tutor Academico not found");
            }
            return getTutorAcademico(resultSet);
        } catch (SQLException ex) {
            Logger.getLogger(TutorAcademicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public boolean addTutorAcademico(Persona tutorAcademico) {
        PersonaDAO personaDao = new PersonaDAO();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO TutorAcademico (idPersona) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, personaDao.addPersonaReturnId(tutorAcademico));
            int affectedRows = statement.executeUpdate();
            if(affectedRows != 0) {
                System.out.println("Tutor Academico added");
                return true;
            } else {
                throw new SQLException("ERROR: Tutor Academico not added");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TutorAcademicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public boolean deleteTutorAcademicoById(int idTutorAcademico) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "DELETE FROM TutorAcademico WHERE idTutorAcademico = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idTutorAcademico);
            int affectedRows = statement.executeUpdate();
            if(affectedRows != 0) {
                System.out.println("Tutor Academico deleted");
                return true;
            } else {
                throw new SQLException("ERROR: Tutor Academico not deleted");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TutorAcademicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public TutorAcademico getTutorAcademico(ResultSet resultSet) {
        TutorAcademico tutorAcademico = new TutorAcademico();
        Persona personaTutorAcademico = new Persona();
        String nombre = "";
        String apellidoPaterno = "";
        String apellidoMaterno = "";
        String correoInstitucional = "";
        String correoPersonal = "";
        try {
            nombre = resultSet.getString("nombreTutorAcademico");
            personaTutorAcademico.setNombre(nombre);
            apellidoPaterno = resultSet.getString("apellidoPaternoTutorAcademico");
            personaTutorAcademico.setApellidoPaterno(apellidoPaterno);
            apellidoMaterno = resultSet.getString("apellidoMaternoTutorAcademico");
            personaTutorAcademico.setApellidoMaterno(apellidoMaterno);
            correoInstitucional = resultSet.getString("correoInstitucionalTutorAcademico");
            personaTutorAcademico.setCorreoInstitucional(correoInstitucional);
            correoPersonal = resultSet.getString("correoPersonalTutorAcademico");
            personaTutorAcademico.setCorreoPersonal(correoPersonal);
            tutorAcademico.setPersona(personaTutorAcademico);
        } catch (SQLException ex) {
            Logger.getLogger(TutorAcademicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tutorAcademico;
    }
}