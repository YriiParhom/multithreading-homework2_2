
public class Main {
    public static final int BUYERS = 11;

    public static void main(String[] args) {

        final CarDealer dealer = new CarDealer();
        for (int i = 1; i < BUYERS; i++) {
            new Thread(null, dealer::sellCar, "Покупатель " + Names.randomNames()).start();
        }
        new Thread(null, dealer::receiveCar, "Производитель").start();
    }
}
