package uv.fei.tutorias;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;


public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Get tutors");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                TutorDAO tutorDao = new TutorDAO();
                ArrayList<Tutor> tutors = (ArrayList<Tutor>) tutorDao.findTutorsByName("juan");
                for (Tutor tutor : tutors) {
                    System.out.println(String.format("Tutor found: %d %s %s", tutor.getTutorId(), tutor.getName(), tutor.getLastName()));

                }
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
