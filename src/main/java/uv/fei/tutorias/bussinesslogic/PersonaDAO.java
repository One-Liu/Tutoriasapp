package uv.fei.tutorias.bussinesslogic;

import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PersonaDAO implements IPersonaDAO {

    private final Logger log = Logger.getLogger(PersonaDAO.class);
    public void runMe(String parameter){

        if(log.isDebugEnabled()){
            log.debug("This is debug : " + parameter);
        }

        if(log.isInfoEnabled()){
            log.info("This is info : " + parameter);
        }

        log.warn("This is warn : " + parameter);
        log.error("This is error : " + parameter);
        log.fatal("This is fatal : " + parameter);

    }

    @Override
    public List<Persona> findPersonsByName(String searchName) {
        ArrayList<Persona> personas = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "Select * from persona where nombre like ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + searchName + "%");
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new SQLException("Persona not found");
            } else {
                int idPersona = 0;
                String nombre = "";
                String apellidoPaterno = "";
                String apellidoMaterno = "";
                String telefono = "";
                String correoInstitucional = "";
                String correoPersonal = "";
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
//            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return personas;
    }
    @Override
    public Persona findPersonById(int searchId){
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
//            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return persona;

    }

    @Override
    public boolean addPerson(Persona persona) {

        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO persona(nombre, apellidoMaterno, apellidoPaterno, correoInstitucional, correoPersonal) VALUES(?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, persona.getNombre());
            statement.setString(2, persona.getApellidoMaterno());
            statement.setString(3, persona.getApellidoPaterno());
            statement.setString(4,persona.getCorreoInstitucional());
            statement.setString(5,persona.getCorreoPersonal());
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0) {
                throw new SQLException("ERROR: La persona no se ha agregado");
            }
        } catch (SQLException ex) {
//            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return true;
    }

    @Override
    public boolean deletePersonById(int searchId) {
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
//            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return true;
    }
    
    // author @liu
    public int addPersonaReturnId(Persona persona) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO Persona (nombre, apellidoPaterno, apellidoMaterno, telefono, correoInstitucional) VALUES (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, persona.getNombre());
            statement.setString(2, persona.getApellidoPaterno());
            statement.setString(3, persona.getApellidoMaterno());
            statement.setString(4,persona.getTelefono());
            statement.setString(5,persona.getCorreoInstitucional());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                int idPersona = resultSet.getInt(1);
                System.out.println("Persona agregada");
                return idPersona;
            } else {
                throw new SQLException("ERROR: La persona no se ha agregado");
            }
        } catch (SQLException ex) {
//            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return 0;
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
//            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return idPersona;
    }
}

