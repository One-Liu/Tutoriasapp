package uv.fei.tutorias.bussinesslogic;

import java.util.List;
import uv.fei.tutorias.domain.Coordinador;

// author @liu
public interface ICoordinadorDAO {
    public List<Coordinador> findCoordinadorByName(String searchName);
    public Coordinador findCoordinadorById(int idCoordinador);
    public boolean addCoordinador(Coordinador coordinador);
    public boolean deleteCoordinadorById(int idCoordinador);
}
