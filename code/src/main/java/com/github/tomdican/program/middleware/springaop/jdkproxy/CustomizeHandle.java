package com.github.tomdican.program.middleware.springaop.jdkproxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomizeHandle implements InvocationHandler {

    private Object target;

    public CustomizeHandle(Class clazz) {
        try {
            this.target = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        before();
        Object result = method.invoke(target, args);
        after();

        System.out.println("proxy class={}" + proxy.getClass().getName());
        return result;
    }


    private void before() {
        System.out.println("handle before");
    }

    private void after() {
        System.out.println("handle after");
    }


}
