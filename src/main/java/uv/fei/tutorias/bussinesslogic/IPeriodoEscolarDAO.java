package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import java.util.List;
import uv.fei.tutorias.domain.PeriodoEscolar;

// author @liu
public interface IPeriodoEscolarDAO {
    public List<PeriodoEscolar> obtenerPeriodosEscolares() throws SQLException;
    public PeriodoEscolar obtenerPeriodoEscolarPorId(int idPeriodoEscolar) throws SQLException;
    public int agregarPeriodoEscolar(PeriodoEscolar periodoEscolar) throws SQLException;
}