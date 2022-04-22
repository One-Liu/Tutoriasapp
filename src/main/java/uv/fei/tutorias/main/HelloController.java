package uv.fei.tutorias.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import uv.fei.tutorias.bussinesslogic.PersonaDAO;
import uv.fei.tutorias.domain.Persona;

public class HelloController {

    @FXML
    private TextField nombre;
    @FXML
    private TextField apellidoMaterno;
    @FXML
    private TextField apellidoPaterno;
    @FXML
    private TextField correoInstitucional;
    @FXML
    private TextField correoPersonal;

    @FXML
    protected void onOkButton() {
        PersonaDAO personaDAO = new PersonaDAO();
        Persona persona = new Persona( nombre.getText(), apellidoMaterno.getText(),
                apellidoPaterno.getText(),  correoPersonal.getText(),
                 correoInstitucional.getText());
        personaDAO.addPerson(persona);
    }
}