package RandomChooserProject;

import java.lang.Math;

public class RandomChooser {
    
    private Input input;
    private int impact, size;
    private String name;

    public RandomChooser(String name, int impact, Input input) {

        this.impact = impact;
        this.name = name;
        this.input = input;
        this.size = this.input.getList().size();

    }

    private double root(double number, int root) {

        double actualRoot = Math.pow(root, -1);
        return ((Math.pow(number, actualRoot)));

    }

    private int chooseRandomNumber() {

        int [] field = new int[50];
        int i = 0, sum = 0;
        double average = 0;
        while (i < field.length) {
            int randomNumber = (int) (1 + Math.random() * Math.pow(size, impact) / i);
            field[i] = randomNumber;
            i++;
        }

        i = 0;
        while (i < field.length) {
            sum += field[i++];
        }

        average = sum / field.length;

        return (int) Math.floor(this.root(average, impact));

    }

    public String toString() {

        return this.input.getList().find(this.chooseRandomNumber());

    }

}
