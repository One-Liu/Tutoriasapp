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
                String correoInstitucional = "";
                String correoPersonal = "";
                do {
                    idPersona = resultSet.getInt("idPersona");
                    nombre = resultSet.getString("nombre");
                    apellidoPaterno = resultSet.getString("apellidoPaterno");
                    apellidoMaterno = resultSet.getString("apellidoMaterno");
                    correoInstitucional = resultSet.getString("correoInstitucional");
                    correoPersonal = resultSet.getString("correoPersonal");
                    Persona persona = new Persona();
                    persona.setIdPersona(idPersona);
                    persona.setNombre(nombre);
                    persona.setApellidoPaterno(apellidoPaterno);
                    persona.setApellidoMaterno(apellidoMaterno);
                    persona.setCorreoInstitucional(correoInstitucional);
                    persona.setCorreoPersonal(correoPersonal);
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
                String correoInstitucional = "";
                String correoPersonal = "";
                do {
                    idPersona = resultSet.getInt("idPersona");
                    nombre = resultSet.getString("nombre");
                    apellidoPaterno = resultSet.getString("apellidoPaterno");
                    apellidoMaterno = resultSet.getString("apellidoMaterno");
                    correoInstitucional = resultSet.getString("correoInstitucional");
                    correoPersonal = resultSet.getString("correoPersonal");

                    persona.setIdPersona(idPersona);
                    persona.setNombre(nombre);
                    persona.setApellidoPaterno(apellidoPaterno);
                    persona.setApellidoMaterno(apellidoMaterno);
                    persona.setCorreoInstitucional(correoInstitucional);
                    persona.setCorreoPersonal(correoPersonal);
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

            String query = "INSERT INTO Persona (nombre, apellidoPaterno, apellidoMaterno, correoInstitucional, correoPersonal) VALUES (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, persona.getNombre());
            statement.setString(2, persona.getApellidoPaterno());
            statement.setString(3, persona.getApellidoMaterno());
            statement.setString(4,persona.getCorreoInstitucional());
            statement.setString(5,persona.getCorreoPersonal());
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
    public int addPersonaReturnId(Persona persona) {
        // Para evitar duplicidad de información (registrar 2 veces a una misma persona)
        int idPersona = findIdPersonaByGivenData(persona);
        if (idPersona == -1) {
            DataBaseConnection dataBaseConnection = new DataBaseConnection();
            try (Connection connection = dataBaseConnection.getConnection()) {
                String query = "INSERT INTO Persona (nombre, apellidoPaterno, apellidoMaterno, correoInstitucional, correoPersonal) VALUES (?,?,?,?,?)";
                PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, persona.getNombre());
                statement.setString(2, persona.getApellidoPaterno());
                statement.setString(3, persona.getApellidoMaterno());
                statement.setString(4, persona.getCorreoInstitucional());
                statement.setString(5, persona.getCorreoPersonal());
                statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
                if(resultSet.next()) {
                    idPersona = resultSet.getInt(1);
                    System.out.println("Persona agregada");
                } else {
                    throw new SQLException("ERROR: La persona no se ha agregado");
                }
            } catch (SQLException ex) {
                Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return idPersona;
    }
    
    // author @liu
    public int findIdPersonaByGivenData(Persona persona) {
        int idPersona = -1;
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT (idPersona) FROM Persona WHERE CONCAT(nombre, \" \", apellidoPaterno, \" \", apellidoMaterno) = ? && correoInstitucional = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, persona.getNombre() + " " + persona.getApellidoPaterno() + " " + persona.getApellidoMaterno());
            statement.setString(2, persona.getCorreoInstitucional());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() != false) {
                idPersona = resultSet.getInt("idPersona");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idPersona;
    }
}