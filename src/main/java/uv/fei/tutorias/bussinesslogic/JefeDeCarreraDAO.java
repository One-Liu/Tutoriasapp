package uv.fei.tutorias.bussinesslogic;

import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.JefeDeCarrera;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import uv.fei.tutorias.domain.Persona;

public class JefeDeCarreraDAO implements IJefeDeCarreraDAO {

    private final Logger LOG = Logger.getLogger(JefeDeCarreraDAO.class);

    @Override
    public JefeDeCarrera obtenerJefeDeCarreraPorIdUsuario(int idUsuario) throws SQLException {
        JefeDeCarrera jefeDeCarrera = new JefeDeCarrera();
        String consulta = "SELECT JC.id, P.idProgramaEducativo, P.nombre, P.apellidoPaterno, P.apellidoMaterno "
            + "FROM jefe_de_carrera JC INNER JOIN persona P ON P.id = JC.idPersona "
            + "WHERE idUsuario = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try( Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idUsuario);
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.next()) {
                jefeDeCarrera = getJefeDeCarrera(resultado);
            }
        } catch(SQLException excepcionSQL) {
            LOG.warn(TutorAcademicoDAO.class.getName(), new SQLException());
            throw excepcionSQL;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return jefeDeCarrera;
    }

    private JefeDeCarrera getJefeDeCarrera(ResultSet resultado) throws SQLException {
        int idJefeDeCarrera;
        String nombre;
        String apellidoPaterno;
        String apellidoMaterno;
        int idProgramaEducativo;

        idJefeDeCarrera = resultado.getInt("id");
        nombre = resultado.getString("nombre");
        apellidoPaterno = resultado.getString("apellidoPaterno");
        apellidoMaterno = resultado.getString("apellidoMaterno");
        idProgramaEducativo = resultado.getInt("idProgramaEducativo");

        Persona personaJefeDeCarrera = new Persona(nombre, apellidoPaterno, apellidoMaterno, idProgramaEducativo);
        JefeDeCarrera jefeDeCarrera = new JefeDeCarrera(idJefeDeCarrera, personaJefeDeCarrera);
        return jefeDeCarrera;
    }
}
