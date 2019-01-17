package com.github.tomdican.program.middleware.springaop.jdkproxy;

import com.github.tomdican.program.middleware.springaop.basic.InterfaceA;
import com.github.tomdican.program.middleware.springaop.basic.RealImplement;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
//        CustomizeHandle handle = new CustomizeHandle(RealImplement.class) ;
//        InterfaceA interfaceA = (InterfaceA) Proxy.newProxyInstance(Main.class.getClassLoader(), new Class[]{InterfaceA.class}, handle);
//        interfaceA.exec();

        // show the proxy class
        clazzTest();

    }

    public static void clazzTest(){
        byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
                "$Proxy1", new Class[]{InterfaceA.class}, 1);
        try {
            FileOutputStream out = new FileOutputStream("$Proxy1.class") ;
            out.write(proxyClassFile);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
