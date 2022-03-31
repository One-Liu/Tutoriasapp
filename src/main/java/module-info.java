module uv.fei.tutorias {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires junit;
    requires org.junit.jupiter.api;
    requires org.testng;


    opens uv.fei.tutorias to javafx.fxml;
    exports uv.fei.tutorias.test to junit;
}