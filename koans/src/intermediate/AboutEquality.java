package intermediate;

import com.sandwich.koan.Koan;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

public class AboutEquality {
    // This suite of Koans expands on the concepts introduced in
    // beginner.AboutEquality

    @Koan
    public void sameObject() {
        Object a = new Object();
        Object b = a;
        assertEquals(a == b, true);
    }

    @Koan
    public void equalObject() {
        Integer a = new Integer(1);
        Integer b = new Integer(1);
        assertEquals(a.equals(b), true); // compares value
        assertEquals(b.equals(a), true);
    }

    @Koan
    public void noObjectShouldBeEqualToNull() {
        assertEquals(new Object().equals(null), false);
    }

    static class Car {
        private String name = "";
        private int horsepower = 0;

        public Car(String s, int p) {
            name = s;
            horsepower = p;
        }

        // @Override //don't need to override if not extending?
        public boolean equals(Car other) {
            // Change this implementation to match the equals contract
            // Car objects with same horsepower and name values should be considered equal
            // http://download.oracle.com/javase/6/docs/api/java/lang/Object.html#equals(java.lang.Object)
            if (other != null && name.equals(other.name) && horsepower == other.horsepower) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            // @see
            // http://download.oracle.com/javase/6/docs/api/java/lang/Object.html#hashCode()
            int result = 0;
            if (name != null && horsepower > 0) {
                result = name.hashCode();
            }
            return result;
        }
    }

    @Koan
    public void equalForOwnObjects() {
        Car car1 = new Car("Beetle", 50);
        Car car2 = new Car("Beetle", 50);
        // @see Car.equals (around line 45) for the place to solve this
        assertEquals(car1.equals(car2), true);
        assertEquals(car2.equals(car1), true);
    }

    @Koan
    public void unequalForOwnObjects() {
        Car car1 = new Car("Beetle", 50);
        Car car2 = new Car("Porsche", 300);
        // @see Car.equals (around line 45) for the place to solve this
        assertEquals(car1.equals(car2), false);
    }

    @Koan
    public void unequalForOwnObjectsWithDifferentType() {
        Car car1 = new Car("Beetle", 50);
        String s = "foo";
        // @see Car.equals (around line 45) for the place to solve this
        assertEquals(car1.equals(s), false);
    }

    @Koan
    public void equalNullForOwnObjects() {
        Car car1 = new Car("Beetle", 50);
        // @see Car.equals (around line 45) for the place to solve this
        assertEquals(car1.equals(null), false);
    }

    @Koan
    public void ownHashCode() {
        // As a general rule: When you override equals you should override
        // hash code
        // Read the hash code contract to figure out why
        //
        // http://download.oracle.com/javase/6/docs/api/java/lang/Object.html#hashCode()
        // (see below)
        // If two objects are equal according to the equals(Object) method, then calling
        // the hashCode method on each of the two objects must produce the same integer
        // result.

        // Implement Car.hashCode around line 51 so that the following assertions pass
        Car car1 = new Car("Beetle", 50);
        Car car2 = new Car("Beetle", 50);
        assertEquals(car1.equals(car2), true);
        // get the property's hash code instead, rather than the class's
        assertEquals(car1.hashCode() == car2.hashCode(), true);
    }

    static class Chicken {
        String color = "green";

        @Override
        public int hashCode() {
            int result = 0;
            if (color != null) {
                result = color.hashCode();
            }
            return result;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Chicken))
                return false;
            return ((Chicken) other).color.equals(color);
        }
    }

    @Koan
    public void ownHashCodeImplementationPartTwo() {
        Chicken chicken1 = new Chicken();
        chicken1.color = "black";
        Chicken chicken2 = new Chicken();
        Chicken chicken3 = new Chicken();
        assertEquals(chicken1.equals(chicken2), false);
        System.out.println(chicken1.hashCode());
        assertEquals(chicken1.hashCode() == chicken2.hashCode(), false); // this should be false as 1 is black
        assertEquals(chicken2.hashCode() == chicken3.hashCode(), true); // this should be true, as both are green
        // Does this still fit the hashCode contract? Why (not)?
        // no because behaviour of .equals and .hashCode should be the same, but here
        // they're not
        // hashCode() will always return true for all instances of Chicken
        // Fix the Chicken class to correct this.

    }

}
