package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.Persona;

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
    public List<Persona> findTutoresAcademicosByName(String searchName) {
        List<Persona> tutores = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT TA.idTutorAcademico, P.* FROM TutorAcademico TA LEFT JOIN Persona P ON P.idPersona = TA.idPersona WHERE CONCAT(nombre, \" \", apellidoPaterno, \" \", apellidoMaterno) LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,"%" + searchName + "%");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == false) {
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
    public Persona findTutorAcademicoById(int idTutorAcademico) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT TA.idTutorAcademico, P.* FROM TutorAcademico TA LEFT JOIN Persona P ON P.idPersona = TA.idPersona WHERE idTutorAcademico = ?";
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
            if (personaDao.addPersona(tutorAcademico)) {
                String query = "INSERT INTO TutorAcademico (idPersona) VALUES (?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, personaDao.findIdPersona(tutorAcademico));
                statement.executeQuery();
                return true;
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
            Persona tutorAcademico = new Persona();
            tutorAcademico = findTutorAcademicoById(idTutorAcademico);
            if(tutorAcademico == null) {
                return false;
            }
            String query = "DELETE FROM TutorAcademico WHERE idTutorAcademico = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idTutorAcademico);
            statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(TutorAcademicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public Persona getTutorAcademico(ResultSet resultSet) {
        int idTutorAcademico = 0;
        String nombre = "";
        String apellidoPaterno = "";
        String apellidoMaterno = "";
        String telefono = "";
        String correoInstitucional = "";
        Persona persona = new Persona();
        
        try {
            idTutorAcademico = resultSet.getInt("idTutorAcademico");
            nombre = resultSet.getString("nombre");
            apellidoPaterno = resultSet.getString("apellidoPaterno");
            apellidoMaterno = resultSet.getString("apellidoMaterno");
            telefono = resultSet.getString("telefono");
            correoInstitucional = resultSet.getString("correoInstitucional");
            persona.setIdPersona(idTutorAcademico);
            persona.setNombre(nombre);
            persona.setApellidoPaterno(apellidoPaterno);
            persona.setApellidoMaterno(apellidoMaterno);
            persona.setTelefono(telefono);
            persona.setCorreoInstitucional(correoInstitucional);
        } catch (SQLException ex) {
            Logger.getLogger(TutorAcademicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return persona;
    }
}