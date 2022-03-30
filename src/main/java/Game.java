import java.util.ArrayList;
import java.util.List;

public class Game {

    public static final int MAX_PINS = 10;
    public static final int MAX_FRAMES = 10;

    private static class Frame {
        private final int first;
        private final int second;

        public Frame(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public Frame(int first) {
            this (first, 0);
        }

        public boolean isSpare() {
            return first + second == MAX_PINS && ! isStrike();
        }

        public int getFirst() {
            return first;
        }

        public boolean isStrike() {
            return first == MAX_PINS;
        }

        public int getTotal() {
            return first + second;
        }
    }
    private final List<Integer> rolls = new ArrayList<>();

    public void roll(int pins) {
        rolls.add(pins);
    }

    public int getScore() {
        List<Frame> frames = extractFrames();
        int sum = frames.subList(0, MAX_FRAMES).stream().mapToInt(Frame::getTotal).sum();
        int bonus = getBonus(frames);
        return sum + bonus;
    }

    private List<Frame> extractFrames() {
        List<Frame> frames = new ArrayList<>();
        for (int i = 0; i < rolls.size(); i++) {
            if (rolls.get(i) == MAX_PINS) {
                frames.add(new Frame(MAX_PINS));
            } else {
                if (rolls.size() < i + 2) {
                    continue;
                }
                frames.add(new Frame(rolls.get(i), rolls.get(i + 1)));
                i++;
            }
        }

        return frames;
    }

    private int getBonus(List<Frame> frames) {
        int bonus = 0;
        for (int i = 0; i < frames.size() && i < MAX_FRAMES; i++) {
            if (frames.get(i).isSpare()) {
                bonus += frames.get(i + 1).getFirst();
            } else if (frames.get(i).isStrike()) {
                if (frames.get(i + 1).isStrike()){
                    bonus += frames.get(i + 1).getFirst() + frames.get(i + 2).getFirst();
                } else {
                    bonus += frames.get(i + 1).getTotal();
                }
            }
        }
        return bonus;
    }
}
