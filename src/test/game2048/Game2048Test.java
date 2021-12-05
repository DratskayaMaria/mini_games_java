package game_2048;

import game_2048.Game2048;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Game2048Test {
    private int getSizeTiles(Game2048 game)
    {
        int count = (int)Math.sqrt(Stream.of(game.tiles).mapToInt(m -> m.length).sum());
        int size = 0;
        for (int i = 0; i < count; i++)
        {
            for (int j = 0; j < count; j++)
            {
                if (game.tiles[i][j] != null)
                {
                    size++;
                }
            }
        }
        return size;
    }


    @Test
    void movesAvailable() {
        Game2048 game = new Game2048();
        game.startGame();
        assertTrue(game.movesAvailable());
    }


    @Test
    void startGame() {
        Game2048 game = new Game2048();
        game.startGame();
        assertEquals(getSizeTiles(game), 2);
    }

    @Test
    void gameState()
    {
        Game2048 game = new Game2048();
        assertEquals(game.gamestate, Game2048.State.start);
        game.startGame();
        assertEquals(game.gamestate, Game2048.State.running);
    }

    @Test
    void addRandomTile() {
        Game2048 game = new Game2048();
        game.startGame();
        int size = getSizeTiles(game);
        game.addRandomTile();
        assertEquals(size + 1, getSizeTiles(game));
    }
}