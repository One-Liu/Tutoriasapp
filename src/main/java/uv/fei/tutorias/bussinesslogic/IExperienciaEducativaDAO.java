package uv.fei.tutorias.bussinesslogic;

import javafx.collections.ObservableList;
import uv.fei.tutorias.domain.ExperienciaEducativa;

public interface IExperienciaEducativaDAO {
    public ObservableList<ExperienciaEducativa> buscarExperienciasEducativasPorNombre(String serchName);
    public ExperienciaEducativa obtenerExperienciaEducativaPorId(int searchId);
    public boolean agregarExperienciaEducativa(ExperienciaEducativa experienciaEducativa);
    public boolean eliminarExperienciaEducativa(int searchId);
}
