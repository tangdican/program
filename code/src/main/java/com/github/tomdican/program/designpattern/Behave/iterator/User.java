package com.github.tomdican.program.designpattern.Behave.iterator;

public class User {

    public static void main(String[] args) {
        String arr[] = {"a","b","c","d"};
        StringArray stringArray = new StringArray(arr);
        Iterator it = stringArray.createIterator();
        for (;it.hasNext();) {
            System.out.println(it.next());
        }
    }
}
