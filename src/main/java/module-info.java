module test_task.devcom_test_task {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens test_task.devcom_test_task to javafx.fxml;
    exports test_task.devcom_test_task;
}