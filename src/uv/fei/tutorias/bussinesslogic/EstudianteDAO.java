package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.Estudiante;
import uv.fei.tutorias.domain.ProgramaEducativo;
import uv.fei.tutorias.domain.TutorAcademico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// author @liu
public class EstudianteDAO implements IEstudianteDAO {
    @Override
    public List<Estudiante> findEstudianteByName(String searchName) {
        List<Estudiante> estudiantes = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT E.matricula, \n" +
                "P.nombre AS nombreEstudiante, P.apellidoPaterno AS apellidoPaternoEstudiante, P.apellidoMaterno AS apellidoMaternoEstudiante, \n" +
                "P.correoInstitucional AS correoInstitucionalEstudiante, P.correoPersonal AS correoPersonalEstudiante, \n" +
                "PE.nombre AS nombreProgramaEducativo, \n" +
                "PTA.nombre AS nombreTutorAcademico, PTA.apellidoPaterno AS apellidoPaternoTutorAcademico, PTA.apellidoMaterno AS apellidoMaternoTutorAcademico, \n" +
                "PTA.correoInstitucional AS correoInstitucionalTutorAcademico, PTA.correoPersonal AS correoPersonalTutorAcademico\n" +
                "FROM Estudiante E \n" +
                "LEFT JOIN Persona P ON P.idPersona = E.idPersona\n" +
                "LEFT JOIN ProgramaEducativo PE ON PE.idProgramaEducativo = E.idProgramaEducativo\n" +
                "LEFT JOIN TutorAcademico TA ON TA.idTutorAcademico = E.idTutorAcademico\n" +
                "LEFT JOIN Persona PTA ON TA.idPersona = PTA.idPersona\n" +
                "WHERE CONCAT(P.nombre,\" \", P.apellidoPaterno,\" \",P.apellidoMaterno) LIKE ?";
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
            String query = "SELECT E.matricula, P.nombre AS nombreEstudiante, P.apellidoPaterno AS apellidoPaternoEstudiante, P.apellidoMaterno AS apellidoMaternoEstudiante, P.correoInstitucional AS correoInstitucionalEstudiante, P.correoPersonal AS correoPersonalEstudiante, PE.nombre AS nombreProgramaEducativo, PTA.nombre AS nombreTutorAcademico, PTA.apellidoPaterno AS apellidoPaternoTutorAcademico, PTA.apellidoMaterno AS apellidoMaternoTutorAcademico, PTA.correoInstitucional AS correoInstitucionalTutorAcademico, PTA.correoPersonal AS correoPersonalTutorAcademico FROM Estudiante E LEFT JOIN Persona P ON P.idPersona = E.idPersona LEFT JOIN ProgramaEducativo PE ON PE.idProgramaEducativo = E.idProgramaEducativo LEFT JOIN TutorAcademico TA ON TA.idTutorAcademico = E.idTutorAcademico LEFT JOIN Persona PTA ON TA.idPersona = PTA.idPersona WHERE idEstudiante = ?";
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
    public boolean addEstudiante(Estudiante estudiante) {
        PersonaDAO personaDao = new PersonaDAO();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO Estudiante (matricula, idTutorAcademico, idProgramaEducativo, idPersona) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, estudiante.getMatricula());
            statement.setInt(2, estudiante.getTutorAcademico().getIdTutorAcademico());
            statement.setInt(3, estudiante.getProgramaEducativo().getIdProgramaEducativo());
            statement.setInt(4, personaDao.addPersonaReturnId(estudiante.getPersona()));
            int affectedRows = statement.executeUpdate();
            dataBaseConnection.cerrarConexion();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: Estudiante not added");
            } else {
                System.out.println("Estudiante added");
                return true;
            }
        } catch(SQLException ex) {
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteEstudianteById(int idEstudiante) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "DELETE FROM Estudiante WHERE idEstudiante = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idEstudiante);
            int affectedRows = statement.executeUpdate();
            dataBaseConnection.cerrarConexion();
            if(affectedRows == 0) {
                throw new SQLException("ERROR: Estudiante not deleted");
            } else {
                System.out.println("Estudiante deleted");
                return true;
            }
        } catch(SQLException ex) {
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Estudiante getEstudiante(ResultSet resultSet) {
        Estudiante estudiante = new Estudiante();
        Persona personaEstudiante = new Persona();
        ProgramaEducativo programaEducativo = new ProgramaEducativo();
        ProgramaEducativoDAO programaEducativoDao = new ProgramaEducativoDAO();
        TutorAcademico tutorAcademico = new TutorAcademico();
        TutorAcademicoDAO tutorAcademicoDao = new TutorAcademicoDAO();
        String matricula = "";
        String nombreEstudiante = "";
        String apellidoPaternoEstudiante = "";
        String apellidoMaternoEstudiante = "";
        String correoInstitucionalEstudiante = "";
        String correoPersonalEstudiante = "";
        try {
            matricula = resultSet.getString("matricula");
            estudiante.setMatricula(matricula);
            nombreEstudiante = resultSet.getString("nombreEstudiante");
            personaEstudiante.setNombre(nombreEstudiante);
            apellidoPaternoEstudiante = resultSet.getString("apellidoPaternoEstudiante");
            personaEstudiante.setApellidoPaterno(apellidoPaternoEstudiante);
            apellidoMaternoEstudiante = resultSet.getString("apellidoMaternoEstudiante");
            personaEstudiante.setApellidoMaterno(apellidoMaternoEstudiante);
            correoInstitucionalEstudiante = resultSet.getString("correoInstitucionalEstudiante");
            personaEstudiante.setCorreoInstitucional(correoInstitucionalEstudiante);
            correoPersonalEstudiante = resultSet.getString("correoPersonalEstudiante");
            personaEstudiante.setCorreoPersonal(correoPersonalEstudiante);
            estudiante.setPersona(personaEstudiante);
            programaEducativo = programaEducativoDao.getProgramaEducativo(resultSet);
            estudiante.setProgramaEducativo(programaEducativo);
            tutorAcademico = tutorAcademicoDao.getTutorAcademico(resultSet);
            estudiante.setTutorAcademico(tutorAcademico);
        } catch(SQLException ex) {
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return estudiante;
        }
    }
}
