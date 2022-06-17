package uv.fei.tutorias.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import uv.fei.tutorias.bussinesslogic.*;
import uv.fei.tutorias.domain.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.util.StringConverter;

public class SignUpController implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private TextField tfCorreoInstitucional;
    @FXML
    private ComboBox<String> cbUsuarios;
    @FXML
    private ComboBox<ProgramaEducativo> cbProgramasEducativos;

    private ObservableList<ProgramaEducativo> programasEducativos = FXCollections.observableArrayList();

    public void cargarDatos() throws SQLException {
        ProgramaEducativoDAO programaEducativoDAO = new ProgramaEducativoDAO();
        this.programasEducativos.addAll(programaEducativoDAO.obtenerProgramasEducativos());
    }

    public void cargarCamposGUI() {
        cbUsuarios.getItems().setAll("Tutor academico", "Coordinador", "Jefe de carrera");

        cbProgramasEducativos.setItems(programasEducativos);
        cbProgramasEducativos.getSelectionModel().selectFirst();
        cbProgramasEducativos.setConverter(new StringConverter<ProgramaEducativo>() {
            @Override
            public String toString(ProgramaEducativo programaEducativo) {
                return programaEducativo == null ? null : programaEducativo.getNombre();
            }

            @Override
            public ProgramaEducativo fromString(String string) {
                throw new UnsupportedOperationException("Operación no soportada");
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public boolean camposLlenos() {
        boolean camposLlenos = true;

        if(this.tfNombre.getText().trim().isBlank()
            || this.tfApellidoPaterno.getText().trim().isBlank()
            || this.tfApellidoMaterno.getText().trim().isBlank()
            || this.tfCorreoInstitucional.getText().trim().isBlank()) {
            UtilidadVentana.mostrarAlertaSinConfirmacion(
                "Campos vacíos",
                "No puede haber campos vacíos",
                Alert.AlertType.WARNING);
            camposLlenos = false;
        }

        return camposLlenos;
    }

    @FXML
    private void clicRegistrar() {
        if(camposLlenos()) {
            String nombre = this.tfNombre.getText();
            String apellidoPaterno = this.tfApellidoPaterno.getText();
            String apellidoMaterno = this.tfApellidoMaterno.getText();
            String correoInstitucional = this.tfCorreoInstitucional.getText();
            ProgramaEducativo programaEducativo = this.cbProgramasEducativos.getSelectionModel().getSelectedItem();

            Persona persona = new Persona(nombre, apellidoPaterno, apellidoMaterno, programaEducativo.getId());
            Usuario usuario = new Usuario(GeneradorDeContrasena.getContrasena(), correoInstitucional);

            PersonaDAO personaDAO = new PersonaDAO();
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            try {
                switch(cbUsuarios.getValue()) {
                    case "Tutor académico" -> {
                        TutorAcademicoDAO tutorAcademicoDAO = new TutorAcademicoDAO();
                        TutorAcademico tutorAcademico = new TutorAcademico(persona);

                        tutorAcademico.setIdPersona(personaDAO.agregarPersona(persona));
                        tutorAcademico.setIdUsuario(usuarioDAO.agregarUsuario(usuario));
                        tutorAcademicoDAO.agregarTutorAcademico(tutorAcademico);
                    }

                    case "Coordinador" -> {
                        CoordinadorDAO coordinadorDAO = new CoordinadorDAO();
                        Coordinador coordinador = new Coordinador(persona);

                        coordinador.setIdPersona(personaDAO.agregarPersona(persona));
                        coordinador.setIdUsuario(usuarioDAO.agregarUsuario(usuario));
                        coordinadorDAO.agregarCoordinador(coordinador);
                    }

                    case "Jefe de carrera" -> {
                        JefeDeCarreraDAO jefeDeCarreraDAO = new JefeDeCarreraDAO();
                        JefeDeCarrera jefeDeCarrera = new JefeDeCarrera(persona);

                        jefeDeCarrera.setIdPersona(personaDAO.agregarPersona(persona));
                        jefeDeCarrera.setIdUsuario(usuarioDAO.agregarUsuario(usuario));
                        jefeDeCarreraDAO.agregarJefeDeCarrera(jefeDeCarrera);
                    }
                }

                UtilidadVentana.mostrarAlertaSinConfirmacion(
                    "Usuario registrado",
                    "La contraseña del usuario es " + usuario.getContrasena() + ".\n"
                    + "Por favor, solicite al usuario cambiarla al ingresar al sistema.",
                    Alert.AlertType.INFORMATION);
            } catch(SQLException excepcionSQL) {
                UtilidadVentana.mensajePerdidaDeConexion();
            }
        }
    }

    @FXML
    private void clicCancelar(ActionEvent evento) {
        UtilidadVentana.cerrarVentana(evento);
    }
}
