package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.ProblematicaAcademica;

import java.util.List;

public interface IProblematicaAcademicaDAO {
    public List<ProblematicaAcademica> findProblematicaAcademicaByDescription(String serchName);
    public ProblematicaAcademica findProblematicaAcademicaById(int searchId);
    public boolean addProblematicaAcademica(ProblematicaAcademica problematicaAcademica);
    public boolean deleteProblematicaAcademicaById(int searchId);

    public List<ProblematicaAcademica> findAllProblematicasAcademicas();
}
