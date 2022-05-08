package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.EstudianteProblematicaAcademica;

import java.util.List;

public interface IEstudianteProblematicaAcademicaDAO {
    public List<EstudianteProblematicaAcademica> findEstudiantesProblematicaAcademicaById(int searchIdproblematicaAcademica);
    public boolean addEstudianteProblematicaAcademica(EstudianteProblematicaAcademica estudiante_problematicaAcademica);
    public boolean deleteEstudinateProblematicaAcademicaById(int searchId);
}
