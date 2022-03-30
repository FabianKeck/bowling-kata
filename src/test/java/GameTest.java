import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GameTest {

    private final Game game = new Game();

    @Test
    public void onePin() {

        this.game.roll(1);
        rollZeros(19);

        Assertions.assertThat(this.game.getScore())
                .isEqualTo(1);
    }

    @Test
    public void twoTimesOne() {

        this.game.roll(1);
        this.game.roll(1);

        rollZeros(18);

        Assertions.assertThat(this.game.getScore())
                .isEqualTo(1 + 1);
    }

    @Test
    public void spare() {

        this.game.roll(5);
        this.game.roll(5);
        this.game.roll(4);


        rollZeros(17);

        Assertions.assertThat(this.game.getScore())
                .isEqualTo(14 + 4);
    }

    @Test
    public void twoSpares() {
        this.game.roll(5);
        this.game.roll(5);
        this.game.roll(4);
        this.game.roll(6);
        this.game.roll(3);

        rollZeros(15);

        Assertions.assertThat(this.game.getScore())
                .isEqualTo(14 + 13 + 3);
    }

    @Test
    public void strike() {
        this.game.roll(10);
        this.game.roll(5);
        this.game.roll(3);

        rollZeros(16);

        Assertions.assertThat(this.game.getScore())
                .isEqualTo(18 + 8);
    }

    @Test
    public void twoStrikes() {
        this.game.roll(10);
        this.game.roll(10);
        this.game.roll(5);
        this.game.roll(3);

        rollZeros(16);

        Assertions.assertThat(this.game.getScore())
                .isEqualTo(25 + 18 + 8);
    }

    @Test
    public void allFours() {
        int numPins = 4;
        rollTimes(20, numPins);

        Assertions.assertThat(this.game.getScore())
                .isEqualTo(20 * numPins);
    }

    @Test
    public void allStrikes() {
        for (int i = 0; i < 12; i++) {
            this.game.roll(10);
        }


        Assertions.assertThat(this.game.getScore())
                .isEqualTo(300);
    }

    @Test
    public void allSpares() {
        for (int i = 0; i < 21; i++) {
            this.game.roll(5);
        }


        Assertions.assertThat(this.game.getScore())
                .isEqualTo(10 * 15);
    }



    private void rollZeros(int numRolls) {
        rollTimes(numRolls, 0);
    }

    private void rollTimes(int numRolls, int numPins) {
        for (int i = 0; i < numRolls; i++) {
            this.game.roll(numPins);
        }
    }
}