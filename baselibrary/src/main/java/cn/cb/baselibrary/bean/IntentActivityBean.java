package cn.cb.baselibrary.bean;

import androidx.annotation.NonNull;

import java.util.Random;

public class IntentActivityBean {
    private int id;
    private String name;
    private String title;
    private Callback callback;
    private Random random = new Random();

    public IntentActivityBean() {
        this.id = random.nextInt();
    }

    public IntentActivityBean(@NonNull String name, Callback callback) {
        this.id = random.nextInt();
        this.name = name;
        this.callback = callback;
    }

    public IntentActivityBean(@NonNull String name, @NonNull String title, Callback callback) {
        this.id = random.nextInt();
        this.name = name;
        this.title = title;
        this.callback = callback;
    }

    public String getName() {
        if (name == null) {
            return getTitle();
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        if (title == null) {
            return getName();
        }
        return title;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    interface Callback {
        boolean todo(Object obj);
    }
}
