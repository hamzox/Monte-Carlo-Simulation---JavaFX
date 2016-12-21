package Pi_Estimation;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class pi_est extends Application {
    
    public static Boolean isSplashLoaded = false; 
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SplashFXML.fxml"));        
        Scene scene = new Scene(root);
        
        CustomTransition(root);
        
        stage.initStyle(StageStyle.UNDECORATED);
        
        stage.setY(180);
        stage.setX(400);
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    private void CustomTransition(Parent pane) {
        
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
            fadeIn.setFromValue(0.2);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0.5);
            fadeOut.setCycleCount(0);

            fadeIn.play();

//            fadeIn.setOnFinished((e) -> {
//                fadeOut.play();
//            });
    }
}
