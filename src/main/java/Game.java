import java.util.ArrayList;
import java.util.List;

public class Game {

    public static final Integer MAX_PINS = 10;
    public static final int MAX_FRAMES = 10;

    private static class Frame {

        List<Integer> rolls =new ArrayList<>();

        public Frame(int first, int second) {
            rolls.add(first);
            rolls.add(second);
        }
        public static Frame strike() {
            return new Frame(MAX_PINS, 0);
        }

        public boolean isSpare() {
            return MAX_PINS.equals(getTotal()) && ! isStrike();
        }

        public int getFirst() {
            return rolls.get(0);
        }

        public boolean isStrike() {
            return MAX_PINS.equals(rolls.get(0));
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

//    private class Frames extends ArrayList<Frame> {
//        public void addRoll() {
//            get(size() - 1).roll();
//        }
//    }

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
            if (MAX_PINS.equals(rolls.get(i))) {
                frames.add(Frame.strike());
            } else {
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
