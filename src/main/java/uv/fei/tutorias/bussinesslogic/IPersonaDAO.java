package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.Persona;

import java.util.List;

public interface IPersonaDAO {
    public List<Persona> findPersonaByName(String serchName);
}
