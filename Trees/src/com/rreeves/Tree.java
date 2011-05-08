package com.rreeves;

import java.util.Random;
import java.util.LinkedList;
import java.util.Queue;

public class Tree {
    private class Node {//Inner class to represent Node.
        public int val;
        public Node left;
        public Node right;

        public Node (int val) {
            this.val = val;
        }
    }

    private Random mRand;
    private Node mRoot;//Root of tree.

    /*
      Inserts val into the tree.
     */
    public void insert(int val) {
        if (mRoot == null) {
            mRoot = new Node(val);
            return;
        }
        insert(mRoot, val);
    }

    /*
      Removes all nodes from tree.
    */
    public void removeAll() {
        removeChildren(mRoot);
        mRoot = null;
    }

    /*
      Randomizes the tree, likely removing the BST property.
     */
    public void randomize() {
        if (mRand == null)
            mRand = new Random();

        randomize(mRoot);
   }

    /*
      Returns true if tree is a BST, otherwise false.
      Treats a null tree as BST.
     */
    public boolean isBst() {
        return validateBstProperty(mRoot, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /*
      Finds maximum depth of tree
     */
    public int getMaxDepth() {
        return getMaxDepth(mRoot);
    }

    /*
      Prints tree level by level
     */
    public void printLevelOrder() {
        Queue<Node> q = new LinkedList<Node>();
        q.add(mRoot);

        Node curr = null;

        while (!q.isEmpty()) {
            curr  = q.poll();

            System.out.println(curr.val);

            if (curr.left != null)
                q.add(curr.left);
            if (curr.right != null)
                q.add(curr.right);
        }
    }

    /*
      Prints tree in order
     */
    public void printInOrder() {
        printInOrder(mRoot);
    }

    private int max(int one, int two) {
        return two > one ? two : one;
    }

    ///////////////////////////
    //Recursive helper methods
    /////////////////////////

    //Recursively removes all children.
    //Do post order traversal and set chidren to null.
    private void removeChildren(Node curr) {
        if (curr == null)
            return;

        removeChildren(curr.left);
        removeChildren(curr.right);
        curr.right = null;
        curr.left = null;
    }

    //Inorder traversal, replacing node values with random numbers.
    private void randomize(Node curr) {
        if (curr == null)
            return;

        randomize(curr.left);
        curr.val = mRand.nextInt(1000);
        randomize(curr.right);
    }

    //Recursively go down left subtree, print, then recursively go down right subtree.
    private void printInOrder(Node curr) {
        if (curr == null)
            return;

        printInOrder(curr.left);
        System.out.println(curr.val);
        printInOrder(curr.right);
    }

    //Recursively inserts val into tree.
    private void insert(Node curr, int val) {
        if (val < curr.val) {
            if (curr.left == null)
                curr.left = new Node(val);
            else
                insert(curr.left, val);
        }
        else if (val >= curr.val) {
            if (curr.right == null)
                curr.right = new Node(val);
            else
                insert(curr.right, val);
        }
    }

    //Validates this tree is a BST by doing a pre-order traversal, validating
    //each node against the curent min and max so far.
    //When you go left, tighten max.
    //When you go right, tighten min.
    private boolean validateBstProperty(Node curr, int min, int max) {
        if (curr == null)
            return true;

        if (curr.val < min || curr.val > max)
            return false;

        if (!validateBstProperty(curr.left, min, curr.val))
            return false;

        if (!validateBstProperty(curr.right, curr.val, max))
            return false;

        return true;
    }

    //Recursively get depth of tree.
    private int getMaxDepth(Node curr) {
        if (curr == null)//Null node has depth of 0
            return 0;

        int left = getMaxDepth(curr.left);//Get left subtree.
        int right = getMaxDepth(curr.right);//Get right subtree.

        return max(left, right) + 1;//Max of left and right subtrees + 1 for current node.
    }
}