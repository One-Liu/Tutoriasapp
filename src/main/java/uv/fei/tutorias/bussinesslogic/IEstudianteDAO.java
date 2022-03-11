package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.Persona;

import java.util.List;

//author @L3M-ON

    public interface IEstudianteDAO{
        public List<Persona> findEstudianteByName(String searchName);
        public Persona findEstudianteById(int idEstudiante);
        public boolean addEstudiante(Persona estudiante);
        public boolean deleteEstudienteById(int idEstudiante);
    }
}
