module test_task.devcom_test_task {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens test_task.form to javafx.fxml;
    exports test_task.form;
}