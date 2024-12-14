import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;

public class Main extends Application {

    private MediaPlayer mediaPlayer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Space Shooter - Kelompok 4");
        primaryStage.setScene(scene);
        primaryStage.show();
        playBackgroundMusic();
    }

    private void playBackgroundMusic() {
        URL musicUrl = getClass().getResource("musik/spaceSong.mp3");
        if (musicUrl != null) {
            String musicPath = musicUrl.toExternalForm();
            Media media = new Media(musicPath);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(0.5);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setAutoPlay(true);
        }
    }
}
