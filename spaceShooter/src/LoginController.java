import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    //Menampilkan Alert jika terjadi kesalahan
    protected void showErrorAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Validasi input
        if (username.isEmpty() || password.isEmpty()) {
            // Tampilkan pesan kesalahan jika ada field yang kosong
            showErrorAlert("Login Error", "Input Required", "Please enter both username and password.");
        } else {
            // Validasi kredensial
            if (validateCredentials(username, password)) {
                // Jika valid, lanjutkan ke mainScreen
                Parent mainScreenRoot = FXMLLoader.load(getClass().getResource("FXML/mainScreen.fxml"));
                Scene mainScreenScene = new Scene(mainScreenRoot);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(mainScreenScene);
                stage.show();
            } else {
                // Tampilkan pesan kesalahan jika kredensial tidak valid
                showErrorAlert("Login Error", "Invalid Credentials", "Username or password is incorrect.");
            }
        }
    }

    private boolean validateCredentials(String username, String password) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/akun/password.txt")));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @FXML
    private void handleRegister(ActionEvent event) throws IOException {
        Parent registerRoot = FXMLLoader.load(getClass().getResource("FXML/Register.fxml"));
        Scene registerScene = new Scene(registerRoot);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(registerScene);
        stage.show();
    }
}