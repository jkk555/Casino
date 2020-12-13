package Main_classes;

import Interface.MainObject;
import javafx.scene.paint.Color;

public class Thread extends java.lang.Thread implements MainObject
{
    public void run() {
        //Migający napis "Money"
        while (true) {
            money_viev.setTextFill(Color.web("FF0033"));
            sleep();
            money_viev.setTextFill(Color.web("FF33FF"));
            sleep();
            money_viev.setTextFill(Color.web("3300FF"));
            sleep();
            money_viev.setTextFill(Color.web("00FF33"));
            sleep();
            money_viev.setTextFill(Color.web("FFFF00"));
            sleep();
            money_viev.setTextFill(Color.web("FF9900"));
            sleep();
        }
    }

    //zatrzymywanie programu na 200 milisekund
    public void sleep()
    {
        try {
            java.lang.Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
