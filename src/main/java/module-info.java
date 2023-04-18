module com.example.mainform {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires java.xml;


    opens com.example.mainform to javafx.fxml;
    exports com.example.mainform;

}