import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Player;
import ru.netology.exception.NonRegisteredException;
import ru.netology.game.Game;

public class GameTest {

    Game game = new Game();

    Player player1 = new Player(1, "Lolo123", 50);
    Player player2 = new Player(2, "QWERTY", 100);
    Player player3 = new Player(3, "Champion", 2_000);
    Player player4 = new Player(4, "NewGamer", 50);

    @Test

    public void shouldCompareStrength() {

        game.register(player2);
        game.register(player3);

        int expected = 2;
        int actual = game.round(player2.getName(), player3.getName());

        Assertions.assertEquals(expected, actual);
    }

    @Test

    public void shouldCompareIfEqualStrength() {

        game.register(player1);
        game.register(player4);

        int expected = 0;
        int actual = game.round(player1.getName(), player4.getName());

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldCompareAndReturn1() {

        game.register(player3);
        game.register(player4);

        int expected = 1;
        int actual = game.round(player3.getName(), player4.getName());

        Assertions.assertEquals(expected, actual);
    }

    @Test

    public void shouldNotRegisteredExceptionPlayer1() {
        game.register(player1);
        game.register(player2);

        Assertions.assertThrows(NonRegisteredException.class, () -> {
            game.round(player3.getName(), player1.getName());
        });
    }

    @Test

    public void shouldNotRegisteredExceptionPlayer2() {
        Game registeredPlayers = new Game();
        registeredPlayers.register(player3);
        registeredPlayers.register(player4);

        Assertions.assertThrows(NonRegisteredException.class, () -> {
            registeredPlayers.round(player3.getName(), player1.getName());
        });
    }

    @Test
    public void shouldNotRegisteredExceptionPlayer1AndPlayer2() {
        Game registeredPlayers = new Game();
        registeredPlayers.register(player1);
        registeredPlayers.register(player2);

        Assertions.assertThrows(NonRegisteredException.class, () -> {
            registeredPlayers.round(player3.getName(), player4.getName());
        });
    }

}
