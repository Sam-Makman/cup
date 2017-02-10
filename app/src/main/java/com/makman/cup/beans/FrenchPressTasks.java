package com.makman.cup.beans;

/**
 * Created by sam on 1/28/17.
 */

public class FrenchPressTasks {

    public static Task[] getTasks(){
        Task[] tasks = new Task[5];
        tasks[0] = new Task("grind", "grind the beans into a medium grind", -1);
        tasks[1] = new Task("bloom",
                "pour 3 oz of water, it should wet the grinds and fill the french press by 2 inches. let sit for 30 seconds",
                30);
        tasks[2] = new Task("steep", "fill the press the rest of the way. let sit for 4 minutes", 240 );
        tasks[3] = new Task("press", "press the french press", -1);
        tasks[4] = new Task("pour", "enjoy your coffee", -1);

        return tasks;
    }
}
