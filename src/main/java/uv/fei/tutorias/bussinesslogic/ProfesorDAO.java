package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.Profesor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;



public class ProfesorDAO implements IProfesorDAO {
    private final Logger LOG = Logger.getLogger(ProfesorDAO.class);

    @Override
    public boolean addProfesor(Profesor profesor) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean bandera = false;
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO profesor(id, idPersona) VALUES(?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,profesor.getId());
            statement.setInt(2, profesor.getIdPersona());
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0) {
                throw new SQLException("ERROR: El profesor no se ha agregado");
            }else {
                bandera = true;
            }
        } catch (SQLException ex) {
            LOG.warn(PersonaDAO.class.getName(), ex);
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return bandera;
    }

    public boolean addProfesorandPersona(Profesor profesor){
        boolean bandera = false;
        PersonaDAO personaDAO = new PersonaDAO();
        int idPersona;
        idPersona = personaDAO.addPersonaReturnId(profesor);
        if (idPersona != -1){
            profesor.setIdPersona(idPersona);
            addProfesor(profesor);
            bandera = true;
        }
        return bandera;
    }




    @Override
    public List<Profesor> findProfesoresByName(String searchName) {
        List<Profesor> profesores = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()){
            String query = "select prof.id, per.nombre, per.apellidoPaterno, per.apellidoMaterno from persona per inner join profesor prof on per.id = prof.idPersona where per.nombre LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,"%" + searchName + "%");
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                throw new SQLException("No se ha encontrado al profesor con el nombre " + searchName);
            } else {
                do {
                    profesores.add(getProfesor(resultSet));
                }while (resultSet.next());
            }
        } catch (SQLException e) {
            LOG.warn(PersonaDAO.class.getName(), e);
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return profesores;
    }

    @Override
    public Profesor findProfesorById(int searchId) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Profesor profesor = new Profesor();
        try (Connection connection = dataBaseConnection.getConnection()){
            String query = "select prof.id, per.nombre, per.apellidoPaterno, per.apellidoMaterno from persona per inner join profesor prof on per.id = prof.idPersona where prof.id = (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,searchId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                throw new SQLException("No se ha encontrado al profesor con el nombre " + searchId);
            } else {
               profesor = getProfesor(resultSet);
            }
        } catch (SQLException e) {
            LOG.warn(PersonaDAO.class.getName(), e);
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return profesor;
    }


    @Override
    public boolean deleteProfesorById(int searchId) {
        boolean bandera = false;
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "DELETE FROM profesor WHERE (id = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, searchId);

            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0) {
                throw new SQLException("ERROR: No se ha eliminado ningun profesor con el id " + searchId);
            }else {
                bandera = true;
            }
        } catch (SQLException ex) {
            LOG.warn(PersonaDAO.class.getName(), ex);
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return bandera;
    }


    private Profesor getProfesor(ResultSet resultSet) {
        Profesor profesor = new Profesor();
        int id = 0;
        String nombrePersona = "";
        String apellidoPaternoPersona = "";
        String apellidoMaternoPersona = "";

        try {
            id = resultSet.getInt("id");
            profesor.setId(id);
            nombrePersona = resultSet.getString("nombre");
            profesor.setNombre(nombrePersona);
            apellidoMaternoPersona = resultSet.getString("apellidoMaterno");
            profesor.setApellidoMaterno(apellidoMaternoPersona);
            apellidoPaternoPersona = resultSet.getString("apellidoPaterno");
            profesor.setApellidoPaterno(apellidoPaternoPersona);

        } catch(SQLException ex) {
            LOG.warn(PersonaDAO.class.getName(), ex);
        }
        return profesor;
    }

}
