package intermediate;

import com.sandwich.koan.Koan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

public class AboutRegularExpressions {

    @Koan
    public void basicMatching() {
        Pattern p = Pattern.compile("xyz"); // regex
        Matcher m = p.matcher("xyzxxxxyz"); // the string to use rgex on
        // index 012345678
        assertEquals(m.find(), true); // Attempts to find the next subsequence of the input sequence that matches the
                                      // pattern.
        // true if, and only if, a subsequence of the input sequence matches this
        // matcher's pattern
        assertEquals(m.start(), 0); // returns the index of the first character matched
        assertEquals(m.find(), true); // if a previous invocation of the method was successful and
        // the matcher has not since been reset, at the first character not matched by
        // the previous match.
        assertEquals(m.start(), 6); // If the match succeeds then more information can be obtained via the start,
                                    // end, and group methods.
        assertEquals(m.find(), false);
    }

    @Koan
    public void extendedMatching() {
        Pattern p = Pattern.compile("x.z");// x [any char] z
        Matcher m = p.matcher("xyz u x z u xfz");
        // index 012345678901234
        assertEquals(m.find(), true);
        assertEquals(m.start(), 0);
        assertEquals(m.find(), true);
        assertEquals(m.start(), 6);
        assertEquals(m.find(), true);
        assertEquals(m.start(), 12);
    }

    @Koan
    public void escapingMetaCharacters() {
        Pattern p = Pattern.compile("end\\.");
        Matcher m = p.matcher("begin. end.");
        // index 01234567890
        assertEquals(m.find(), true);
        assertEquals(m.start(), 7);
    }

    @Koan
    public void splittingStrings() {
        String csvDataLine = "1,name,description";
        String[] data = csvDataLine.split(","); // you can use any regex here
        assertEquals(data[0], "1");
        assertEquals(data[1], "name");
        assertEquals(data[2], "description");
    }
}
