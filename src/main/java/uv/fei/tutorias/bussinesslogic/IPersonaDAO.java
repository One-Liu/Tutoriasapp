package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.Persona;

import java.util.List;

public interface IPersonaDAO {
    public List<Persona> findPersonsByName(String serchName);
    public Persona findPersonById(int searchId);
    public boolean addPerson(Persona persona);
    public boolean deletePersonById(int searchId);
}
