package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.Persona;

import java.util.List;

public interface IPersonaDAO {
    public List<Persona> obtenerPersonaPorNombre(String serchName);
    public Persona obtenerPersonaPorId(int searchId);
    public int agregarPersona(Persona persona);
    public boolean eliminarPersonaPorId(int searchId);
}
