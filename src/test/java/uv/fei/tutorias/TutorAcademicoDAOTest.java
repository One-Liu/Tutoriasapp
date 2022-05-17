/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uv.fei.tutorias;

import org.junit.Before;
import org.junit.Test;
import uv.fei.tutorias.bussinesslogic.JefeDeCarreraDAO;
import uv.fei.tutorias.bussinesslogic.TutorAcademicoDAO;
import uv.fei.tutorias.domain.JefeDeCarrera;
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.TutorAcademico;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// author @liu

public class TutorAcademicoDAOTest {
    private TutorAcademicoDAO tutorAcademicoDAO;
    private TutorAcademico tutorAcademico;
    @Before
    public void init(){
        tutorAcademicoDAO = new TutorAcademicoDAO();
        tutorAcademico = new TutorAcademico();
        tutorAcademico.setIdPersona(40);
        tutorAcademico.getUsuario().setId(1);
    }
    @Test
    public void addTutorAcademica(){
        assertTrue(tutorAcademicoDAO.addTutorAcademico(tutorAcademico));
    }


}
