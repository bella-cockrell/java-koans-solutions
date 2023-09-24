package intermediate;

import com.sandwich.koan.Koan;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

public class AboutDates {

    private Date date = new Date(100010001000L);// in milliseconds lol

    @Koan
    public void dateToString() {
        assertEquals(date.toString(), "Sat Mar 03 13:33:21 CET 1973");
    }

    @Koan
    public void changingDateValue() {
        int oneHourInMiliseconds = 3600000;
        date.setTime(date.getTime() + oneHourInMiliseconds); // getTime returns the number of milliseconds since January
                                                             // 1, 1970, 00:00:00 GMT
        assertEquals(date.toString(), "Sat Mar 03 14:33:21 CET 1973");
    }

    @Koan
    public void usingCalendarToChangeDates() {
        Calendar cal = Calendar.getInstance(); // instead of needing to use milliseconds
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        assertEquals(cal.getTime().toString(), "Tue Apr 03 14:33:21 CET 1973");
    }

    @Koan
    public void usingRollToChangeDatesDoesntWrapOtherFields() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.roll(Calendar.MONTH, 12); // Adds the specified (signed) amount to the specified calendar
        // field without changing larger fields. A negative amount means to roll down.
        assertEquals(cal.getTime().toString(), "Sat Mar 03 14:33:21 CET 1973");
    }

    @Koan
    public void usingDateFormatToFormatDate() {
        String formattedDate = DateFormat.getDateInstance().format(date);
        assertEquals(formattedDate, "3 Mar 1973");
    }

    @Koan
    public void usingDateFormatToFormatDateShort() {
        String formattedDate = DateFormat.getDateInstance(DateFormat.SHORT).format(date);
        assertEquals(formattedDate, "03/03/1973");
    }

    @Koan
    public void usingDateFormatToFormatDateFull() {
        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(date);
        // There is also DateFormat.MEDIUM and DateFormat.LONG... you get the idea ;-)
        assertEquals(formattedDate, "Saturday, 3 March 1973");
    }

    @Koan
    public void usingDateFormatToParseDates() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy-k-m-s"); //kms?
        Date date2 = dateFormat.parse("01-01-2000-12-2-1");
        assertEquals(date2.toString(), "Sat Jan 01 12:02:01 CET 2000");
        // What happened to the time? What do you need to change to keep the time as
        // well?
    }
}
