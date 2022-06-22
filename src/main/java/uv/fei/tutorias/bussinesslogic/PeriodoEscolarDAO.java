package uv.fei.tutorias.bussinesslogic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.PeriodoEscolar;

// author @liu
public class PeriodoEscolarDAO implements IPeriodoEscolarDAO {

    private final Logger LOGGER = Logger.getLogger(PeriodoEscolarDAO.class);
    
    @Override
    public List<PeriodoEscolar> obtenerPeriodosEscolares() throws SQLException {
        List<PeriodoEscolar> periodosEscolares = new ArrayList<>();
        String consulta = "SELECT * FROM periodo_escolar";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            while(resultado.next()) {
                periodosEscolares.add(getPeriodoEscolar(resultado));
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return periodosEscolares;
    }

    @Override
    public PeriodoEscolar obtenerPeriodoEscolarPorId(int idPeriodoEscolar) throws SQLException {
        PeriodoEscolar periodoEscolar = new PeriodoEscolar();
        String consulta = "SELECT * FROM periodo_escolar WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try (Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idPeriodoEscolar);
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.next()) {
                periodoEscolar = getPeriodoEscolar(resultado);
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return periodoEscolar;
    }

    private PeriodoEscolar getPeriodoEscolar(ResultSet resultado) throws SQLException {
        int idPeriodoEscolar;
        Date fechaInicio;
        Date fechaTermino;

        idPeriodoEscolar = resultado.getInt("id");
        fechaInicio = resultado.getDate("fechaInicio");
        fechaTermino = resultado.getDate("fechaTermino");
        
        PeriodoEscolar periodoEscolar = new PeriodoEscolar(idPeriodoEscolar,(java.util.Date) fechaInicio,(java.util.Date) fechaTermino);
        return periodoEscolar;
    }

    @Override
    public int agregarPeriodoEscolar(PeriodoEscolar periodoEscolar) throws SQLException {
        int id;
        String consulta = "INSERT INTO periodo_escolar (fechaInicio, fechaTermino) VALUES (?,?)";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
            sentencia.setDate(1, (Date) periodoEscolar.getFechaInicio());
            sentencia.setDate(2, (Date) periodoEscolar.getFechaTermino());
            int columnasAfectadas = sentencia.executeUpdate();
            ResultSet resultado = sentencia.getGeneratedKeys();
            if(columnasAfectadas == 0) {
                id = -1;
            }else {
                resultado.next();
                id=resultado.getInt(1);
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return id;
    }
}