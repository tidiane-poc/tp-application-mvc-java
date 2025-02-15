package ma.enset.iidbcc.tpapplicationmvc.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.iidbcc.tpapplicationmvc.entites.Consultation;
import ma.enset.iidbcc.tpapplicationmvc.entites.Patient;
import ma.enset.iidbcc.tpapplicationmvc.services.IConsultationService;
import ma.enset.iidbcc.tpapplicationmvc.state.AppGlobalState;

import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class ConsultationController implements Initializable {
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    @FXML private ComboBox<Patient> patientSelector;
    @FXML private DatePicker consultationDatePicker;
    @FXML private TextArea descriptionTextArea;
    @FXML private TextField searchTextField;

    @FXML private TableView<Consultation> consultationTableView;
    @FXML private TableColumn<Consultation, Long> idTableColumn;
    @FXML private TableColumn<Consultation, String> dateTableColumn;
    @FXML private TableColumn<Consultation, String> patientNameTableColumn;
    @FXML private TableColumn<Consultation, String> patientPhoneNumberTableColumn;
    @FXML private TableColumn<Consultation, String> descriptionTableColumn;

    @FXML private Button saveBtn;
    @FXML private Button deleteBtn;
    @FXML private Button refreshBtn;

    private static final IConsultationService consultationService = AppGlobalState.getConsultationService();
    private Consultation consultationToUpdate;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        consultationTableView.setItems(AppGlobalState.getConsultations());
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateTableColumn.setCellValueFactory(cell -> {
            if (cell.getValue().getDate() == null) return new SimpleStringProperty("");
            return new SimpleStringProperty(sdf.format(cell.getValue().getDate()));
        });
        patientNameTableColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue().getPatient() == null) return new SimpleStringProperty("");
            return new SimpleStringProperty(cellData.getValue().getPatient().getNom() + " " + cellData.getValue().getPatient().getPrenom());
        });
        patientPhoneNumberTableColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue().getPatient() == null) return new SimpleStringProperty("");
            return new SimpleStringProperty(cellData.getValue().getPatient().getTel());
        });
        descriptionTableColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        patientSelector.setItems(AppGlobalState.getPatients());
        patientSelector.setCellFactory(patientListView -> new ListCell<>() {
            @Override
            protected void updateItem(Patient item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText("");
                } else {
                    setText(item.getNom() + " " + item.getPrenom());
                }
            }
        });
        patientSelector.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Patient item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText("");
                } else {
                    setText(item.getNom() + " " + item.getPrenom());
                }
            }
        });

        consultationTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                consultationDatePicker.setValue(newValue.getDate().toLocalDate());
                descriptionTextArea.setText(newValue.getDescription());
                patientSelector.setValue(newValue.getPatient());
                consultationToUpdate = newValue;
                saveBtn.setText("Modifier");
                deleteBtn.setDisable(false);
            } else {
                saveBtn.setText("Enregistrer");
                deleteBtn.setDisable(true);
            }
        });

        searchTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty()) {
                var items = consultationService.findAllByQuery(newValue);
                AppGlobalState.setConsultations(items);
            } else {
                AppGlobalState.init();
            }
        });
    }

    public void save() {
        if (consultationToUpdate != null) {
            update();
        } else {
            create();
        }

    }

    private void create() {
        Consultation consultation = new Consultation();
        consultation.setDate(Date.valueOf(consultationDatePicker.getValue()));
        consultation.setDescription(descriptionTextArea.getText());
        consultation.setPatient(patientSelector.getValue());
        consultationService.save(consultation);
        AppGlobalState.init();
        clearFields();
    }
    public void delete() {
        Consultation consultation = consultationTableView.getSelectionModel().getSelectedItem();
        if (consultation == null) return;

        consultationService.delete(consultation.getId());
        AppGlobalState.init();
        clearFields();
    }

    private void update() {
        consultationToUpdate.setDate(Date.valueOf(consultationDatePicker.getValue()));
        consultationToUpdate.setDescription(descriptionTextArea.getText());
        consultationToUpdate.setPatient(patientSelector.getValue());
        consultationService.update(consultationToUpdate);
        AppGlobalState.init();
        clearFields();
    }

    public void clearFields() {
        consultationDatePicker.setValue(null);
        descriptionTextArea.clear();
        patientSelector.setValue(null);
        consultationToUpdate = null;
    }
}
