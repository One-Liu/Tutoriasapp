package uv.fei.tutorias.bussinesslogic;

import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class PersonaDAO implements IPersonaDAO {

    private final Logger LOG = Logger.getLogger(PersonaDAO.class);

    @Override
    public Persona obtenerPersonaPorId(int searchId) throws SQLException{
        ConexionBD dataBaseConnection = new ConexionBD();
        Persona persona;
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            String query = "Select * from persona where id like ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, searchId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                persona = new Persona();
            } else {
                    persona = getPersona(resultSet);
            }
        }catch (SQLException ex){
            LOG.warn(getClass().getName(), ex);
            throw ex;
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return persona;
    }



    @Override
    public boolean eliminarPersonaPorId(int searchId) throws SQLException {
        boolean bandera = false;
        ConexionBD dataBaseConnection = new ConexionBD();
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            String query = "DELETE FROM persona WHERE (id = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, searchId);
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate != 0) {
                bandera = true;
            }
        }catch (SQLException ex){
            LOG.warn(getClass().getName(), ex);
            throw ex;
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return bandera;
    }
    @Override
    public int agregarPersona(Persona persona) throws SQLException {
        ConexionBD dataBaseConnection = new ConexionBD();
        int id;
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            String query = "INSERT INTO persona(nombre, apellidoPaterno, apellidoMaterno, idProgramaEducativo) VALUES(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, persona.getNombre());
            statement.setString(2, persona.getApellidoPaterno());
            statement.setString(3, persona.getApellidoMaterno());
            statement.setInt(4, persona.getIdProgramaEducativo());
            int executeUpdate = statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (executeUpdate == 0) {
                id = -1;
            }else {
                resultSet.next();
                id=resultSet.getInt(1);
            }
        }catch (SQLException ex){
            LOG.warn(getClass().getName(), ex);
            throw ex;
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return id;
    }

   private Persona getPersona(ResultSet resultSet)throws SQLException{
        Persona persona = new Persona();
       int idPersona;
       String nombre;
       String apellidoPaterno;
       String apellidoMaterno;
       int idProgramaEducativo;

       idPersona = resultSet.getInt("id");
       nombre = resultSet.getString("nombre");
       apellidoPaterno = resultSet.getString("apellidoPaterno");
       apellidoMaterno = resultSet.getString("apellidoMaterno");
       idProgramaEducativo = resultSet.getInt("idProgramaEducativo");

       persona.setIdPersona(idPersona);
       persona.setNombre(nombre);
       persona.setApellidoPaterno(apellidoPaterno);
       persona.setApellidoMaterno(apellidoMaterno);
       persona.setIdProgramaEducativo(idProgramaEducativo);

       return persona;
   }

}

