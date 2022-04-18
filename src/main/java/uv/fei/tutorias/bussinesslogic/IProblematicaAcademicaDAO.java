package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.ExperienciaEducativa;
import uv.fei.tutorias.domain.ProblematicaAcademica;

import java.util.List;

public interface IProblematicaAcademicaDAO {
    public List<ExperienciaEducativa> findProblematicasAcademicasByName(String serchName);
    public ProblematicaAcademica findProblematicaAcademicaById(int searchId);
    public boolean addProblematicaAcademica(ProblematicaAcademica problematicaAcademica);
    public boolean deleteProblematicaAcademicaById(int searchId);
}
