package uv.fei.tutorias.main;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uv.fei.tutorias.utilidades.DatosGlobalesDeSesion;

public class MenuPrincipalCoordinadorDeTutoriasControlador {
    @FXML
    private void clicConsultarReporteGeneralDeTutoriasAcademicas(ActionEvent actionEvent) {

    }

    @FXML
    private void clicRegistrarFechasDeSesionDeTutoria(ActionEvent actionEvent) {
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUIRegistrarFechasDeSesionDeTutoria.fxml"));
            Parent raiz = cargadorFXML.load();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Registrar fechas de sesion de tutoria");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException ioException) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }


    }

    @FXML
    private void clicRegistrarFechasDeCierreParaLaEntregaDeReporte(ActionEvent actionEvent) {
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUIRegistrarFechasDeCierreParaLaEntregaDeReporte.fxml"));
            Parent raiz = cargadorFXML.load();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Consultar oferta academica");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException ioException) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
    }

    @FXML
    private void clicConsultarOfertaAcademica(ActionEvent actionEvent) {
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUIConsultarOfertaAcademica.fxml"));
            Parent raiz = cargadorFXML.load();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Consultar oferta academica");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException ioException) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
    }
    
    @FXML
    private void clicConsultarReportePorTutorAcademico(ActionEvent evento) {
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("/src/main/resources/uv/fei/tutorias/main/GUISeleccionDeReporte.fxml"));
            Parent raiz = cargadorFXML.load();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Selección de reporte");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException excepcionIO) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
    }

    @FXML
    private void clicModificarFechasDeSesionDeTutoria(ActionEvent evento) {
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUISeleccionDePeriodoEscolar.fxml"));
            Parent raiz = cargadorFXML.load();
            SeleccionDePeriodoEscolarControlador controladorGUI = cargadorFXML.getController();
            controladorGUI.cargarDatos();
            controladorGUI.cargarCamposGUI();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Selección de periodo escolar");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException excepcionIO) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        } catch(SQLException excepcionSQL) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
    }

    @FXML
    private void clicRegistrarUsuario(ActionEvent evento) {
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUISignUp.fxml"));
            Parent raiz = cargadorFXML.load();
            SignUpControlador controladorGUI = cargadorFXML.getController();
            controladorGUI.cargarDatos();
            controladorGUI.cargarCamposGUI();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Sign up");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException excepcionIO) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        } catch(SQLException excepcionSQL) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
    }

    @FXML
    private void clicAsignarTutorAEstudiante(ActionEvent evento) {
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUIAsignacionDeTutorAcademico.fxml"));
            Parent raiz = cargadorFXML.load();
            AsignacionDeTutorAcademicoControlador controladorGUI = cargadorFXML.getController();
            controladorGUI.cargarDatos();
            controladorGUI.cargarCamposGUI();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Asignación de tutor académico");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException excepcionIO) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        } catch(SQLException excepcionSQL) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
    }
    
    @FXML
    private void clicModificarAsignacionDeTutor(ActionEvent evento) {
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUISeleccionDeEstudiante.fxml"));
            Parent raiz = cargadorFXML.load();
            ModificacionDeAsignacionDeTutorAcademicoControlador controladorGUI = cargadorFXML.getController();
            controladorGUI.cargarDatos();
            controladorGUI.cargarCamposGUI();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Selección de estudiante");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException excepcionIO) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        } catch(SQLException excepcionSQL) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
    }
    
    @FXML
    private void clicCerrarSesion(ActionEvent evento) {
        DatosGlobalesDeSesion.getDatosGlobalesDeSesion().setCoordinador(null);
        UtilidadVentana.cerrarVentana(evento);
        
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUILogin.fxml"));
            Parent raiz = cargadorFXML.load();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Login");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException excepcionIO) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
    }
}
