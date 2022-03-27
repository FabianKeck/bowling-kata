public class Game {

    private int count;
    public void roll(int i) {
        count += i;
    }

    public int getScore() {
        return count;
    }
}
