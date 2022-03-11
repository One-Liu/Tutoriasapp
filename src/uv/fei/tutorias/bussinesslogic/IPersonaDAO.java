package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.Persona;

import java.util.List;

public interface IPersonaDAO {
    public List<Persona> findPersonasByName(String searchName);
    public Persona findPersonaById(int searchId);
    public boolean addPersona(Persona persona);
    public boolean eliminarPersonaById(int searchId);
}