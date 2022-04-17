package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.Profesor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;



public class ProfesorDao implements IProfesorDao{
    private final Logger log = Logger.getLogger(PersonaDAO.class);

    @Override
    public boolean addProfesor(Persona profesor) {
        PersonaDAO personaDao = new PersonaDAO();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            if (personaDao.addPerson(profesor)) {
                String query = "INSERT INTO profesor (Persona_idPersona) VALUES ( ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, personaDao.findIdPersona(profesor));
                statement.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            log.warn(PersonaDAO.class.getName(), ex);
        }
        return false;
    }


    @Override
    public List<Profesor> findProfesoresByName(String searchName) {
        return null;
    }

    @Override
    public Profesor findProfesorById(int idProfesor) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT E.matricula, P.nombre AS nombreEstudiante, P.apellidoPaterno AS apellidoPaternoEstudiante, P.apellidoMaterno AS apellidoMaternoEstudiante, P.correoInstitucional AS correoInstitucionalEstudiante, P.correoPersonal AS correoPersonalEstudiante, PE.nombre AS nombreProgramaEducativo, PTA.nombre AS nombreTutorAcademico, PTA.apellidoPaterno AS apellidoPaternoTutorAcademico, PTA.apellidoMaterno AS apellidoMaternoTutorAcademico, PTA.correoInstitucional AS correoInstitucionalTutorAcademico, PTA.correoPersonal AS correoPersonalTutorAcademico FROM Estudiante E LEFT JOIN Persona P ON P.idPersona = E.idPersona LEFT JOIN ProgramaEducativo PE ON PE.idProgramaEducativo = E.idProgramaEducativo LEFT JOIN TutorAcademico TA ON TA.idTutorAcademico = E.idTutorAcademico LEFT JOIN Persona PTA ON TA.idPersona = PTA.idPersona WHERE idEstudiante = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idProfesor);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("Estudiante not found");
            }
            return getProfesor(resultSet);
        } catch(SQLException ex) {
            log.warn(PersonaDAO.class.getName(), ex);
        }
        return null;

    }




    @Override
    public boolean deleteProfesorById(int idProfesor) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {

            String query = "DELETE FROM profesor WHERE (idProfesor = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idProfesor);

            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0) {
                throw new SQLException("ERROR: No se ha eliminado ningun profesor");
            } else {
                System.out.println("Profesor eliminada satisfactoriamente");
            }
        } catch (SQLException ex) {
            log.warn(PersonaDAO.class.getName(), ex);
        }
        return true;
    }


    private Profesor getProfesor(ResultSet resultSet) {
        Profesor profesor = new Profesor();
        String nombrePersona = "";
        String apellidoPaternoPersona = "";
        String apellidoMaternoPersona = "";
        String correoInstitucionalPersona = "";
        String correoPersonalPersona = "";
        try {
            nombrePersona = resultSet.getString("nombre");
            profesor.getPersona().setNombre(nombrePersona);
            apellidoMaternoPersona = resultSet.getString("apellidoMaterno");
            profesor.getPersona().setApellidoMaterno(apellidoMaternoPersona);
            apellidoPaternoPersona = resultSet.getString("apellidoPaterno");
            profesor.getPersona().setApellidoPaterno(apellidoPaternoPersona);
            correoInstitucionalPersona = resultSet.getString("correoInstitucional");
            profesor.getPersona().setCorreoInstitucional(correoInstitucionalPersona);
            apellidoPaternoPersona = resultSet.getString("apellidoPaterno");
            profesor.getPersona().setApellidoPaterno(apellidoPaternoPersona);
            correoPersonalPersona = resultSet.getString("correoPersonal");
            profesor.getPersona().setCorreoPersonal(correoPersonalPersona);

        } catch(SQLException ex) {
            log.warn(PersonaDAO.class.getName(), ex);
        }
        return profesor;
    }

}
