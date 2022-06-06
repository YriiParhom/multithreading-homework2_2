import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CarDealer {

    private static final int RECIEVE_TIME = 3000;
    private static final int SELL_TIME = 1000;
    private static final int CARS = 10;
    private final List<Car> cars = new ArrayList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void receiveCar() {
        for (int i = 0; i < CARS; i++) {
            try {
                lock.lock();
                Thread.sleep(RECIEVE_TIME);
                cars.add(new Car("Toyota", "Land Cruiser"));
                System.out.println(Thread.currentThread().getName() + " выпустил 1 " + cars.get(0));
                condition.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            } finally {
                lock.unlock();
            }
        }
    }

    public void sellCar() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            while (cars.size() == 0) {
                System.out.println("Машин нет!");
                condition.await();
            }
            Thread.sleep(SELL_TIME);
            System.out.println(Thread.currentThread().getName() + " уехал на новеньком " + cars.get(0));
            cars.remove(0);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        } finally {
            lock.unlock();
        }
    }
}
