package uv.fei.tutorias.bussinesslogic;

import javafx.collections.ObservableList;
import uv.fei.tutorias.domain.ExperienciaEducativa;
import uv.fei.tutorias.domain.Persona;

import java.util.List;

public interface IExperienciaEducativaDAO {
    public ObservableList<ExperienciaEducativa> findExperienciasEducativasByName(String serchName);
    public ExperienciaEducativa findExperienciaEducativaById(int searchId);
    public boolean addExperienciaEducativa(ExperienciaEducativa experienciaEducativa);
    public boolean deleteExperienciaEducativa(int searchId);
}
