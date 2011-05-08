package com.rreeves.game;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Stack;

/*
  EscapeMaze - finds a path from start to end using a BFS algorithm.

 */
public class EscapeMaze {

    private VertexColor []mVisited;
    private int []mParents;//mParents[vertex] is the parent of vertex.

    private GameGraph mGraph;

    public EscapeMaze(SpotType [][]board) {
        mGraph = new GameGraph();
        initGraph(board);

        int numberVertices = mGraph.numberVertices();
        mParents = new int[numberVertices];
        mVisited = new VertexColor[numberVertices];

        for (int i = 0; i < numberVertices; ++i) {
            mParents[i] = -1;
            mVisited[i] = VertexColor.WHITE;
        }
    }

    /*
      Initializes mGraph with board.

      Each spot on the board is a vertex.
      Each move from a spot is an edge.
      Spots can only move to their adjacent neighbors.
      No diagonal moves allowed.
     */
    public void initGraph(SpotType [][]board) {
        //Create vertices
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                mGraph.addVertex(board[row][col], row, col);
            }
        }
        //Create edges
        int i = 0;
        int j = 0;
        int numRows = board.length;
        int numCols = board[0].length;

        //Top left
        mGraph.addEdge(0, 1);
        mGraph.addEdge(0, numCols);

        //Top right
        mGraph.addEdge(numCols-1, numCols-2);
        mGraph.addEdge(numCols-1, numCols*2 - 1);

        //Bottom left
        int x = numRows * numCols - numCols;
        mGraph.addEdge(x, x+1);
        int y = (numRows-2) * numCols;
        mGraph.addEdge(x, y);

        //Bottom right
        x = numCols*numRows - 1;
        mGraph.addEdge(x, x-1);
        mGraph.addEdge(x, numCols*(numRows-1)-1);

        int col = 0;
        int row = 0;

        //Rop row
        for (col = 1; col < numCols-1; col++) {
            mGraph.addEdge(col, col-1);
            mGraph.addEdge(col, col+1);
            mGraph.addEdge(col, col + numCols);
        }

        //Bottom row
        x = numRows * numCols - numCols;
        for (col = 1; col < numCols-1; col++) {
            mGraph.addEdge(col+x, col+x-1);
            mGraph.addEdge(col+x, col+x+1);
            mGraph.addEdge(col+x, col+x-numCols);
        }

        //Left column
        int startRow = numCols;
        int endRow = (numRows-2) * numCols;

        for (row = startRow; row <= endRow ; row += numCols) {
            mGraph.addEdge(row, row - numCols);
            mGraph.addEdge(row, row + numCols);
            mGraph.addEdge(row, row+1);
        }

        //Right column
        startRow = numCols * 2 - 1;
        endRow = (numRows-1) * numCols - 1;

        for (row = startRow; row <= endRow; row += numCols) {
            mGraph.addEdge(row, row - numCols);
            mGraph.addEdge(row, row + numCols);
            mGraph.addEdge(row, row-1);
        }

        //Inner spots (row 1, col 1) to (last row -1, last col -1)
        //4 edges each
        for (row = 1; row < numRows-1; row++) {
            for (col = 1; col < numCols-1; col++) {
                int t = row * numCols + col;

                mGraph.addEdge(t, t+1);
                mGraph.addEdge(t, t-1);
                mGraph.addEdge(t, t-numCols);
                mGraph.addEdge(t, t+numCols);
            }
        }
    }

    /*
      Prints the path from start to end.
      This is accomplished by navigating from the
      end vertex to start vertex in the parent array.
      Each vertice is pushed onto a stack.

      Popping vertices from stack creates path start to end
     */
    private void printPath(int endVertex) {
        Stack<Integer> stack = new Stack<Integer>();

        int i = endVertex;

        while (mParents[i] != -1) {
            stack.push(mParents[i]);
            i = mParents[i];
        }

        int row = 0;
        int col = 0;

        System.out.println("Shortest path out of maze is: ");
        StringBuilder msg = new StringBuilder();

        while (!stack.isEmpty()) {
            i = stack.pop();

            msg.setLength(0);
            msg.append("Row ");
            msg.append(mGraph.getVertexRow(i));
            msg.append(" Col ");
            msg.append(mGraph.getVertexCol(i));

            System.out.println(msg);
        }
    }

    /*
      Prints the shortest path from startVertex to end.
      1) Push startVertex on a queue.
      2) Use a BFS graph traversal algorithm to visit empty
      spots adjacent to the current position.
      3) Mark nodes with colors WHITE/GREY/BLACK in a visited array.
      This prevents processing the same node twice. The GREY color is
      a formality.
      4) As vertices are added to the queue, mark their parent in the parent array.
      4) When the end is reached, print the path using the parent array.

     */
    public void printShortestPath(int startVertex) {
        if (mGraph.getVertexType(startVertex) != SpotType.START)
            return;

        Queue<Integer> vertexQueue = new LinkedList<Integer>();
        vertexQueue.add(startVertex);

        while (!vertexQueue.isEmpty()) {
            Integer from = vertexQueue.poll();
            mVisited[from] = VertexColor.GREY;

            //Process edges
            ListIterator<Integer> edges = mGraph.getEdges(from);
            while (edges.hasNext()) {
                Integer to = edges.next();

                if (mVisited[to] == VertexColor.WHITE &&
                    mGraph.getVertexType(to) == SpotType.EMPTY) {//Only process this vertex once.

                    vertexQueue.add(to);

                    mParents[to] = from;
                }

                if (mGraph.getVertexType(to) == SpotType.END) {//Found path, build and return.
                    mParents[to] = from;
                    printPath(to);
                    return;
                }
            }
            mVisited[from] = VertexColor.BLACK;
        }

        System.out.println("No path exists");
    }
}