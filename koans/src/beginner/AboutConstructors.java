package beginner;

import com.sandwich.koan.Koan;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

public class AboutConstructors {

    class A {
        String someString = "a"; // property

        public A() {
            someString += "x"; // A construc
        }

    }

    class B extends A {
        public B() {
            someString += "g"; // then B construc
        }

    }

    @Koan
    public void simpleConstructorOrder() {
        assertEquals(new B().someString, "axg");
    }

    class Aa {
        String someString = "a";

        public Aa() {
            someString += "x";
        }

        public Aa(String s) { // <- this String
            someString += s; // 3: s is appended to someString because of polymorphism
        }
    }

    class Bb extends Aa { // 1: prop of Aa
        public Bb() {
            super("Boo"); // 2: use super() instead of constructor
            someString += "g"; // 4: finish Bb construc
        }

    }

    @Koan
    public void complexConstructorOrder() {
        assertEquals(new Bb().someString, "aBoog");
    }

}
