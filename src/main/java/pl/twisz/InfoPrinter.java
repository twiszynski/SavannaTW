package pl.twisz;

import pl.twisz.animals.Animal;
import pl.twisz.animals.Lion;
import pl.twisz.animals.Zebra;

public class InfoPrinter {

    private Savanna savanna;

    public InfoPrinter(Savanna sav) {
        this.savanna = sav;
    }

    public void printHello() {
        System.out.println("##########################################################");
        System.out.println();
        System.out.println("Hello!");
        System.out.println("Congrats! You've just started your own savanna simulator!");
        System.out.println("Take a look below and see what happened day after day :)");
        System.out.println();
    }

    public void printInitialElementsInfo() {
        int size = savanna.getCols()*savanna.getRows();
        int trees = savanna.getAcaciaCount()+savanna.getBaobabCount();
        int allAnimals = savanna.getGiraffeCount()+savanna.getZebraCount()+savanna.getLionsCount();
        System.out.println("Initial state:");
        System.out.println("Savanna size is "+size+" cells.");
        System.out.println("There is "+trees+" different trees located all over the area.");
        System.out.println();
        System.out.println(allAnimals + " animals on start:");
        System.out.println(savanna.getGiraffeCount() + " Giraffe(s)");
        System.out.println(savanna.getZebraCount() + " Zebra(s)");
        System.out.println(savanna.getLionsCount() + " Lion(s)");
        System.out.println();
    }

    public void printLegend() {
        System.out.println("\tLegend:");
        System.out.println("\t A \t\t-> \tAcacia");
        System.out.println("\t B \t\t-> \tBaobab");
        System.out.println("\t ~ \t\t-> \tElephant Grass");
        System.out.println("\t(x) \t-> \tnumber of herbivores");
        System.out.println("\t[x] \t-> \tnumber of predators");
        System.out.println("\t<^^^^> \t-> \tfire");
        System.out.println();
    }

    public void printAnimalsLeft() {
        int lions = 0;
        int zebras = 0;
        int giraffes = 0;

        for (int i = 0; i < savanna.getRows(); i++) {
            for (int j = 0; j < savanna.getCols(); j++) {
                Cell currentCell = savanna.board[i][j];
                for (Animal a : currentCell.getAnimals()){
                    if (a instanceof Lion) {
                        lions++;
                    } else if (a instanceof Zebra) {
                        zebras++;
                    } else {
                       giraffes++;
                    }
                }
            }
        }
        savanna.setAnimalsLeft(lions + zebras + giraffes);
        System.out.println("-> Giraffes: "+giraffes
                +", Zebras: "+zebras
                +", Lions: "+lions);
    }

    public void showRainRange(){
        int cellsWithRain = 0;
        for (int i = 0; i < savanna.getRows(); i++) {
            for (int j = 0; j < savanna.getCols(); j++) {
                Cell currentCell = savanna.board[i][j];
                if (currentCell.getWeather() == Weather.RAIN) {
                    cellsWithRain++;
                }
            }
        }

        if (cellsWithRain != 0) {
            System.out.println("'!'!|!' -> Rainfall has reached " +cellsWithRain+ " area(s) this day!");
        } else {
            System.out.println(" -> It is very dry on the savanna these days...");
        }
        System.out.println();
    }

    public void simulationByDaysCompleted() {
        System.out.println();
        System.out.println("Your "+savanna.getDaysPassed()+"-days simulation has been completed!");
        System.out.println("Go over the results and see how did it look like.");
        System.out.println("See you next time!");
    }

    public void simulationWhileAnimalsCompleted() {
        System.out.println();
        System.out.println("Simulation has been completed! Animals on your savanna survived "+(savanna.getDaysPassed()-1)+" days!");
        System.out.println("Go over the results and see how did it look like.");
        System.out.println("See you next time!");
    }

}
