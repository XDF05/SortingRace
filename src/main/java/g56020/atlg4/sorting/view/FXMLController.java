package g56020.atlg4.sorting.view;

import g56020.atlg4.sorting.model.Difficulty;
import g56020.atlg4.sorting.model.SortType;
import g56020.atlg4.sorting.model.SortingRace;
import g56020.atlg4.sorting.model.sort.Sort;
import g56020.atlg4.sorting.utils.Observable;
import g56020.atlg4.sorting.utils.Observer;
import g56020.atlg4.sorting.utils.State;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;


public class FXMLController implements Observer {
    @FXML
    private MenuItem aboutItem;

    @FXML
    private MenuItem menuItemlogs;

    @FXML
    private LineChart<Integer, Long> chart;

    @FXML
    private ChoiceBox<Difficulty> configurationChoice;

    @FXML
    private TableColumn<Sort, Long> durationCol;

    @FXML
    private Label leftStatus;

    @FXML
    private TableColumn<Sort, String> nameCol;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private MenuItem quitItem;

    @FXML
    private Label rightStatus;

    @FXML
    private TableColumn<Sort, Integer> sizeCol;

    @FXML
    private ChoiceBox<SortType> sortChoice;

    @FXML
    private Button start;

    @FXML
    private TableColumn<Sort, Long> swapCol;

    @FXML
    private TableView<Sort> table;

    @FXML
    private Spinner<Integer> threadSpinner;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    private double progress;
    private XYChart.Series<Integer, Long> series;

    private SortingRace model;

    @FXML
    public void initialize() {
        initChoiceBoxes();
        initTableView();
    }

    @FXML
    void showLogs(ActionEvent event) {
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/logs.fxml"));
            Scene scene = new Scene(root, 1024, 768);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("show logs");
    }

    @FXML
    void onStartButtonClick() {
        int nbElem = configurationChoice.getValue().getN();
        switch (sortChoice.getValue()) {
            case BUBBLE_SORT -> {
                model.start(SortType.BUBBLE_SORT, threadSpinner.getValue(), nbElem);
            }
            case MERGE_SORT -> {
                model.start(SortType.MERGE_SORT, threadSpinner.getValue(), nbElem);
            }
            case INSERTION_SORT -> {
                model.start(SortType.INSERTION_SORT, threadSpinner.getValue(), nbElem);
            }
            default -> throw new IllegalArgumentException("Unexpected value: " + sortChoice.getValue());
        }

    }


    private void initChoiceBoxes() {
        for (SortType s : SortType.values()) {
            sortChoice.getItems().add(s);
        }
        sortChoice.setValue(SortType.BUBBLE_SORT);
        for (Difficulty d : Difficulty.values()) {
            configurationChoice.getItems().add(d);
        }
        configurationChoice.setValue(Difficulty.VERY_EASY);
    }

    private void initTableView() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
        swapCol.setCellValueFactory(new PropertyValueFactory<>("operations"));
        durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
    }

    public void setModel(SortingRace model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object arg) {
        Platform.runLater(() -> {
            leftStatus.setText("Active threads: " + Thread.activeCount());
            if (arg == State.SORTING) {
                series = new XYChart.Series<>();
                chart.getData().add(series);
                progressBar.setProgress(progress = 0);
            } else if (arg instanceof Sort) {
                Sort sort = ((Sort) arg);
                series.setName(sort.getName().toString());
                table.getItems().add(((Sort) arg));
                int size = ((Sort) arg).getSize();
                long operations = ((Sort) arg).getOperations();

                series.getData().add(new XYChart.Data(size, operations));
                progressBar.setProgress(progress += 0.1);
            }
        });
    }
}
