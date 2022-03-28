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


        rollZeros(18);

        Assertions.assertThat(this.game.getScore())
                .isEqualTo(14 + 4);
    }

    private void rollZeros(int numRolls) {
        for (int i = 0; i < numRolls; i++) {
            this.game.roll(0);
        }
    }

}