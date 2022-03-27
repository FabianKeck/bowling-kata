import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    public void onePin() {
        Game game = new Game();

        game.roll(1);
        for (int i = 0; i < 19; i++) {
            game.roll(0);
        }

        Assertions.assertThat(game.getScore())
                .isEqualTo(1);
    }

    @Test
    public void twoTimesOne() {
        Game game = new Game();

        game.roll(1);
        game.roll(1);

        for (int i = 0; i < 18; i++) {
            game.roll(0);
        }

        Assertions.assertThat(game.getScore())
                .isEqualTo(1 + 1);
    }

    @Test
    public void spare() {
        Game game = new Game();

        game.roll(5);
        game.roll(5);
        game.roll(4);


        for (int i = 0; i < 18; i++) {
            game.roll(0);
        }

        Assertions.assertThat(game.getScore())
                .isEqualTo(14 + 4);
    }


}