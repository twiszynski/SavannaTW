package pl.twisz;

import pl.twisz.animals.Animal;
import pl.twisz.animals.Herbivore;
import pl.twisz.plants.Grass;
import pl.twisz.plants.Plant;
import pl.twisz.plants.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cell {
    private int rowNumber;
    private int columnNumber;
    private final Plant plant;
    private Weather weather;
    private List<Animal> animals = new ArrayList<Animal>();
    private List<Animal> futureAnimals = new ArrayList<Animal>();

    public Cell(Plant plant){
        this.plant = plant;
    }
    public static Cell withGrass(Grass grass){
        return new Cell(grass);
    }
    public static Cell withTree(Tree tree){
        return new Cell(tree);
    }

    public Plant getPlant() {
        return plant;
    }

    public List<Animal> getAnimals() {
        return animals;
    }
    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
    public List<Animal> getFutureAnimals() {
        return futureAnimals;
    }

    public int getRowNumber() {
        return rowNumber;
    }
    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }
    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public Weather getWeather() {
        return weather;
    }
    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public void animalsEat() {
        if (this.animals.size() > 0) {
            /*
            initialize new list with object from 'animals' and assign this reference to 'futureAnimals'
            'futureAnimals' list is used in Lion.eat method
            */
            List<Animal> animalsBackup = new ArrayList<Animal>(animals);
            this.futureAnimals = animalsBackup;
            for (Animal a : this.animals) {
                a.eat(this);
            }
            this.animals = futureAnimals;
        }
    }
    public void animalsRunFromFire(Savanna savanna) {
        if (this.getAnimals().size() > 0) {
            Random random = new Random();
            int r = this.getRowNumber();
            int c = this.getColumnNumber();
            int deltaR;
            int deltaC;
            int newR;
            int newC;

            // iterate through animal list in current cell
            for (int i = 0; i < this.getAnimals().size(); i++) {
                /*
                animals can't stay in the same cell,
                thus loop will be repeated till deltas are different
                from zero and new index is not out of bounds
                */
                do {
                    // set new row number
                    do {
                        deltaR = random.nextInt(3) - 1;
                    } while (deltaR == 0
                            || (r + deltaR < 0 || r + deltaR >= savanna.getRows()));
                    newR = r + deltaR;

                    // set new column number
                    do {
                        deltaC = random.nextInt(3) - 1;
                    } while (deltaC == 0
                            || (c + deltaC < 0 || c + deltaC >= savanna.getCols()));
                    newC = c + deltaC;
                } while (savanna.board[newR][newC]
                        .getWeather() == Weather.FIRE); // find cell without fire that day
                //add animal to new cell
                savanna.board[newR][newC].getAnimals()
                        .add(this.getAnimals().get(i));
            }
            //clear the list in current cell since there is no animals left
            this.getAnimals().clear();
        }
    }

    @Override
    public String toString() {
        if (this.weather == Weather.FIRE) {
            return "  <^^^^>  ";
        } else if (this.animals.size() == 0) {
            return ("    " + this.plant.toString() + "     ");
        } else {
            int h1 = 0;
            int p1 = 0;
            for (Animal a : this.animals){
                if (a instanceof Herbivore){
                    h1++;
                } else {
                    p1++;
                }
            }

            String a = "";
            String b = "";
            if (h1 > 0 && p1 > 0) {
                a = "("+h1+")["+p1+"]  ";
                b = " ";
            } else if (h1 > 0) {
                a = "("+h1+")   ";
                b = "   ";
            } else if (p1 > 0) {
                a = "["+p1+"]   ";
                b = "   ";
            }
            return (b + this.plant.toString() + a);
        }
    }
}
