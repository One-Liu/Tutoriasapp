package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// author @liu

public class EstudianteDAO implements IEstudianteDAO {
    @Override
    public List<Persona> findEstudianteByName(String searchName) {
        List<Persona> estudiantes = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT E.idEstudiante, P.* FROM Estudiante E LEFT JOIN Persona P ON P.idPersona = E.idPersona WHERE CONCAT(nombre, \" \", apellidoPaterno, \" \", apellidoMaterno) LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, searchName);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("Estudiante not found");
            } else {
                do {
                    estudiantes.add(getEstudiante(resultSet));
                }while(resultSet.next());
            }
        } catch(SQLException ex) {
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estudiantes;
    }

    @Override
    public Persona findEstudianteById(int idEstudiante) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT E.idEstudiante, P.* FROM Estudiante E LEFT JOIN Persona P ON P.idPersona = E.idPersona WHERE idEstudiante = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idEstudiante);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("Estudiante not found");
            }
            return getEstudiante(resultSet);
        } catch(SQLException ex) {
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean addEstudiante(Persona estudiante) {
        PersonaDAO personaDao = new PersonaDAO();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO Estudiante (idPersona) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, personaDao.addPersonaReturnId(estudiante));
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: Estudiante not added");
            } else {
                System.out.println("Estudiante added");
                return true;
            }
        } catch(SQLException ex) {
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteEstudianteById(int idEstudiante) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "DELETE FROM Estudiante WHERE idEstudiante = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idEstudiante);
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: Estudiante not deleted");
            } else {
                System.out.println("Estudiante deleted");
                return true;
            }
        } catch(SQLException ex) {
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Persona getEstudiante(ResultSet resultSet) {
        int idEstudiante = 0;
        String nombre = "";
        String apellidoPaterno = "";
        String apellidoMaterno = "";
        String telefono = "";
        String correoInstitucional = "";
        Persona estudiante = new Persona();
        try {
            idEstudiante = resultSet.getInt("idEstudiante");
            nombre = resultSet.getString("nombre");
            apellidoPaterno = resultSet.getString("apellidoPaterno");
            apellidoMaterno = resultSet.getString("apellidoMaterno");
            telefono = resultSet.getString("telefono");
            correoInstitucional = resultSet.getString("correoInstitucional");
        } catch(SQLException ex) {
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estudiante;
    }
}
