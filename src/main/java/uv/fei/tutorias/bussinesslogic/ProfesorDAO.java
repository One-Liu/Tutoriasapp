package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.Profesor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import uv.fei.tutorias.domain.Persona;

public class ProfesorDAO implements IProfesorDAO {
    private final Logger LOG = Logger.getLogger(ProfesorDAO.class);

    @Override
    public boolean addProfesor(Profesor profesor) throws SQLException {
        ConexionBD dataBaseConnection = new ConexionBD();
        boolean bandera = false;
        String query = "INSERT INTO profesor(id, idPersona) VALUES(?,?)";
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, profesor.getIdProfesor());
            statement.setInt(2, profesor.getIdPersona());
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate != 0) {
                bandera = true;
            }
        } catch (SQLException ex) {
            LOG.warn(getClass().getName(), ex);
            throw ex;
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return bandera;
    }

    public boolean addProfesorandPersona(Persona persona) throws SQLException {
        boolean bandera = false;
        PersonaDAO personaDAO = new PersonaDAO();
        int idPersona;
        idPersona = personaDAO.agregarPersona(persona);
        Profesor profesor = new Profesor();
        if (idPersona != -1){
            profesor.setIdPersona(idPersona);
            addProfesor(profesor);
            bandera = true;
        }
        return bandera;
    }




    @Override
    public List<Profesor> findProfesoresByName(String searchName) throws SQLException {
        List<Profesor> profesores = new ArrayList<>();
        ConexionBD dataBaseConnection = new ConexionBD();
        String query = "select prof.id, per.nombre, per.apellidoPaterno, per.apellidoMaterno, per.idProgramaEducativo from persona per inner join profesor prof on per.id = prof.idPersona where per.nombre LIKE ?";
        try (Connection connection = dataBaseConnection.abrirConexion()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,"%" + searchName + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                profesores.add(getProfesor(resultSet));
            }
        } catch (SQLException ex) {
            LOG.warn(getClass().getName(), ex);
            throw ex;
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return profesores;
    }

    @Override
    public Profesor findProfesorById(int searchId) throws SQLException {
        ConexionBD dataBaseConnection = new ConexionBD();
        Profesor profesor = new Profesor();
        String query = "select prof.id, per.nombre, per.apellidoPaterno, per.apellidoMaterno, per.idProgramaEducativo from persona per inner join profesor prof on per.id = prof.idPersona where prof.id = (?)";
        try (Connection connection = dataBaseConnection.abrirConexion()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,searchId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                profesor = getProfesor(resultSet);
            }
        } catch (SQLException e) {
            LOG.warn(getClass().getName(), e);
            throw e;
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return profesor;
    }


    @Override
    public boolean deleteProfesorById(int searchId) throws SQLException {
        boolean bandera = false;
        ConexionBD dataBaseConnection = new ConexionBD();
        String query = "DELETE FROM profesor WHERE (id = ?)";
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, searchId);
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate != 0) {
                bandera = true;
            }
        } catch (SQLException ex) {
            LOG.warn(getClass().getName(), ex);
            throw ex;
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return bandera;
    }


    private Profesor getProfesor(ResultSet resultSet) throws SQLException {
        Profesor profesor = new Profesor();
        int id = 0;
        String nombrePersona = "";
        String apellidoPaternoPersona = "";
        String apellidoMaternoPersona = "";
        int idProgramaEducativo = 0;

        try {
            id = resultSet.getInt("id");
            profesor.setIdProfesor(id);
            nombrePersona = resultSet.getString("nombre");
            profesor.setNombre(nombrePersona);
            apellidoMaternoPersona = resultSet.getString("apellidoMaterno");
            profesor.setApellidoMaterno(apellidoMaternoPersona);
            apellidoPaternoPersona = resultSet.getString("apellidoPaterno");
            profesor.setApellidoPaterno(apellidoPaternoPersona);
            idProgramaEducativo = resultSet.getInt("idProgramaEducativo");
            profesor.setIdProgramaEducativo(idProgramaEducativo);
            
        } catch(SQLException ex) {
            LOG.warn(getClass().getName(), ex);
            throw ex;
        }
        return profesor;
    }

    @Override
    public List<Profesor> obtenerProfesores() throws SQLException {
        List<Profesor> profesores = new ArrayList<>();
        ConexionBD dataBaseConnection = new ConexionBD();
        String query = "select prof.id, per.nombre, per.apellidoPaterno, per.apellidoMaterno, per.idProgramaEducativo from persona per inner join profesor prof on per.id = prof.idPersona where per.nombre";
        try (Connection connection = dataBaseConnection.abrirConexion()){
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                profesores.add(getProfesor(resultSet));
            }
        }catch (SQLException ex){
            LOG.warn(getClass().getName(), ex);
            throw ex;
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return profesores;
    }
}
