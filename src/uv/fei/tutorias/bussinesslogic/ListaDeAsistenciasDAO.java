package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.Estudiante;
import uv.fei.tutorias.domain.ListaDeAsistencias;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

// author @liu
public class ListaDeAsistenciasDAO implements IListaDeAsistenciasDAO {
    @Override
    public ListaDeAsistencias findListaDeAsistenciasById(int idListaDeAsistencias) {
        ListaDeAsistencias listaDeAsistencias = new ListaDeAsistencias();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idListaDeAsistencias);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("ListaDeAsistencias not found");
            }
            listaDeAsistencias = getListaDeAsistencias(resultSet);
        } catch(SQLException ex) {
            Logger.getLogger(ListaDeAsistenciasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return listaDeAsistencias;
        }
    }

    @Override
    public boolean addListaDeAsistencias(ListaDeAsistencias listaDeAsistencias) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO ListaDeAsistencias (idEstudiante, idSesionDeTutoriaAcademica) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, listaDeAsistencias.getEstudiante().getIdEstudiante());
            statement.setInt(2, listaDeAsistencias.getSesionDeTutoriaAcademica().getIdSesionDeTutoriaAcademica());
            int affectedRows = statement.executeUpdate();
            dataBaseConnection.cerrarConexion();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: ListaDeAsistencias not added");
            } else {
                System.out.println("ListaDeAsistencias added");
                return true;
            }
        } catch(SQLException ex) {
            Logger.getLogger(ListaDeAsistenciasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteListaDeAsistenciasById(int idListaDeAsistencias) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "DELETE FROM ListaDeAsistencias WHERE idListaDeAsistencias = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idListaDeAsistencias);
            int affectedRows = statement.executeUpdate();
            dataBaseConnection.cerrarConexion();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: ListaDeAsistencias not deleted");
            } else {
                System.out.println("ListaDeAsistencias deleted");
                return true;
            }
        } catch(SQLException ex) {
            Logger.getLogger(ListaDeAsistenciasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public ListaDeAsistencias getListaDeAsistencias(ResultSet resultSet) {
        ListaDeAsistencias listaDeAsistencias = new ListaDeAsistencias();
        SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica();
        SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDao = new SesionDeTutoriaAcademicaDAO();
        Estudiante estudiante = new Estudiante();
        EstudianteDAO estudianteDao = new EstudianteDAO();
        
        sesionDeTutoriaAcademica = sesionDeTutoriaAcademicaDao.getSesionDeTutoriaAcademica(resultSet);
        listaDeAsistencias.setSesionDeTutoriaAcademica(sesionDeTutoriaAcademica);
        estudiante = estudianteDao.getEstudiante(resultSet);
        listaDeAsistencias.setEstudiante(estudiante);
        
        return listaDeAsistencias;
    }
}
