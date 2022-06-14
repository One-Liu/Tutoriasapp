package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.SolucionAProblematicaAcademica;

import java.sql.SQLException;

public interface ISolucionAProblematicaAcademicaDAO {
    public boolean agregarSolucionProblematicaAcademica(String solucionProblematicaAcademicaTexto) throws SQLException;
    public  boolean eliminarSolucionAProblematicaAcademicaById(int idProblematicaAcademica) throws SQLException;
    public SolucionAProblematicaAcademica buscarSolucionAProblematicaAcademicaById(int searchId);


}
