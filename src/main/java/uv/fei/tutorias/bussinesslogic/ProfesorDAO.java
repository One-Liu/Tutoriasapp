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
    public boolean agregarProfesor(Profesor profesor) throws SQLException {
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
    @Override
    public boolean agregarProfesorYPersona(Persona persona) throws SQLException {
        boolean bandera = false;
        PersonaDAO personaDAO = new PersonaDAO();
        int idPersona;
        idPersona = personaDAO.agregarPersona(persona);
        Profesor profesor = new Profesor();
        if (idPersona != -1){
            profesor.setIdPersona(idPersona);
            agregarProfesor(profesor);
            bandera = true;
        }
        return bandera;
    }


    @Override
    public Profesor obtenerProfesorPorId(int searchId) throws SQLException {
        ConexionBD dataBaseConnection = new ConexionBD();
        Profesor profesor = new Profesor();
        String query = "select prof.id, per.nombre, per.apellidoPaterno, per.apellidoMaterno, per.idProgramaEducativo from persona per inner join profesor prof on per.id = prof.idPersona where prof.id = ? ";
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
    public boolean eliminarProfesorPorId(int searchId) throws SQLException {
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
        int id;
        String nombrePersona;
        String apellidoPaternoPersona;
        String apellidoMaternoPersona;
        int idProgramaEducativo;
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

        return profesor;
    }

    @Override
    public List<Profesor> obtenerProfesores() throws SQLException {
        List<Profesor> profesores = new ArrayList<>();
        ConexionBD dataBaseConnection = new ConexionBD();
        String query = "select prof.id, per.nombre, per.apellidoPaterno, per.apellidoMaterno, per.idProgramaEducativo from persona per inner join profesor prof on per.id = prof.idPersona";
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
