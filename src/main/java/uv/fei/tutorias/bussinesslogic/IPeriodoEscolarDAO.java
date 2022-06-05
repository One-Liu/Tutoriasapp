package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import javafx.collections.ObservableList;
import uv.fei.tutorias.domain.PeriodoEscolar;

// author @liu
public interface IPeriodoEscolarDAO {
    public ObservableList<PeriodoEscolar> obtenerPeriodosEscolares() throws SQLException;
    public PeriodoEscolar obtenerPeriodoEscolarPorId(int idPeriodoEscolar) throws SQLException;
    public boolean agregarPeriodoEscolar(PeriodoEscolar periodoEscolar) throws SQLException;
    public boolean eliminarPeriodoEscolarPorId(int idPeriodoEscolar) throws SQLException;
    public boolean modificarPeriodoEscolar(PeriodoEscolar periodoEscolar) throws SQLException;
}