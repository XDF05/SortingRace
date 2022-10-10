package g56020.atlg4.sorting;

import g56020.atlg4.sorting.model.SortingRace;
import g56020.atlg4.sorting.model.config.ConfigManager;
import g56020.atlg4.sorting.view.FXMLController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMLSorting extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SortingRace sortingRace = new SortingRace();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sort.fxml"));
        VBox root = fxmlLoader.load();
        FXMLController viewCtrl = fxmlLoader.getController();
        viewCtrl.setModel(sortingRace);

        Scene scene = new Scene(root, 1024, 768);
        stage.setTitle("Sorting");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}