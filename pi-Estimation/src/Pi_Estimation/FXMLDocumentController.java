/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pi_Estimation;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import Pi_Estimation.service.service;
import Pi_Estimation.ui.newStage;
import Pi_Estimation.ui.particle;
import Pi_Estimation.ui.table;
import javafx.animation.FadeTransition;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;


public class FXMLDocumentController implements Initializable {

    
    double radius = 200; //radius for circle. 
    
    @FXML 
    private javafx.scene.shape.Circle closeIt;
    @FXML 
    private javafx.scene.shape.Circle minima;
    @FXML
    private Button button;
    @FXML
    private Label label;
    @FXML
    private TextField no_of_repl;
    @FXML
    private TextField stream_size;
    @FXML
    private TableView<table> table1;
    @FXML
    private TableColumn<TableView, Integer> replication;
    @FXML
    private TableColumn<TableView, Integer> sample_size;
    @FXML
    private TableColumn<TableView, Integer> hitCount;
    @FXML
    private TableColumn<TableView, Double> pi;
    @FXML
    private TableColumn<TableView, Double> variance;
    @FXML
    private TableColumn<TableView, Double> final_var;
    @FXML
    private TableColumn<TableView, Double> std_dev;
    @FXML
    private TableColumn<TableView, Double> clib;
    @FXML
    private TableColumn<TableView, Double> club;

    service simulationService;
    Canvas canvas;
    @FXML
    private BarChart<String, Double> chart;
    @FXML
    private NumberAxis piChart;
    @FXML
    private CategoryAxis sampleSizeChart;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        simulationService = new service();
        canvas = new Canvas(640, 480);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {

        String sampleSizeText = stream_size.getText();

        String[] sampleSizeStream = sampleSizeText.split(",");

        //int sampleSize=100 ;
        Circle circle = createCircle();
        Map<Integer, List<particle>> sampleMap = new LinkedHashMap<>();
        for (String sampleSizeStream1 : sampleSizeStream) {

            int sampleSize = Integer.valueOf(sampleSizeStream1);
            sampleMap.put(sampleSize, simulationService.getParticles(sampleSize, radius, canvas,circle));
        }

        XYChart.Series series1 = new XYChart.Series();
        List<table> data = simulationService.getTableData(circle, sampleMap);
        populateTable(data);

        for (table data1 : data) {

            List<particle> particles = simulationService.getParticles(data1.getSampleSize(), radius, canvas,circle);
            Group root = new Group();
            circle = createCircle();
            Rectangle rect = createRectangle();

            root.getChildren().add(rect);
            root.getChildren().add(circle);
            root.getChildren().addAll(particles);
            new newStage(root);
            series1.getData().add(new XYChart.Data("" + data1.getSampleSize(), data1.getEstimatePi()));

        }
        chart.getData().addAll(series1);

    }

    void populateTable(List<table> data) {

        ObservableList<table> tableData = FXCollections.observableArrayList(data);
        replication.setCellValueFactory(new PropertyValueFactory<TableView, Integer>("replication"));
        sample_size.setCellValueFactory(new PropertyValueFactory<TableView, Integer>("sampleSize"));
        hitCount.setCellValueFactory(new PropertyValueFactory<TableView, Integer>("hitCount"));
        pi.setCellValueFactory(new PropertyValueFactory<TableView, Double>("estimatePi"));
        variance.setCellValueFactory(new PropertyValueFactory<TableView, Double>("variance"));
        final_var.setCellValueFactory(new PropertyValueFactory<TableView, Double>("finalVariance"));
        std_dev.setCellValueFactory(new PropertyValueFactory<TableView, Double>("standardDev"));
        clib.setCellValueFactory(new PropertyValueFactory<TableView, Double>("clib"));
        club.setCellValueFactory(new PropertyValueFactory<TableView, Double>("club"));

        table1.setItems(tableData);

    }

    private Circle createCircle() {
        Circle c = new Circle();
        c.centerXProperty().bind(canvas.widthProperty().divide(2));
        c.centerYProperty().bind(canvas.heightProperty().divide(2));
        c.setRadius(radius);
        c.setFill(Color.YELLOW);
        c.setStroke(Color.YELLOW);
        c.setStrokeWidth(4);
        return c;
    }

    private Rectangle createRectangle() {
        Rectangle r = new Rectangle();
        r.setTranslateX(canvas.getWidth() / 2 - radius);
        r.setTranslateY(canvas.getHeight() / 2 - radius);
        r.setWidth(radius * 2);
        r.setHeight(radius * 2);
        r.setFill(Color.BLUE);
        r.setStroke(Color.BLUE);
        r.setStrokeWidth(4);
        return r;
    }
    
    /** RED CLOSING WINDOW BUTTON**/
    public void handleMousePressRed(MouseEvent action) {
        Stage stage = (Stage) closeIt.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    public void handleMouseEnterRed(MouseEvent action) {
        closeIt.setOpacity(0.6);
    }
    public void handleMouseLeaveRed(MouseEvent action){
        
        closeIt.setOpacity(1);
    }/** EO-RED BUTTON **/
    
    /** GREEN BUTTON MINIMIZE THE WINDOW**/
    public void handleMousePressGreen(MouseEvent action) {
        Stage stage = (Stage) minima.getScene().getWindow();
        stage.setIconified(true);
    }
    
    public void handleMouseEnterGreen(MouseEvent action) {
        minima.setOpacity(0.6);
    }
    public void handleMouseLeaveGreen(MouseEvent action){
        minima.setOpacity(1);
    }/** EO-GREEN BUTTON **/
}
