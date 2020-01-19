package pl.twisz;

import pl.twisz.animals.Animal;
import pl.twisz.animals.Giraffe;
import pl.twisz.animals.Lion;
import pl.twisz.animals.Zebra;
import pl.twisz.plants.Acacia;
import pl.twisz.plants.Baobab;
import pl.twisz.plants.Grass;
import pl.twisz.plants.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Savanna {
    Cell[][] board;
    private final int rows;
    private final int cols;
    private int daysPassed = 0;
    private int lionsCount = 0;
    private int giraffeCount = 0;
    private int zebraCount = 0;
    private int animalsLeft = 0;
    private int acaciaCount = 0;
    private int baobabCount = 0;

    public int getRows() {
        return rows;
    }
    public int getCols() {
        return cols;
    }

    public int getLionsCount() {
        return lionsCount;
    }
    public int getGiraffeCount() {
        return giraffeCount;
    }
    public int getZebraCount() {
        return zebraCount;
    }
    public int getAcaciaCount() {
        return acaciaCount;
    }
    public int getBaobabCount() {
        return baobabCount;
    }

    public int getDaysPassed() {
        return daysPassed;
    }

    public int getAnimalsLeft() {
        return animalsLeft;
    }
    public void setAnimalsLeft(int animalsLeft) {
        this.animalsLeft = animalsLeft;
    }

    //constructor - initiating board with given size and number of trees
    public Savanna(int rows, int cols, int trees) {
        this.rows = rows;
        this.cols = cols;
        Random random = new Random();
        board = new Cell[rows][cols];

        if (trees > rows * cols) {
            trees = rows * cols;
        }

        // applying trees to random cells of the board:
        for (int i = 0; i < trees; i++) {
            int r, c;
            do {
                r = random.nextInt(rows);
                c = random.nextInt(cols);
            } while (board[r][c] != null);

            //type of tree being chosen randomly:
            Tree tree;
            boolean t = random.nextBoolean();
            if (t) {
                tree = new Acacia();
                this.acaciaCount += 1;
            } else {
                tree = new Baobab();
                this.baobabCount += 1;
            }
            board[r][c] = Cell.withTree(tree);
        }

        //filling remaining cells with grass:
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == null) {
                    board[i][j] = Cell.withGrass(new Grass());
                }
            }
        }

        //applying rowNumber and columnNumber for cells:
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++){
                board[i][j].setRowNumber(i);
                board[i][j].setColumnNumber(j);
            }
        }
    }

    // add animals to Savanna board
    public void populateSavanna(int predators, int herbivores) {
        this.lionsCount = predators;
        Random random = new Random();

        // distributing predators
        for (int i = 0; i < predators; i++) {
            int r, c;
            r = random.nextInt(this.rows);
            c = random.nextInt(this.cols);
            Cell c1 = this.board[r][c];
            Animal predator = new Lion();
            c1.getAnimals().add(predator);
        }
        // distributing herbivores
        for (int j = 0; j < herbivores; j++) {
            int r, c;
            r = random.nextInt(this.rows);
            c = random.nextInt(this.cols);
            Cell c2 = this.board[r][c];
            //type of herbivore being chosen randomly:
            Animal herbivore;
            boolean h = random.nextBoolean();
            if (h) {
                herbivore = new Giraffe();
                this.giraffeCount += 1;
            } else {
                herbivore = new Zebra();
                this.zebraCount += 1;
            }
            c2.getAnimals().add(herbivore);
        }
        this.animalsLeft = this.lionsCount+this.zebraCount+this.giraffeCount;
    }

    public void lookForDead(){
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Cell currentCell = board[r][c];
                currentCell.getAnimals().removeIf(a -> a.checkStarvingDays() >= 5);
            }
        }
    }

    /* animal changing cell after each day
            - new coordinates chosen randomly
            - no further than 1 cell around current cell
            - animal can stay in the same cell
            - cells with fire are excluded */
    public void animalMigration() {

        Random random = new Random();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Cell currentCell = board[r][c];

                int deltaR;
                int deltaC;
                int newR;
                int newC;

                /* below list will be filled with animals
                which are going to stay in current cell.
                This list is replacing previous 'animal' list
                for current cell, after iteration for this cell
                is completed.
                 */
                List<Animal> newListOfAnimals = new ArrayList<>();

                // iterate through animal list
                for (int i = 0; i < currentCell.getAnimals().size(); i++) {

                    // set new row number
                    do {
                        do {
                            deltaR = random.nextInt(3) - 1;
                        } while (r + deltaR < 0 || r + deltaR >= rows);
                        newR = r + deltaR;

                        // set new column number
                        do {
                            deltaC = random.nextInt(3) - 1;
                        } while (c + deltaC < 0 || c + deltaC >= cols);
                        newC = c + deltaC;
                    } while (board[newR][newC].getWeather() == Weather.FIRE); // only cell without fire that day


                    /* - moving animal to list of another cell or

                     */
                    if (newR != r || newC != c) {
                        board[newR][newC].getAnimals()
                                .add(currentCell.getAnimals().get(i));
                    } else {
                        newListOfAnimals
                                .add(currentCell.getAnimals().get(i));
                    }
                }
                currentCell.setAnimals(newListOfAnimals);
            }
        }
    }

    public void showSavannaBoard() {
        System.out.println("-------");
        System.out.println("DAY " + daysPassed);
        System.out.println("-------");
        System.out.println();
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                Cell c = this.board[i][j];
                System.out.print(c.toString());
            }
            System.out.println();
            System.out.println();
            System.out.println();
        }
        for (int i = 0; i < this.getCols(); i++) {
            System.out.print("----------");
        }
        System.out.println();
    }

    public void oneDay() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Cell currentCell = board[r][c];

                Weather.randomizeWeatherConditions(currentCell);
                Weather currentWeather = currentCell.getWeather();
                switch (currentWeather) {
                    case SUN:
                        currentCell.getPlant().grow();
                        currentCell.animalsEat();
                        break;
                    case RAIN:
                        currentCell.getPlant().afterRain();
                        currentCell.animalsEat();
                        break;
                    case FIRE:
                        currentCell.animalsRunFromFire(this);
                        currentCell.getPlant().afterFire();
                        break;
                }
            }
        }
    }

    public void nextDayPassed() {
        this.daysPassed += 1;
    }

}
