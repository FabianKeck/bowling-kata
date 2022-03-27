import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    public void onePin() {
        Game game = new Game();

        game.roll(1);

        Assertions.assertThat(game.getScore())
                .isEqualTo(1);
    }



}