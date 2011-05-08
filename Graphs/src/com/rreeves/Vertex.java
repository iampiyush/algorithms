package com.rreeves;

import java.util.LinkedList;
import java.util.ListIterator;

public class Vertex {
    private int mInDegree;
    private int mIndex;
    private String mName;
    private LinkedList<Integer> mAdjacentVertices = new LinkedList<Integer>();

    public Vertex(int index, String name) {
        mIndex = index;
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public int getIndex() {
        return mIndex;
    }

    public ListIterator<Integer> getAdjacentVertices() {
        return mAdjacentVertices.listIterator();
    }

    public void addAdjacentVertice(int i) {
        mAdjacentVertices.add(i);
    }

    public int getInDegree() {
        return mInDegree;
    }

    public void incrementInDegree() {
        mInDegree++;
    }

    public void decrementInDegree() {
        mInDegree--;
    }
}