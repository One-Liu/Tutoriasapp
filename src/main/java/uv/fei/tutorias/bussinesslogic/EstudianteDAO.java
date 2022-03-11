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

//author @L3M-ON

public class EstudianteDAO implements IEstudianteDAO{
    @Override
    public List<Persona> findEstudianteByName(String searchName) {
        List<Persona> estudiante = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT * FROM Estudiante where nombre like ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,"%" + searchName + "%");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == false) {
                throw new SQLException("Estudiante not found");
            } else {
                int idEstudiante = 0;
                String nombre = "";
                String apellidoPaterno = "";
                String apellidoMaterno = "";
                int edad = 0;
                String telefono = "";
                String correoInstitucional = "";
                do {
                    idEstudiante = resultSet.getInt("idEstudiante");
                    nombre = resultSet.getString("nombre");
                    apellidoPaterno = resultSet.getString("apellidoPaterno");
                    apellidoMaterno = resultSet.getString("apellidoMaterno");
                    edad = resultSet.getInt("edad");
                    telefono = resultSet.getString("telefono");
                    correoInstitucional = resultSet.getString("correoInstitucional");
                    Persona estudiante = new Persona();
                    estudiante.setIdEstudiante(idEstudiante);
                    estudiante.setNombre(nombre);
                    estudiante.setApellidoPaterno(apellidoPaterno);
                    estudiante.setApellidoMaterno(apellidoMaterno);
                    estudiante.setEdad(edad);
                    estudiante.setTelefono(telefono);
                    estudiante.setCorreoInstitucional(correoInstitucional);
                    estudiante.add(estudiante);
                } while (resultSet.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estudiante;
    }

    @Override
    public Persona findEstudianteById(int idEstudiante) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT TA.idEstudiante, P.* FROM Estudiante TA LEFT JOIN Persona P ON P.idPersona = TA.idPersona WHERE idEstudiante = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idEstudiante);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == false) {
                throw new SQLException("Estudiante not found");
            }
            return getEstudiante(resultSet);
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean addEstudiante(Persona estudiante) {
        PersonaDAO personaDao = new PersonaDAO();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            if (personaDao.addPersona(estudiante)) {
                String query = "INSERT INTO Estudiante (idPersona) VALUES (?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, personaDao.findIdPersona(estudiante));
                statement.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteEstudienteById(int idEstudiante) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            Persona estudiante = new Persona();
            estudiante= findEstudianteById(idEstudiante);
            if(estudiante == null) {
                return false;
            }
            String query = "DELETE FROM Estudiante WHERE idEstudiante = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idEstudiante);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }


}
