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

public class PersonaDAO implements IPersonaDAO {

    @Override
    public List<Persona> findPersonasByName(String searchName) {
        ArrayList<Persona> personas = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "Select * from persona where nombre like ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + searchName + "%");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == false) {
                throw new SQLException("Persona not found");
            } else {
                int idPersona = 0;
                String nombre = "";
                String apellidoPaterno = "";
                String apellidoMaterno = "";
                String telefono = "";
                String correoInstitucional = "";
                do {
                    idPersona = resultSet.getInt("idPersona");
                    nombre = resultSet.getString("nombre");
                    apellidoPaterno = resultSet.getString("apellidoPaterno");
                    apellidoMaterno = resultSet.getString("apellidoMaterno");
                    telefono = resultSet.getString("telefono");
                    correoInstitucional = resultSet.getString("correoInstitucional");
                    Persona persona = new Persona();
                    persona.setIdPersona(idPersona);
                    persona.setNombre(nombre);
                    persona.setApellidoPaterno(apellidoPaterno);
                    persona.setApellidoMaterno(apellidoMaterno);
                    persona.setTelefono(telefono);
                    persona.setCorreoInstitucional(correoInstitucional);
                    personas.add(persona);
                } while (resultSet.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return personas;
    }
    
    @Override
    public Persona findPersonaById(int searchId){
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Persona persona = new Persona();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "Select * from persona where idPersona like ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + searchId + "%");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == false) {
                throw new SQLException("Persona not found");
            } else {
                int idPersona = 0;
                String nombre = "";
                String apellidoPaterno = "";
                String apellidoMaterno = "";
                String telefono = "";
                String correoInstitucional = "";
                do {
                    idPersona = resultSet.getInt("idPersona");
                    nombre = resultSet.getString("nombre");
                    apellidoPaterno = resultSet.getString("apellidoPaterno");
                    apellidoMaterno = resultSet.getString("apellidoMaterno");
                    telefono = resultSet.getString("telefono");
                    correoInstitucional = resultSet.getString("correoInstitucional");

                    persona.setIdPersona(idPersona);
                    persona.setNombre(nombre);
                    persona.setApellidoPaterno(apellidoPaterno);
                    persona.setApellidoMaterno(apellidoMaterno);
                    persona.setTelefono(telefono);
                    persona.setCorreoInstitucional(correoInstitucional);
                } while (resultSet.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return persona;
    }
    
    @Override
    public boolean addPersona(Persona persona) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {

            String query = "INSERT INTO Persona (nombre, apellidoPaterno, apellidoMaterno, telefono, correoInstitucional) VALUES (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, persona.getNombre());
            statement.setString(2, persona.getApellidoPaterno());
            statement.setString(3, persona.getApellidoMaterno());
            statement.setString(4,persona.getTelefono());
            statement.setString(5,persona.getCorreoInstitucional());
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0) {
                throw new SQLException("ERROR: La persona no se ha agregado");
            } else {
                System.out.println("Persona agregada");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public boolean eliminarPersonaById(int searchId) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {

            String query = "DELETE FROM persona WHERE (idPersona = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, searchId);

            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0) {
                throw new SQLException("ERROR: No se ha eliminado ninguna tabla");
            } else {
                System.out.println("Persona eliminada satisfactoriamente");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    // author @liu
    public int findIdPersona(Persona persona) {
        int idPersona = 0;
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            // Un correo institucional es único para cada Persona
            String query = "SELECT idPersona FROM Persona WHERE correoInstitucional = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, persona.getCorreoInstitucional());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == false) {
                throw new SQLException("idPersona not found");
            }
            idPersona = resultSet.getInt("idPersona");
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idPersona;
    }
}