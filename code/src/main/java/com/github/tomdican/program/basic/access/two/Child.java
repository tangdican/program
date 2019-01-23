package com.github.tomdican.program.basic.access.two;

import com.github.tomdican.program.basic.access.one.Parent;

public class Child{

    public static void main(String[] args) {
        Access access = new Access();
        access.show();
    }

    static class Access extends Parent{
        void show(){
            super.showMe();
        }
        // error,can not access it from parent class
        void show2(){
           // super.showMe2();
        }


    }



}
