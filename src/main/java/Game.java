import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Integer> rolls = new ArrayList<>();

    public void roll(int pins) {
        rolls.add(pins);
    }

    public int getScore() {
        int sum = rolls.stream().mapToInt(i -> i).sum();
        int bonus = 0;
        for (int i = 2; i < rolls.size(); i += 2) {
            if (rolls.get(i - 1) + rolls.get(i - 2) == 10) {
                bonus += rolls.get(i);
            }
        }
        return sum + bonus;
    }
}
