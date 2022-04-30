package uv.fei.tutorias.bussinesslogic;

import java.util.ArrayList;
import java.sql.ResultSet;
import uv.fei.tutorias.domain.PeriodoEscolar;

// author @liu
public interface IPeriodoEscolarDAO {
    public ArrayList<PeriodoEscolar> findPeriodosEscolaresByFechaInicio(String date);
    public PeriodoEscolar findPeriodoEscolarById(int idPeriodoEscolar);
    public PeriodoEscolar getPeriodoEscolar(ResultSet resultSet);
    public boolean addPeriodoEscolar(PeriodoEscolar periodoEscolar);
    public boolean deletePeriodoEscolarById(int idPeriodoEscolar);
}
