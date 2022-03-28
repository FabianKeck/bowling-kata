import java.util.ArrayList;
import java.util.List;

public class Game {
    private class Frame {
        private final int first;
        private final int second;

        public Frame(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public boolean isSpare() {
            return first + second == 10;
        }

        public int getFirst() {
            return first;
        }
    }
    private final List<Integer> rolls = new ArrayList<>();

    public void roll(int pins) {
        rolls.add(pins);
    }

    public int getScore() {
        int sum = rolls.stream().mapToInt(i -> i).sum();
        List<Frame> frames = extractFrames();
        int bonus = getBonus(frames);
        return sum + bonus;
    }

    private List<Frame> extractFrames() {
        List<Frame> frames = new ArrayList<>();
        for (int i = 2; i < rolls.size(); i += 2) {
            frames.add(new Frame(rolls.get(i - 2), rolls.get(i - 1)));
        }
        return frames;
    }

    private int getBonus(List<Frame> frames) {
        int bonus = 0;
        for (int i = 0; i < frames.size(); i++) {
            if (frames.get(i).isSpare()) {
                bonus += frames.get(i + 1).getFirst();
            }
        }
        return bonus;
    }
}
