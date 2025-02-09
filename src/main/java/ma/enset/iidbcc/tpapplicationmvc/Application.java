package ma.enset.iidbcc.tpapplicationmvc;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ma.enset.iidbcc.tpapplicationmvc.dao.impl.PatientDaoImpl;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        launch();

        PatientDaoImpl patientDaoImpl = new PatientDaoImpl();
//        Patient patient = new Patient("DIALLO", "Amadou", "amadou@gmail.com", "0661013474");
//        patientDao.save(patient);
        System.out.println("All patients:");
        patientDaoImpl.findAll().forEach(System.out::println);

        System.out.println("Patient with id 1:");
        var optionalPatient = patientDaoImpl.findOne(1L);
        System.out.println(optionalPatient.orElse(null));

        System.out.println("Patient with email: amadou@gmail.com");
        var optionalPatientByEmail = patientDaoImpl.findByEmail("amadou@gmail.com");
        System.out.println(optionalPatientByEmail.orElse(null));
//        patientDao.delete(3L);
    }
}