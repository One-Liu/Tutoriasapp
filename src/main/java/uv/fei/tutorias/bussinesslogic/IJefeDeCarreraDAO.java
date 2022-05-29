package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.JefeDeCarrera;

public interface IJefeDeCarreraDAO {
    public boolean agregarJefeDeCarrera(JefeDeCarrera jefeDeCarrera);
    public boolean eliminarJefeDeCarreraPorId(int searchId);
    public JefeDeCarrera obtenerJefeDeCarreraPorId(int searchId);
}
