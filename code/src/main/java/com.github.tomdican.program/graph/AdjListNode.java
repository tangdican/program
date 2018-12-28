package com.github.tomdican.program.graph;

public class AdjListNode {
    private int v;

    public AdjListNode() {
    }

    public void setV(int v) {
        this.v = v;
    }
    public AdjListNode(int _v)
    {
        v = _v;
    }
    int getV() { return v; }
}
