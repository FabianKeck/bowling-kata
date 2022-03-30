import java.util.ArrayList;
import java.util.List;

public class Game {

    public static final Integer MAX_PINS = 10;
    public static final int MAX_FRAMES = 10;

    private final Frames frames = new Frames();

    public void roll(int pins) {
        frames.addRoll(pins);
    }

    public int getScore() {
        int sum = frames.subList(0, MAX_FRAMES).stream().mapToInt(Frame::getTotal).sum();
        int bonus = getBonus(frames);
        return sum + bonus;
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

    private static class Frame {

        List<Integer> rolls =new ArrayList<>();

        public Frame(int numPins) {
            roll(numPins);
        }

        public boolean isSpare() {
            return MAX_PINS.equals(getTotal()) && ! isStrike();
        }

        public int getFirst() {
            return rolls.get(0);
        }

        public boolean isStrike() {
            return  !rolls.isEmpty() && MAX_PINS.equals(rolls.get(0));
        }

        public int getTotal() {
            return rolls.stream().mapToInt(i -> i).sum();
        }

        public void roll(int numPins) {
            if (isFinished()) {
                throw new IllegalArgumentException("This Frame is closed");
            }
            rolls.add(numPins);
        }

        private boolean isFinished() {
            return rolls.size() == 2 || isStrike();
        }

    }

    private class Frames extends ArrayList<Frame> {

        public void addRoll(int numPins) {
            if (this.isEmpty() || last().isFinished()) {
                add(new Frame(numPins));
            } else {
                last().roll(numPins);
            }
        }

        private Frame last() {
            return get(size() - 1);
        }
    }

}
