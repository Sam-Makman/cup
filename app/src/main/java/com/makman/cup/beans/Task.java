package com.makman.cup.beans;

/**
 * Created by sam on 1/28/17.
 */

public class Task {
    private String title;
    private String description;
    private int timer;

    public Task(String title, String description, int timer) {
        this.title = title;
        this.description = description;
        this.timer = timer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
}
