package ch4;

import java.time.Instant;

// Demonstration of what can go wrong when you override a method  called from constructor (Page 96)
public final class Sub extends Super {
    // Blank final, set by constructor
    private final Instant instant;

    Sub() {
        instant = Instant.now();
    }

    // Overriding method invoked by superclass constructor
    @Override public void overrideMe() {
        System.out.println(instant);
    }

    public static void main(String[] args) {
        // 父类先构造时调用方法，instant为null，因为子类还没开始构造；
        Sub sub = new Sub();
        sub.overrideMe();
    }
}
