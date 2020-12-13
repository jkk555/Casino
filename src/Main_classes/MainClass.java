package Main_classes;

import Interface.GraphObject;
import Interface.MainObject;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;

//Wykonano w lipcu 2020r.

public class MainClass extends Application implements MainObject, GraphObject {

    private Timeline timeline;

    private Label timerLabel = new Label();

    private Label real_time;

    private AnimationTimer real_timer;

    protected ImageView[] imageViews = new ImageView[9];
  
    private int lottery_ticket[] = new int[9];

    private int los[] = new int[9];

    //Tworzenie serii danych wykresu
    XYChart.Series series = new XYChart.Series();

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception, IOException {
        Methods methods = new Methods(900,0,0,1);

        Thread thread = new Thread();
        thread.start();
        
        money_viev.setStyle("-fx-font-size: 32px;-fx-font-weight: bold;");
        money_viev.setLayoutX(185);
        money_viev.setLayoutY(10);

        /***
         * Odczyt stanu konta
         */

        String stankonta;
        File file = new File("E:/Casino-Alpha/konto.txt");
        Scanner load = new Scanner(file);

        scene_main.setFill(Color.RED);

        try {
            stankonta = load.nextLine();
            System.out.println(stankonta);
        } finally {
            load.close();
        }
        methods.setMoney(Integer.parseInt(stankonta));

        //Tworzenie imageVievs i przypisywanie im danego obrazka
        for (int i = 0; i < 9; i++) {
            imageViews[i] = new ImageView();
            imageViews[i].setImage(truskawka);
        }

        /***
         *
         * Button start
         *
         */

        ImageView start = new ImageView(new Image("Image/Start.gif"));
        methods.createImageViev(245, 165, 1.1, 1.1, start);
        series.getData().add(new XYChart.Data(methods.getClick(), methods.getMoney()));


        start.setOnMouseClicked(event_start -> {

                methods.lack_of_money(methods.getMoney());

                methods.sleep();

                methods.addGraph(series, methods.getClick());

                methods.setClick(methods.getClick()+1);
                methods.setClick2(methods.getClick2()+1);

                //koszt ruchu przy danej stawce
                switch ((int) methods.getRate()) {
                    case 1:
                        methods.setMoney(methods.getMoney()-15);
                        break;

                    case 2:
                        methods.setMoney(methods.getMoney()-30);
                        break;

                    case 3:
                        methods.setMoney(methods.getMoney()-50);
                        break;
                }

                //co 60 kliknięć gracz dostaje premie 500
                if (methods.getClick2() % 60 == 0) {
                    methods.setMoney(methods.getMoney()+500);
                    methods.setClick2(0);
                }

                //bonusy za jednakową linie owoców:
                for (int i = 0; i < 9; i++) {
                    los[i] = methods.getRandom().nextInt(5) + 1;
                    lottery_ticket[i] = los[i];
                    methods.setImages(los[i], imageViews[i]);

                    if (lottery_ticket[0] == lottery_ticket[1] && lottery_ticket[1] == lottery_ticket[2]&&methods.getMoney()>=0) {

                        switch (los[i]) {
                            case 0:
                                methods.setMoney((int) (methods.getMoney() + (45 * methods.getRate())));
                                break;

                            case 1:
                                methods.setMoney((int) (methods.getMoney() + (60 * methods.getRate())));
                                break;

                            case 2:
                                methods.setMoney((int) (methods.getMoney() + (75 * methods.getRate())));
                                break;

                            case 3:
                                methods.setMoney((int) (methods.getMoney() + (100 * methods.getRate())));
                                break;

                            case 4:
                                methods.setMoney((int) (methods.getMoney() + (200 * methods.getRate())));
                                break;

                        }
                        break;

                    }

                }

                money_viev.setText("Money: " + methods.getMoney() + "$");
        });

        /***
         *
         * stawki za kliknięcie
         *
         */

        ImageView rate1 = new ImageView(new Image("Image/rate1.png"));
        methods.createImageViev(12, 259, 0.7, 0.7, rate1);
        rate1.setOnMouseClicked(event_rate1 -> {
            methods.setRate(1);
            methods.setClick2(0);
        });

        ImageView rate2 = new ImageView(new Image("Image/rate2.png"));
        methods.createImageViev(80, 259, 0.7, 0.7, rate2);
        rate2.setOnMouseClicked(event_rate2 -> {
            methods.setRate(2);
            methods.setClick2(0);
        });

        ImageView rate3 = new ImageView(new Image("Image/rate3.png"));
        methods.createImageViev(150, 259, 0.7, 0.7, rate3);
        rate3.setOnMouseClicked(event_rate3 -> {
            methods.setRate(3);
            methods.setClick2(0);
        });

        /****
         *
         * Inne przyciski
         *
         */

        ImageView graph = new ImageView(new Image("Image/graph.png"));
        methods.createImageViev(345, 262, 0.7, 0.7, graph);
        Scene sceneGraph = new Scene(lineChart, GraphObject.WIDTH, GraphObject.HEIGHT);
        Stage stageGraph = new Stage();
        lineChart.getData().add(series);

        graph.setOnMouseClicked(event_graph -> methods.createGraph(stageGraph, sceneGraph));

        Stage shop_stage = new Stage();

        ImageView shop = new ImageView(new Image("Image/shop.png"));
        methods.createImageViev(410, 262, 0.7, 0.7, shop);
        shop.setOnMouseClicked(event_shop -> {

            shop_stage.setTitle("Credit Timer");
            Group root = new Group();
            Scene scene = new Scene(root, 300, 250);

            timerLabel.setText("Time: "+methods.getTimeSeconds());
            timerLabel.setTextFill(Color.RED);
            timerLabel.setStyle("-fx-font-size: 4em;");

            Button button = new Button();
            button.setText("Start credit timer");
            button.setOnAction(event_timer -> {
                if (timeline != null) {
                    timeline.stop();
                }
                methods.setSeconds(methods.getTimeSeconds());

                timerLabel.setText("Time: "+methods.getTimeSeconds());
                timeline = new Timeline();
                timeline.setCycleCount(Timeline.INDEFINITE); //automatyczne odliczanie
                timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1) , event11 -> {

                            methods.setTimeSeconds(methods.getTimeSeconds()-1);

                            timerLabel.setText("Time: "+methods.getTimeSeconds());
                            if (methods.getTimeSeconds() <= 0) {

                                //Jeśli stan konta jest ujemny otrzymuje sie 100$
                                if (methods.getMoney() < 0) {
                                    money_viev.setStyle("-fx-font-size: 32px;-fx-font-weight: bold;");
                                    methods.setMoney(100);
                                    money_viev.setText("Money: " + methods.getMoney());
                                }

                                timeline.stop();
                            }
                        }));
                timeline.playFromStart();
            });

            VBox vb = new VBox(20);
            vb.setAlignment(Pos.CENTER);
            vb.setPrefWidth(scene.getWidth());
            vb.setLayoutY(30);
            vb.getChildren().addAll(button, timerLabel);

            ImageView background = new ImageView(MainObject.background);

            root.getChildren().addAll(background,vb);

            shop_stage.setScene(scene);
            shop_stage.show();
        });

        ImageView save = new ImageView(new Image("Image/save.png"));
        methods.createImageViev(480, 262, 0.7, 0.7, save);
        save.setOnMouseClicked(event_save -> {
            try {
                methods.zapis(String.valueOf(methods.getMoney()), "konto.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        /***
         * Utworzenie napisu który wyświetla aktualny czas
         */

        Font CLOCK_FONT = Font.font("Arial", FontWeight.BOLD, 20);

        try {
            real_time = new Label();
            real_time.setFont(CLOCK_FONT);
            real_time.setTextFill(Color.GREEN);
            primaryStage.setOnCloseRequest(event -> {
                real_timer.stop();
                Platform.exit();
            });
            real_timer = new Real_Timer();
            real_timer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageView backgroundViev = new ImageView(background);

        root1.getChildren().addAll(backgroundViev, rate1, rate2, rate3, graph, shop, save, start, money_viev, real_time);

        setXYImageViev(162, 80, 0);
        setXYImageViev(91 + 162, 80, 1);
        setXYImageViev(182 + 162, 80, 2);

        primaryStage.setResizable(false);
        primaryStage.setScene(scene_main);
        primaryStage.setTitle(MainObject.title);
        primaryStage.show();
    }

    public void setXYImageViev(int X, int Y, int i)  {
        imageViews[i].setLayoutX(X);
        imageViews[i].setLayoutY(Y);
        root1.getChildren().add(imageViews[i]);
    }

    private class Real_Timer extends AnimationTimer {
        @Override
        public void handle(long now) {
            LocalTime localTime = LocalTime.now();
            String time_value = localTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM));
            real_time.setText(time_value);
        }
    }

}