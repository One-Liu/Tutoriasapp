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
    public List<Persona> findPersonaByName(String searchName) {
        ArrayList<Persona> personas = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "Select * from personas where nombre like ?";
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
                int edad = 0;
                String telefono = "";
                String correoInstitucional = "";
                do {
                    idPersona = resultSet.getInt("idPersona");
                    nombre = resultSet.getString("nombre");
                    apellidoPaterno = resultSet.getString("apellidoPaterno");
                    apellidoMaterno = resultSet.getString("apellidoMaterno");
                    edad = resultSet.getInt("apellidoPaterno");
                    telefono = resultSet.getString("telefono");
                    correoInstitucional = resultSet.getString("correoInstitucional");
                    Persona persona = new Persona();
                    persona.setNombre(nombre);
                    persona.setApellidoPaterno(apellidoPaterno);
                    persona.setApellidoMaterno(apellidoMaterno);
                    persona.setEdad(edad);
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


}

