package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.ProblematicaAcademica;
import uv.fei.tutorias.domain.SolucionAProblematicaAcademica;

import java.util.List;

public interface ISolucionAProblematicaAcademicaDAO {
    public boolean addSolucionProblematicaAcademica(SolucionAProblematicaAcademica solucionAProblematicaAcademica);
    public  boolean deleteSolucionAProblematicaAcademicaById(int idProblematicaAcademica);
    public SolucionAProblematicaAcademica findSolucionAProblematicaAcademicaById (int searchId);


}
