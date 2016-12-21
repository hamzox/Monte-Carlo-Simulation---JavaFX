package Pi_Estimation;

import java.io.IOException;
import static java.lang.Thread.MAX_PRIORITY;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SplashFXMLController implements Initializable {

    @FXML
    private StackPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      new SplashScreen().start();
    }
    
    class SplashScreen extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(6000);
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        
                        Parent root=null;
                        
                        try {
                            root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                        } catch (IOException ex) {
                            Logger.getLogger(SplashFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        
                        UserCustomizeSettings(stage, scene);

                        rootPane.getScene().getWindow().hide();
                    }
                
                });
                
            } catch (InterruptedException ex) {
                Logger.getLogger(SplashFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
    private void UserCustomizeSettings(Stage stage, Scene scene){

        stage.initStyle(StageStyle.UNDECORATED);
        
        stage.setX(10);
        stage.setY(50);
        
        stage.setMaxHeight(650);
        stage.setMaxWidth(760);
        
        stage.setScene(scene);
        stage.setTitle("Pi Estimation");
        stage.show();
        
    }

}
