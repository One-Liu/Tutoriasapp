package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.SolucionAProblematicaAcademica;

import java.sql.SQLException;

public interface ISolucionAProblematicaAcademicaDAO {
    public int agregarSolucionProblematicaAcademica(String solucionProblematicaAcademicaTexto) throws SQLException;
    public boolean eliminarSolucionAProblematicaAcademicaById(int idProblematicaAcademica) throws SQLException;
    public SolucionAProblematicaAcademica buscarSolucionAProblematicaAcademicaById(int searchId);
    public boolean modificarSolucionAProblematicaAcademica(SolucionAProblematicaAcademica solucionAProblematicaAcademica) throws SQLException;

}
