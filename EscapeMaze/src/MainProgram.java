import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import com.rreeves.game.EscapeMaze;
import com.rreeves.game.SpotType;

/*
  Test driver for the EscapeMaze class.

  EscapeMaze finds a path from start to end in a board game.
  The game consists of an nxn board, where each spot on the board
  is either the start, empty, a wall, or the end.

 */
public class MainProgram {

    private static void escapeMazeTest() {
        //Sample board
        SpotType [][]board = { {SpotType.START, SpotType.EMPTY, SpotType.WALL, SpotType.END},
                               {SpotType.WALL, SpotType.EMPTY, SpotType.WALL, SpotType.EMPTY},
                               {SpotType.EMPTY, SpotType.EMPTY, SpotType.WALL, SpotType.EMPTY},
                               {SpotType.WALL, SpotType.EMPTY, SpotType.EMPTY, SpotType.EMPTY},

        };

        EscapeMaze maze = new EscapeMaze(board);
        maze.printShortestPath(0);
    }

    public static void main(String []args) {
        escapeMazeTest();
   }
}