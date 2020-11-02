import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;

import java.awt.*;


class PlaygroundTest {

    private Playground playground;

    @Test
    public void hasWon() {
    playground = new Playground();
    playground.setActivePlayer(1);

    playground.getShipsPlayer1().put(new Point(0,0),new Boat());
    playground.getShipsPlayer1().put(new Point(1,0),new Boat());
    playground.getShipsPlayer1().put(new Point(2,0),new Boat());

    playground.getSunkenShipsPlayer1().put(new Point(0,0),new Hit());
    playground.getSunkenShipsPlayer1().put(new Point(1,0),new Hit());
    playground.getSunkenShipsPlayer1().put(new Point(2,0),new Hit());

    Assertions.assertThat(playground.hasWon()).isEqualTo(true);
    }


    @Test
    void getActivePlayer() {
        playground = new Playground();
        playground.setActivePlayer(2);
        Assertions.assertThat(playground.getActivePlayer()).isEqualTo(2);
    }
}