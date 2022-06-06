import java.util.Random;

public enum Names {
    Юрий,
    Анатолий,
    Ирина,
    Василий,
    Станислав,
    Егор,
    Светлана,
    Сергей,
    Андрей,
    Максим;

    private static final Random random = new Random();

    public static Names randomNames() {
        Names[] names = values();
        return names[random.nextInt(names.length)];
    }
}
