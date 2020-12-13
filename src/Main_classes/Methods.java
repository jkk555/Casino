package Main_classes;

import Interface.GraphObject;
import Interface.MainObject;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.*;
import java.util.Random;

public class Methods extends MainClass implements MainObject
{
    //numer stawki
    private double rate;
    //15 minut czyli czas oczekiwania na kredyt
    private int seconds;
    //---
    private int timeSeconds;
    //ogólne kliknięcia
    private int click;
    //kliknięcia po zmianie stawki
    private int click2;
    //stan konta
    private int money;
    //generator
    private Random random = new Random();

    public Methods(int seconds,int click,int click2,double rate)
    {
        this.seconds = seconds;
        this.click = click;
        this.click2 = click2;
        this.rate = rate;
        timeSeconds = this.seconds;
    }


    //Losowanie owoców
    protected void setImages(int los, ImageView imageView ) {

        for (int i = 0; i < 9; i++) {
            switch (los) {
                case 1:
                    imageView.setImage(banan);
                    break;

                case 2:
                    imageView.setImage(winogrono);
                    break;

                case 3:
                    imageView.setImage(truskawka);
                    break;

                case 4:
                    imageView.setImage(cytryna);
                    break;

                case 5:
                    imageView.setImage(pomarancza);
                    break;

            }

        }

    }

    //Zapis stanu konta
    protected static void zapis(String text,String filePath) throws IOException
    {
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(filePath);
            fileWriter.write(text);
        } finally {
            if (fileWriter != null) {
                fileWriter.close();
            }
        }
    }

    protected void createImageViev(int X,int Y, double SCALE_X, double SCALE_Y, ImageView imageView)
    {
        imageView.setLayoutX(X);
        imageView.setLayoutY(Y);
        imageView.setScaleX(SCALE_X);
        imageView.setScaleY(SCALE_Y);
    }

    //Tworzenie wykresu
    protected void createGraph(Stage stageGraph, Scene sceneGraph)
    {
        xAxis.setLabel("Numer ruchu");
        lineChart.setTitle("Wykres stanu konta w czasie gry");

        series.setName("Kliknięcia");

        stageGraph.setScene(sceneGraph);
        stageGraph.setTitle(GraphObject.title);
        stageGraph.show();
    }

    //Wyświetlenie gifa po straceniu wszystkich pieniędzy
    protected void end()
    {
        Stage end_stage = new Stage();
        Pane rootEnd = new Pane();
        Scene sceneEnd = new Scene(rootEnd,290,280);
        Image end = new Image("Image/200.gif");
        ImageView vievEnd = new ImageView(end);
        vievEnd.setLayoutX(50);
        vievEnd.setLayoutY(40);
        vievEnd.setScaleX(1.5);
        vievEnd.setScaleY(1.5);

        end_stage.setResizable(false);
        rootEnd.getChildren().add(vievEnd);
        end_stage.setScene(sceneEnd);
        end_stage.show();
    }

    protected void sleep()
    {
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //dodawanie punktów wykresu co 2 kliknięcia
    protected void addGraph(XYChart.Series series, int click)
    {
        if (click % 2 == 0)
         {
            series.getData().add(new XYChart.Data(click, money));
         }
    }

    //Brak pieniędzy
    protected void lack_of_money(int money)
    {
        if (money-15 < 0) {
            end();
        }
    }

    /****
     *
     * getters and setters
     *
     */

    public void setTimeSeconds(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setClick(int click) {
        this.click = click;
    }

    public void setClick2(int click2) {
        this.click2 = click2;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getMoney() {
        return money;
    }

    public int getClick() {
        return click;
    }

    public int getClick2() {
        return click2;
    }

    public double getRate() {
        return rate;
    }

    public int getTimeSeconds() {
        return timeSeconds;
    }

    public Random getRandom() {
        return random;
    }

}