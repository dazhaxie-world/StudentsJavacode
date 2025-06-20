package util;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecognizePattenUtil {
    private String regex = "[ ]+";

    public String[] recognize(String s){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        String[] result;
        result = s.split(regex);
        return result;
    }

}
