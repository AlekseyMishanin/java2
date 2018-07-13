package java2.lesson2.DOP_DZ_2;

import java2.lesson2.DZ.Display;

public class Body implements Display {
    private int weight;
    private double height;
    public Body next;

    public Body(int weight, double height, Body next) {
        this.weight = weight;
        this.height = height;
        this.next = next;
    }

    public int getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public void display(){
        System.out.format("%d\t%.2f",weight, height);
    }
}
