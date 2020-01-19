package pl.twisz;

public class App {
    public static void main(String[] args) {

        // creating savanna board with plants
        Savanna s1 = new Savanna(5, 7, 17);
        Savanna s2 = new Savanna(3, 3, 4);
        // populating savanna with animals
        s1.populateSavanna(3, 16);
        s2.populateSavanna(0, 5);
        // using SavannaSimulator object for two modes of simulation
        SavannaSimulator sim = new SavannaSimulator();
        sim.simulateByGivenDays(s1, 17);
        //sim.simulateWhileAnimalsLive(s2);
    }
}
