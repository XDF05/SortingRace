package g56020.atlg4.sorting.view;

import g56020.atlg4.sorting.utils.Observable;
import g56020.atlg4.sorting.utils.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.Timestamp;
import java.util.List;

public class LogsFXMLController implements Observer {

    @FXML
    private TableColumn<?, ?> colSortType;

    @FXML
    private TableColumn<?, ?> colTimeStamp;

    @FXML
    private TableView<> logsTable;

    @Override
    public void update(Observable observable, Object arg) {
        try{

        }
    }
}
