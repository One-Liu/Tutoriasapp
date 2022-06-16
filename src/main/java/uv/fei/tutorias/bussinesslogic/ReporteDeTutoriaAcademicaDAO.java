package uv.fei.tutorias.bussinesslogic;

import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.ReporteDeTutoriaAcademica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReporteDeTutoriaAcademicaDAO implements IReporteDeTutoriaAcademicaDAO{
    private final Logger LOG = Logger.getLogger(ReporteDeTutoriaAcademicaDAO.class);

    @Override
    public boolean agregarReporteDeTutoriaAcademica(ReporteDeTutoriaAcademica reporteDeTutoriaAcademica) throws SQLException {
        boolean bandera = false;
        ConexionBD dataBaseConnection = new ConexionBD();
        String query = "INSERT INTO reporte_de_tutoria_academica(descripcionGeneral, idSesionDeTutoriaAcademica, idTutorAcademico) VALUES(?,?,?)";
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, reporteDeTutoriaAcademica.getDescripcionGeneral());
            statement.setInt(2, reporteDeTutoriaAcademica.getIdSesionDeTutoriaAcademica());
            statement.setInt(3, reporteDeTutoriaAcademica.getIdTutorAcademico());
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate != 0) {
                bandera = true;
            }
        }catch (SQLException ex){
            LOG.warn(getClass().getName(),ex);
            throw ex;
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return bandera;
    }

    @Override
    public boolean eliminarReporteDeTutoriasAcademicasPorId(int idReporteDeTutoriaAcademicaBusqueda) throws SQLException {
        boolean bandera = false;
        ConexionBD dataBaseConnection = new ConexionBD();
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            String query = "DELETE FROM reporte_de_tutoria_academica WHERE (id = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idReporteDeTutoriaAcademicaBusqueda);
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate != 0) {
                bandera = true;
            }
        }catch (SQLException ex){
            LOG.warn(getClass().getName(),ex);
            throw ex;
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return bandera;
    }

    @Override
    public ReporteDeTutoriaAcademica obtenerReporteDeTutoriaPorId(int idReporteDeTutoriaAcademicaBusqueda) throws SQLException {
        ConexionBD dataBaseConnection = new ConexionBD();
        ReporteDeTutoriaAcademica reporteDeTutoriaAcademica = new ReporteDeTutoriaAcademica();
        String query = "SELECT * from reporte_de_tutoria_academica where id = ?";
        try (Connection connection = dataBaseConnection.abrirConexion()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,idReporteDeTutoriaAcademicaBusqueda);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                reporteDeTutoriaAcademica = getReporteDeTutoriaAcademica(resultSet);
            }
        }catch (SQLException ex){
            LOG.warn(getClass().getName(),ex);
            throw ex;
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return reporteDeTutoriaAcademica;
    }
    
    @Override
    public ReporteDeTutoriaAcademica obtenerReporteDeTutoriaPorIdSesionTutoriaYIdTutor(ReporteDeTutoriaAcademica reporteDeTutoriaAcademicaBusqueda) throws SQLException {
        ConexionBD dataBaseConnection = new ConexionBD();
        ReporteDeTutoriaAcademica reporteDeTutoriaAcademica = new ReporteDeTutoriaAcademica();
        String query = "SELECT * from reporte_de_tutoria_academica where idSesionDeTutoriaAcademica = ? AND idTutorAcademico = ?";
        try (Connection connection = dataBaseConnection.abrirConexion()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, reporteDeTutoriaAcademicaBusqueda.getIdSesionDeTutoriaAcademica());
            statement.setInt(2, reporteDeTutoriaAcademicaBusqueda.getIdTutorAcademico());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                reporteDeTutoriaAcademica = getReporteDeTutoriaAcademica(resultSet);
            }
        }catch (SQLException ex){
            LOG.warn(getClass().getName(),ex);
            throw ex;
        }  finally {
            dataBaseConnection.cerrarConexion();
        }
        return reporteDeTutoriaAcademica;
    }

    private ReporteDeTutoriaAcademica getReporteDeTutoriaAcademica(ResultSet resultSet) throws SQLException{
        ReporteDeTutoriaAcademica reporteDeTutoriaAcademica = new ReporteDeTutoriaAcademica();
        int idReporteDeTutoriaAcademica;
        String descripcion;
        int idsesionDeTutoriaAcademica;
        int idTutorAcademico;

        idReporteDeTutoriaAcademica = resultSet.getInt("id");
        descripcion = resultSet.getString("descripcionGeneral");
        idsesionDeTutoriaAcademica = resultSet.getInt("idSesionDeTutoriaAcademica");
        idTutorAcademico = resultSet.getInt("idTutorAcademico");

        reporteDeTutoriaAcademica.setId(idReporteDeTutoriaAcademica);
        reporteDeTutoriaAcademica.setDescripcionGeneral(descripcion);
        reporteDeTutoriaAcademica.setIdSesionDeTutoriaAcademica(idsesionDeTutoriaAcademica);
        reporteDeTutoriaAcademica.setIdTutorAcademico(idTutorAcademico);

        return reporteDeTutoriaAcademica;

    }
}
