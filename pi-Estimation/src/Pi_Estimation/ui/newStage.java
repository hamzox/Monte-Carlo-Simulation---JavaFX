/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pi_Estimation.ui;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author hii
 */
public class newStage {
    public static int count=1;
    
    public newStage(Group root) {
        Stage subStage = new Stage();
        subStage.setTitle("Test: "+count);
        subStage.sizeToScene();
        
        subStage.setX(700);
        subStage.setY(50);
        
        Scene scene = new Scene(root, 640, 500);
        
        subStage.setScene(scene);
        subStage.show();
        
        count++;
    }
}
