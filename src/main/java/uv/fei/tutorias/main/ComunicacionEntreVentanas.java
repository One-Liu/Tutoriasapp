package uv.fei.tutorias.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ComunicacionEntreVentanas {
    private String nombreFxml;
    private Object objetoAPasar;
    private Object objetoRecuperado;
    private String nombreDeLaVentana;
    private ActionEvent actionEvent;
    //setActionEvent setnombreFxml setObjeto setnombreDeLaVentana
    public void pasarValoresEntreVentanas() throws IOException {
        Node node = (Node) this.actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(Utilidad.class.getResource(this.nombreFxml));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setUserData(this.objetoAPasar);
        stage.setTitle(this.nombreDeLaVentana);
        stage.setScene(scene);
        stage.show();
    }
    public Object recuperarValoresDeLaVentana(){
        Node node = (Node) this.actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        this.objetoRecuperado = stage.getUserData();
        return objetoRecuperado;
    }


    public String getNombreFxml() {
        return nombreFxml;
    }

    public void setNombreFxml(String nombreFxml) {
        this.nombreFxml = nombreFxml;
    }

    public Object getObjetoAPasar() {
        return objetoAPasar;
    }

    public void setObjetoAPasar(Object objetoAPasar) {
        this.objetoAPasar = objetoAPasar;
    }

    public Object getObjetoRecuperado() {
        return objetoRecuperado;
    }

    public void setObjetoRecuperado(Object objetoRecuperado) {
        this.objetoRecuperado = objetoRecuperado;
    }

    public String getNombreDeLaVentana() {
        return nombreDeLaVentana;
    }

    public void setNombreDeLaVentana(String nombreDeLaVentana) {
        this.nombreDeLaVentana = nombreDeLaVentana;
    }

    public ActionEvent getActionEvent() {
        return actionEvent;
    }

    public void setActionEvent(ActionEvent actionEvent) {
        this.actionEvent = actionEvent;
    }

    public ComunicacionEntreVentanas() {
        this.nombreFxml = "";
        this.objetoRecuperado = new Object();
        this.objetoAPasar = new Object();
        this.nombreDeLaVentana = "";
        this.actionEvent = new ActionEvent();
    }
}
