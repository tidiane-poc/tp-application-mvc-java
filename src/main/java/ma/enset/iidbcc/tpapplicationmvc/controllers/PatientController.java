package ma.enset.iidbcc.tpapplicationmvc.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.iidbcc.tpapplicationmvc.entites.Patient;
import ma.enset.iidbcc.tpapplicationmvc.services.IPatientService;
import ma.enset.iidbcc.tpapplicationmvc.state.AppGlobalState;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientController implements Initializable {

    @FXML private TextField searchTextField;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField phoneNumberTextField;

    @FXML private TableView<Patient> patientTableView;
    @FXML private TableColumn<Patient,Long> idTableColumn;
    @FXML private TableColumn<Patient,String> firstNameTableColumn;
    @FXML private TableColumn<Patient,String> lastNameTableColumn;
    @FXML private TableColumn<Patient,String> phoneNumberTableColumn;
    @FXML private TableColumn<Patient,String> emailTableColumn;

    @FXML private Button saveBtn;
    @FXML private Button deleteBtn;

    private static final IPatientService patientService = AppGlobalState.getPatientService();
    private Patient patientToUpdate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        patientTableView.setItems(AppGlobalState.getPatients());
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        lastNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        phoneNumberTableColumn.setCellValueFactory(new PropertyValueFactory<>("tel"));
        emailTableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        patientTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                firstNameTextField.setText(newValue.getPrenom());
                lastNameTextField.setText(newValue.getNom());
                emailTextField.setText(newValue.getEmail());
                phoneNumberTextField.setText(newValue.getTel());
                patientToUpdate = newValue;
                saveBtn.setText("Modifier");
            }else {
                clearFields();
                saveBtn.setText("Enregistrer");
            }
        });

        searchTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty()) {
                var patients = patientService.findByQuery(newValue);
                AppGlobalState.setPatients(patients);
            } else {
                AppGlobalState.init();
            }
        });
    }

    public void save(){
        if (patientToUpdate != null) {
            update();
        } else {
            Patient patient = new Patient(
                    firstNameTextField.getText(),
                    lastNameTextField.getText(),
                    emailTextField.getText(),
                    phoneNumberTextField.getText()
            );
            patientService.save(patient);
            AppGlobalState.init();
            clearFields();
        }

    }

    public void delete(){
        Patient patient = patientTableView.getSelectionModel().getSelectedItem();
        if (patient != null) {
            patientService.delete(patient.getId());
            AppGlobalState.init();
            clearFields();
        }
    }
    private void update(){
        if (patientToUpdate != null) {
            patientToUpdate.setPrenom(firstNameTextField.getText());
            patientToUpdate.setNom(lastNameTextField.getText());
            patientToUpdate.setEmail(emailTextField.getText());
            patientToUpdate.setTel(phoneNumberTextField.getText());
            patientService.update(patientToUpdate);
            AppGlobalState.init();
            clearFields();
        }
    }

    private void clearFields(){
        firstNameTextField.clear();
        lastNameTextField.clear();
        emailTextField.clear();
        phoneNumberTextField.clear();
        patientToUpdate = null;
    }
}
