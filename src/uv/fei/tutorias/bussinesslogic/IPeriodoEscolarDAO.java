package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.PeriodoEscolar;

import java.util.List;

// author @liu
public interface IPeriodoEscolarDAO {
    public List<PeriodoEscolar> findPeriodosEscolaresByFechaInicio(String date);
    public PeriodoEscolar findPeriodoEscolarById(int idPeriodoEscolar);
    public boolean addPeriodoEscolar(PeriodoEscolar periodoEscolar);
    public boolean deletePeriodoEscolarById(int idPeriodoEscolar);
}
