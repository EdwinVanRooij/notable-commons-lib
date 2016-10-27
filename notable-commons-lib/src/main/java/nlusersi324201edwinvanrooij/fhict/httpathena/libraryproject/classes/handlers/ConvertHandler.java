package nlusersi324201edwinvanrooij.fhict.httpathena.libraryproject.classes.handlers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Edwin on 11/27/2015.
 */
public class ConvertHandler {
    public static String DateToString(Date date) {
        String result;
        if (date == null) {
            return null;
        } else {
            result = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
            return result;
        }
    }

    public static boolean IntToBoolean(int input) {
        if (input == 1) {
            return true;
        } else if (input == 0) {
            return false;
        }
        return false;
    }

    public static Date StringToDate(String date_from_db) {
        if (date_from_db.equals("null") || date_from_db.equals("")) {
            return null;
        } else {
            String format = "yyyy-MM-dd hh:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            try {
                return sdf.parse(date_from_db);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
