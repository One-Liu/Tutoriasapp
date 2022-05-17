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


// author @liu

public class TutorAcademicoDAO implements ITutorAcademicoDAO {
    private final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(TutorAcademicoDAO.class);

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
            LOG.warn(TutorAcademicoDAO.class.getName(), ex);
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
            LOG.warn(TutorAcademicoDAO.class.getName(), ex);
        }
        return null;
    }
    
    @Override
    public boolean addTutorAcademico(TutorAcademico tutorAcademico) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean bandera = false;
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO tutor_academico(idPersona,idUsuario) VALUES(?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,tutorAcademico.getIdPersona());
            statement.setInt(2, tutorAcademico.getUsuario().getId());
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0) {
                throw new SQLException("ERROR: El profesor no se ha agregado");
            }else {
                bandera = true;
            }
        } catch (SQLException ex) {
            LOG.warn(TutorAcademicoDAO.class.getName(), ex);
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return bandera;
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
            statement.executeUpdate();
        } catch (SQLException ex) {
            LOG.warn(TutorAcademicoDAO.class.getName(), ex);
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
            persona.setIdPersona(idTutorAcademico);
            persona.setNombre(nombre);
            persona.setApellidoPaterno(apellidoPaterno);
            persona.setApellidoMaterno(apellidoMaterno);
        } catch (SQLException ex) {
            LOG.warn(TutorAcademicoDAO.class.getName(), ex);
        }
        return persona;
    }
}
