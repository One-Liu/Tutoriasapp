package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.JefeDeCarrera;

public interface IJefeDeCarreraDAO {
    public boolean addJefeDeCarrera(JefeDeCarrera jefeDeCarrera);
    public boolean deleteJefeDeCarreraById(int searchId);
    public JefeDeCarrera findJefeDeCarreraById(int searchId);
}
