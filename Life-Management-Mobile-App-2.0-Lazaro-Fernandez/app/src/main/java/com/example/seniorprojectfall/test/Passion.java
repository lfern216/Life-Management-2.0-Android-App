package com.example.seniorprojectfall.test;

/**
 * Created by lazaro on 9/28/17.
 */

public class Passion {

    private String name;
    private int imageSource;

    public Passion (int imageSource, String name) {
        this.name = name;
        this.imageSource = imageSource;
    }

    public String getName() {
        return name;
    }

    public int getImageSource() {
        return imageSource;
    }
}
