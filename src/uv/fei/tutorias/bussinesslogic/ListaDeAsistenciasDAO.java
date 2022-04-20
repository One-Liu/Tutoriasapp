package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.Estudiante;
import uv.fei.tutorias.domain.ListaDeAsistencias;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

// author @liu
public class ListaDeAsistenciasDAO implements IListaDeAsistenciasDAO {
    
    static final Logger LOGGER = Logger.getLogger(ListaDeAsistenciasDAO.class);
    
    @Override
    public ListaDeAsistencias findListaDeAsistenciasById(int idListaDeAsistencias) {
        ListaDeAsistencias listaDeAsistencias = new ListaDeAsistencias();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT * FROM ListaDeAsistencias " +
                "WHERE idListaDeAsistencias = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idListaDeAsistencias);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("ListaDeAsistencias not found");
            }
            listaDeAsistencias = getListaDeAsistencias(resultSet);
        } catch(SQLException ex) {
            LOGGER.error(ListaDeAsistenciasDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return listaDeAsistencias;
        }
    }
    
    @Override
    public ListaDeAsistencias getListaDeAsistencias(ResultSet resultSet) {
        int idListaDeAsistencias = 0;
        int idEstudiante = 0;
        int idSesionDeTutoriaAcademica = 0;
        try {
            idListaDeAsistencias = resultSet.getInt("idListaDeAsistencias");
            idEstudiante = resultSet.getInt("idEstudiante");
            idSesionDeTutoriaAcademica = resultSet.getInt("idSesionDeTutoriaAcademica");
        } catch(SQLException ex) {
            LOGGER.error(ListaDeAsistenciasDAO.class.getName(),ex);
        }
        EstudianteDAO estudianteDao = new EstudianteDAO();
        Estudiante estudiante = estudianteDao.findEstudianteById(idEstudiante);
        SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDao = new SesionDeTutoriaAcademicaDAO();
        SesionDeTutoriaAcademica sesionDeTutoriaAcademica = sesionDeTutoriaAcademicaDao.findSesionDeTutoriaAcademicaById(idSesionDeTutoriaAcademica);
        ListaDeAsistencias listaDeAsistencias = new ListaDeAsistencias(idListaDeAsistencias,sesionDeTutoriaAcademica,estudiante);
        return listaDeAsistencias;
    }

    @Override
    public boolean addListaDeAsistencias(ListaDeAsistencias listaDeAsistencias) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean result = false;
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO ListaDeAsistencias (idEstudiante, idSesionDeTutoriaAcademica) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, listaDeAsistencias.getEstudiante().getIdEstudiante());
            statement.setInt(2, listaDeAsistencias.getSesionDeTutoriaAcademica().getIdSesionDeTutoriaAcademica());
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: ListaDeAsistencias not added");
            }
            result = true;
        } catch(SQLException ex) {
            LOGGER.error(ListaDeAsistenciasDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return result;
        }
    }

    @Override
    public boolean deleteListaDeAsistenciasById(int idListaDeAsistencias) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean result = false;
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "DELETE FROM ListaDeAsistencias WHERE idListaDeAsistencias = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idListaDeAsistencias);
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: ListaDeAsistencias not deleted");
            }
            result = true;
        } catch(SQLException ex) {
            LOGGER.error(ListaDeAsistenciasDAO.class.getName(),ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return result;
        }
    }
}
