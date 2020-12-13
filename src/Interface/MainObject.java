package Interface;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;

public interface MainObject
{
     String title = "Casino";
     int WIDTH = 600;
     int HEIGHT = 350;
     Pane root1 = new Pane();

     //Images
     Image truskawka = new Image("Image/truskawka.gif");
     Image banan = new Image("Image/banan.gif");
     Image winogrono = new Image("Image/winogrono.gif");
     Image pomarancza = new Image("Image/pomarańcza.gif");
     Image cytryna = new Image("Image/cytryna.gif");
     Image background = new Image("Image/background.png");

     //Utworzenie układu współrzędnych X i Y
     NumberAxis xAxis = new NumberAxis();
     NumberAxis yAxis = new NumberAxis();

     //Utworzenie wykresu liniowego
     LineChart<Number,Number> lineChart =
            new LineChart<Number,Number>(xAxis,yAxis);

     Label money_viev = new Label("Money: ");

     Scene scene_main = new Scene(MainObject.root1, MainObject.WIDTH, MainObject.HEIGHT);

     //Elementy odpowiedzialne za zrobienie screenshota w tym kombinacja klawiszy ctrl+C oraz wysokość i szerokość screenshota
     /*  KeyCombination kk = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN);
     WritableImage w = new WritableImage(MainObject.WIDTH, MainObject.HEIGHT); */

}