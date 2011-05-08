package com.rreeves;

import java.util.ArrayList;

public class Graph {
    private ArrayList<Vertex> mVertices = new ArrayList<Vertex>();
    private int mVertexCount;

    //Adds vertex to graph and returns it's index.
    public int addVertex(String name) {
        mVertices.add(new Vertex(mVertexCount, name));
        return mVertexCount++;
    }

    public void addEdge(int from, int to) {
        Vertex v = mVertices.get(from);
        v.addAdjacentVertice(to);

        Vertex toV = mVertices.get(to);
        toV.incrementInDegree();
    }

    public Vertex getVertex(int index) {
        return mVertices.get(index);
    }

    public int numberVertices() {
        return mVertices.size();
    }

    public String getVertexName(int index) {
        return mVertices.get(index).getName();
    }

}