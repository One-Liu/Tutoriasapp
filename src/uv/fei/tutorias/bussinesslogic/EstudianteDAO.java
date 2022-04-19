package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.Estudiante;
import uv.fei.tutorias.domain.ProgramaEducativo;
import uv.fei.tutorias.domain.TutorAcademico;

// author @liu
public class EstudianteDAO implements IEstudianteDAO {
    @Override
    public List<Estudiante> findEstudianteByName(String searchName) {
        List<Estudiante> estudiantes = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT E.idEstudiante, E.matricula, P.idPersona AS idPersonaEstudiante, P.nombre AS nombreEstudiante, " +
                "P.apellidoPaterno AS apellidoPaternoEstudiante, P.apellidoMaterno AS apellidoMaternoEstudiante, " +
                "P.correoInstitucional AS correoInstitucionalEstudiante, P.correoPersonal AS correoPersonalEstudiante, " +
                "E.idTutorAcademico, E.idProgramaEducativo " +
                "FROM Estudiante E " +
                "LEFT JOIN Persona P ON P.idPersona = E.idPersona " +
                "WHERE CONCAT(P.nombre,\" \",P.apellidoPaterno,\" \",P.apellidoMaterno) LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + searchName + "%");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("Estudiante not found");
            } else {
                do {
                    estudiantes.add(getEstudiante(resultSet));
                }while(resultSet.next());
            }
        } catch(SQLException ex) {
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return estudiantes;
        }
    }

    @Override
    public Estudiante findEstudianteById(int idEstudiante) {
        Estudiante estudiante = new Estudiante();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT E.idEstudiante, E.matricula, P.idPersona AS idPersonaEstudiante, P.nombre AS nombreEstudiante, " +
                "P.apellidoPaterno AS apellidoPaternoEstudiante, P.apellidoMaterno AS apellidoMaternoEstudiante, " +
                "P.correoInstitucional AS correoInstitucionalEstudiante, P.correoPersonal AS correoPersonalEstudiante, " +
                "E.idTutorAcademico, E.idProgramaEducativo " +
                "FROM Estudiante E " +
                "LEFT JOIN Persona P ON P.idPersona = E.idPersona " +
                "WHERE idEstudiante = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idEstudiante);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("Estudiante not found");
            }
            estudiante = getEstudiante(resultSet);
        } catch(SQLException ex) {
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return estudiante;
        }
    }

    @Override
        public Estudiante getEstudiante(ResultSet resultSet) {
        int idEstudiante = 0;
        String matricula = "";
        int idPersonaEstudiante = 0;
        String nombreEstudiante = "";
        String apellidoPaternoEstudiante = "";
        String apellidoMaternoEstudiante = "";
        String correoInstitucionalEstudiante = "";
        String correoPersonalEstudiante = "";
        int idProgramaEducativo = 0;
        int idTutorAcademico = 0;
        try {
            idEstudiante = resultSet.getInt("idEstudiante");
            matricula = resultSet.getString("matricula");
            idPersonaEstudiante = resultSet.getInt("idPersonaEstudiante");
            nombreEstudiante = resultSet.getString("nombreEstudiante");
            apellidoPaternoEstudiante = resultSet.getString("apellidoPaternoEstudiante");
            apellidoMaternoEstudiante = resultSet.getString("apellidoMaternoEstudiante");
            correoInstitucionalEstudiante = resultSet.getString("correoInstitucionalEstudiante");
            correoPersonalEstudiante = resultSet.getString("correoPersonalEstudiante");
            idTutorAcademico = resultSet.getInt("idTutorAcademico");
            idProgramaEducativo = resultSet.getInt("idProgramaEducativo");
        } catch(SQLException ex) {
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Persona personaEstudiante = new Persona(idPersonaEstudiante,nombreEstudiante,apellidoPaternoEstudiante,apellidoMaternoEstudiante,correoInstitucionalEstudiante,correoPersonalEstudiante);
        ProgramaEducativoDAO programaEducativoDao = new ProgramaEducativoDAO();
        ProgramaEducativo programaEducativo = programaEducativoDao.findProgramaEducativoById(idProgramaEducativo);
        TutorAcademicoDAO tutorAcademicoDao = new TutorAcademicoDAO();
        TutorAcademico tutorAcademico = tutorAcademicoDao.findTutorAcademicoById(idTutorAcademico);
        Estudiante estudiante = new Estudiante(idEstudiante,matricula,personaEstudiante,tutorAcademico,programaEducativo);
        return estudiante;
    }
    
    @Override
    public boolean addEstudiante(Estudiante estudiante) {
        PersonaDAO personaDao = new PersonaDAO();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean result = false;
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO Estudiante (matricula, idTutorAcademico, idProgramaEducativo, idPersona) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, estudiante.getMatricula());
            statement.setInt(2, estudiante.getTutorAcademico().getIdTutorAcademico());
            statement.setInt(3, estudiante.getProgramaEducativo().getIdProgramaEducativo());
            statement.setInt(4, personaDao.addPersonaReturnId(estudiante.getPersona()));
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: Estudiante not added");
            }
            result = true;
        } catch(SQLException ex) {
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return result;
        }
    }

    @Override
    public boolean deleteEstudianteById(int idEstudiante) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean result = false;
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "DELETE FROM Estudiante WHERE idEstudiante = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idEstudiante);
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: Estudiante not deleted");
            }
            result = true;
        } catch(SQLException ex) {
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataBaseConnection.cerrarConexion();
            return result;
        }
    }
}
