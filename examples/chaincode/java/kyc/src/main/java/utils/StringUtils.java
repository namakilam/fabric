package utils;

import javafx.beans.binding.BooleanExpression;

/**
 * Created by namakilam on 25/07/17.
 */
public class StringUtils {
    public static Boolean Empty(String s) {
        return s.length() == 0;
    }

    public static Boolean NotEmpty(String s) {
        return !Empty(s);
    }
}
