package intermediate;

import com.sandwich.koan.Koan;

import java.util.Arrays;
import java.util.Comparator;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

public class AboutComparison {

    @Koan
    public void compareObjects() {
        String a = "abc";
        String b = "bcd";
        assertEquals(a.compareTo(b), -1); // a is before b
        assertEquals(a.compareTo(a), 0); // totally equal
        assertEquals(b.compareTo(a), 1); // b is after a
        // The result is a negative integer if this String object lexicographically
        // precedes the argument string.
        // The result is a positive integer if this String object lexicographically
        // follows the argument string.
        // The result is zero if the strings are equal
    }

    static class Car implements Comparable<Car> { // implementing Comparable allows you to compare objects. Must define
                                                  // compareTo.
        int horsepower;

        // For an explanation for this implementation look at
        // http://download.oracle.com/javase/6/docs/api/java/lang/Comparable.html#compareTo(T)
        public int compareTo(Car o) {
            return horsepower - o.horsepower;
        }

    }

    @Koan
    public void makeObjectsComparable() {
        Car vwbeetle = new Car();
        vwbeetle.horsepower = 50;
        Car porsche = new Car();
        porsche.horsepower = 300;
        assertEquals(vwbeetle.compareTo(porsche), -250); // 50 - 300
    }

    static class RaceHorse {
        int speed;
        int age;

        @Override
        public String toString() { // for this class, override the toString method
            return "Speed: " + speed + " Age: " + age;
        }
    }

    static class HorseSpeedComparator implements Comparator<RaceHorse> { // not Comparable?
        public int compare(RaceHorse o1, RaceHorse o2) { // not .compareTo?
            return o1.speed - o2.speed;
        }
    }

    static class HorseAgeComparator implements Comparator<RaceHorse> {
        public int compare(RaceHorse o1, RaceHorse o2) {
            return o1.age - o2.age; // lower value goes first in sort e.g. 10 - 12 = -2, but if 12 - 10 = 2
        }
    }

    @Koan
    public void makeObjectsComparableWithoutComparable() {
        RaceHorse lindy = new RaceHorse();
        lindy.age = 10;
        lindy.speed = 2;
        RaceHorse lightning = new RaceHorse();
        lightning.age = 2;
        lightning.speed = 10;
        RaceHorse slowy = new RaceHorse();
        slowy.age = 12;
        slowy.speed = 1;

        RaceHorse[] horses = { lindy, slowy, lightning };

        Arrays.sort(horses, new HorseAgeComparator());
        assertEquals(horses[0], lightning);
        Arrays.sort(horses, new HorseSpeedComparator());
        assertEquals(horses[0], slowy);
    }
}
