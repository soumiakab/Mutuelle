package com.mutuelle.app.views;
import java.util.Map;

import com.mutuelle.app.dao.ClientDao;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
public class TestController extends Application {

	ClientDao clientdao = new ClientDao();
    @Override
    public void start(Stage stage) {

        initUI(stage);
    }

    private void initUI(Stage stage) {

        var root = new HBox();

        var scene = new Scene(root, 480, 330);
        var xAxis = new CategoryAxis();

        var yAxis = new NumberAxis();
        yAxis.setLabel("number");

        var barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Clients In Day");

        var data = new XYChart.Series<String, Number>();
        for(Map<String,Integer> elemt:clientdao.clientsInDay()) {
        	 //System.out.println(elemt.keySet()+""+elemt.values().toArray()[0]);
        	 int r=(int) elemt.values().toArray()[0];
        	 data.getData().add(new XYChart.Data<>(elemt.keySet().toString(),r));
        }


        barChart.getData().add(data);
        barChart.setLegendVisible(false);

        root.getChildren().add(barChart);

        stage.setTitle("Clients Chart");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}