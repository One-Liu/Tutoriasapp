package uv.fei.tutorias.main;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import uv.fei.tutorias.bussinesslogic.PersonaDAO;
import uv.fei.tutorias.domain.Persona;

import java.sql.SQLException;

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
            if (!camposVacios()){
                PersonaDAO personaDAO = new PersonaDAO();
                Persona persona = new Persona( nombre.getText(), apellidoMaterno.getText(),
                        apellidoPaterno.getText(),  correoPersonal.getText(),
                        correoInstitucional.getText());
                personaDAO.addPerson(persona);
            }


    }
    public boolean camposVacios(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        boolean bandera = true;
        if (nombre.getText().trim().isEmpty()){
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error nombre vacio");
            alert.showAndWait();
        }else if (apellidoPaterno.getText().trim().isEmpty()){
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error apellido paterno vacio");
            alert.showAndWait();
        }else if (apellidoMaterno.getText().trim().isEmpty()){
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error apellido materno");
            alert.showAndWait();
        }else if (correoInstitucional.getText().trim().isEmpty()){
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error correo institucional vacio");
            alert.showAndWait();
        }else if (correoPersonal.getText().trim().isEmpty()){
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error correo personal");
            alert.showAndWait();
        }else {
            bandera = false;
        }
        return bandera;
    }

}