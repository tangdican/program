package com.github.tomdican.program.String.suffixtree;

import java.util.ArrayList;

public class SuffixTree {

    public static void main(String[] args) {
        String testString = "mississippi";
        String[] stringList = {"is", "sip", "hi", "sis"};
        SuffixTree tree = new SuffixTree(testString);
        for (String s : stringList) {
            ArrayList<Integer> list = tree.search(s);
            if (list != null) {
                System.out.println(s + ": " + list.toString());
            }
        }
    }


    private SuffixTreeNode root = new SuffixTreeNode();

    public SuffixTree(String s) {
        for (int i = 0; i < s.length(); i++) {
            String suffix = s.substring(i);
            root.insertString(suffix, i);
        }
    }

    public ArrayList<Integer> search(String s) {
        return root.search(s);
    }
}
