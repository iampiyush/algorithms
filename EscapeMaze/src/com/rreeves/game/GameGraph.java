package com.rreeves.game;

import java.util.ListIterator;
import java.util.LinkedList;
import java.util.ArrayList;

/*
  Graph representing the game.
 */
class GameGraph {

    private class Vertex {
        public int index;
        public LinkedList<Integer> edges = new LinkedList<Integer>();
        public SpotType type;
        public int row;//Row and column of where this vertex came from in the game.
        public int col;

        public Vertex(int index, SpotType type, int row, int col) {
            this.index = index;
            this.type = type;
            this.row = row;
            this.col = col;
        }
    }

    private ArrayList<Vertex> mVertices = new ArrayList<Vertex>();

    public int numberVertices() {
        return mVertices.size();
    }
    public void addVertex(SpotType type, int row, int col) {
        mVertices.add(new Vertex(mVertices.size(), type, row, col));
    }

    public ListIterator<Integer> getEdges(int index) {
        return mVertices.get(index).edges.listIterator();
    }

    //returns this board row this vertex represents
    public int getVertexRow(int index) {
        Vertex v = mVertices.get(index);
        return v.row;
    }

    //returns the board columm this vertex represents
    public int getVertexCol(int index) {
        Vertex v = mVertices.get(index);
        return v.col;
    }

    public SpotType getVertexType(int index) {
        return mVertices.get(index).type;
    }

    public void addEdge(int from, int to) {
        mVertices.get(from).edges.add(to);
    }

    //For debugging
    public void print() {
        int length = mVertices.size();
        for (int i = 0; i < length; ++i) {
            Vertex vertex = mVertices.get(i);
            ListIterator<Integer> edges = vertex.edges.listIterator();

            StringBuilder msg = new StringBuilder(i + "|");

            while (edges.hasNext()) {
                msg.append("->" + edges.next());
            }
        }
    }
}
