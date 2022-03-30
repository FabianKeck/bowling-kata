import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game {
    private class Frame {
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
            return first + second == 10 && ! isStrike();
        }

        public int getFirst() {
            return first;
        }

        public boolean isStrike() {
            return first == 10;
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
        int sum = frames.subList(0,10).stream().mapToInt(Frame::getTotal).sum();
        int bonus = getBonus(frames);
        return sum + bonus;
    }

    private List<Frame> extractFrames() {
        List<Frame> frames = new ArrayList<>();
        for (int i = 0; i < rolls.size(); i++) {
            if (rolls.get(i) == 10) {
                frames.add(new Frame(10));
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
        for (int i = 0; i < frames.size() && i < 10; i++) {
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
