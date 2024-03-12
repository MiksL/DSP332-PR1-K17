module com.k17.pr1k17 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.k17.pr1k17 to javafx.fxml;
    exports com.k17.pr1k17;
}