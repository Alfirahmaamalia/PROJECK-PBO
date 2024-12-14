import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class RegisterController extends LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleRegister(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Validasi input
        if (username.isEmpty() || password.isEmpty()) {
            // Menggunakan metode dari LoginController untuk menampilkan pesan kesalahan
            showErrorAlert("Registration Error", "Input Required", "Please fill in all fields.");
        } else {
            // Simpan kredensial ke file
            saveCredentialsToFile(username, password);

            // Kembali ke layar login setelah berhasil
            Parent loginScreenRoot = FXMLLoader.load(getClass().getResource("FXML/login.fxml"));
            Scene loginScreenScene = new Scene(loginScreenRoot);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(loginScreenScene);
            stage.show();
        }
    }

    private void saveCredentialsToFile(String username, String password) {
        // Jika ingin ubah password, ubah dulu file pathnya sesuai path password di laptop anda
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\spaceShooterjadii\\src\\akun/password.txt", true))) {
            writer.write(username + ":" + password);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}