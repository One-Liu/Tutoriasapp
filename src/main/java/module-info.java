module uv.fei.tutorias {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires junit;
    requires org.junit.jupiter.api;
    requires org.testng;


    exports uv.fei.tutorias.test to junit;
    opens uv.fei.tutorias.main to javafx.fxml;
    exports uv.fei.tutorias.main;
}