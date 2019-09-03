package com.github.tomdican.program.String.suffixtree.way2;

/***
 * http://www.javased.com/index.php?source_dir=alg-vis/src/algvis/suffixtree/SuffixTree.java
 * https://github.com/kuk0/alg-vis/tree/master/src/algvis/ds/suffixtree
 */
public class SuffixTree {
    private static final int textpos = -40;
    public static String adtName = "stringology";
    public static String dsName = "suffixtree";

//    private SuffixTreeNode root = null;
//    private SuffixTreeNode v = null;
//
//    public TrieWordNode hw = null;
//    private final TrieWordNode cs = null;
//
//    public String text;
//    StringElem str;
//
//    public SuffixTree(VisPanel M) {
//        super(M);
//        clear();
//    }
//
//    public SuffixTree(VisPanel M, String text) {
//        super(M);
//        clear();
//        this.text = text;
//        this.str = new StringElem(this, text, 0, textpos);
//    }
//
//    @Override
//    public String getName() {
//        return "suffixtree";
//    }
//
//    @Override
//    public String stats() {
//        return "";
//    }
//
//    @Override
//    public void insert(int x) {}
//
//    @Override
//    public void clear() {
//        root = new SuffixTreeNode(this);
//        root.reposition();
//        v = null;
//        str = null;
//    }
//
//    SuffixTreeNode getV() {
//        return v;
//    }
//
//    public SuffixTreeNode setV(SuffixTreeNode v) {
//        this.v = v;
//        return v;
//    }
//
//    public SuffixTreeNode getRoot() {
//        return root;
//    }
//
//    public void setRoot(SuffixTreeNode root) {
//        this.root = root;
//    }
//
//    @Override
//    public void draw(View V) {
//        if (str != null)
//            str.draw(V);
//        SuffixTreeNode v = getRoot();
//        if (v != null) {
//            v.moveTree();
//            v.drawTree(V);
//            V.drawString("\u025B", v.x, v.y - 8, Fonts.NORMAL);
//        }
//        v = getV();
//        if (v != null) {
//            v.move();
//            v.drawLabel(V);
//        }
//        if (hw != null) {
//            hw.draw(V);
//        }
//        if (cs != null) {
//            cs.draw(V);
//        }
//    }
//
//    @Override
//    public void random(int n) {
//        boolean p = M.pause;
//        M.pause = false;
//        for (int i = 0; i < n; i++) {
//            if (M.S == null) {
//                insert(WordGenerator.getSkWord());
//            } else {
//                insert(WordGenerator.getWord(M.S));
//            }
//        }
//        M.pause = p;
//    }
//
//    public void insert(String s) {
//        text = s;
//        str = new StringElem(this, text, 0, textpos);
//        start(new SuffixTreeInsert(this, s));
//    }
//
//    public void find(String s) {
//        start(new SuffixTreeFind(this, s));
//    }
//
//    public void reposition() {
//        getRoot().reposition();
//    }
//
//    public void clearExtraColor() {
//        SuffixTreeNode r = getRoot();
//        if (r != null) {
//            getRoot().clearExtraColor();
//        }
//    }
}
