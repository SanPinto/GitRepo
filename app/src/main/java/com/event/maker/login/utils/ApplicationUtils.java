package com.event.maker.login.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sangeetha on 21/6/17.
 */

public class ApplicationUtils {

    /**
     * (			# Start of group
     * (?=.*\d)		#   must contains one digit from 0-9
     * (?=.*[a-z])		#   must contains one lowercase characters
     * (?=.*[A-Z])		#   must contains one uppercase characters
     * (?=.*[@#$%])		#   must contains one special symbols in the list "@#$%"
     * .		#     match anything with previous condition checking
     * {6,20}	#        length at least 6 characters and maximum of 20
     * )			# End of group
     */

    private static final String PASSWORD_PATTERN =
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";


    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean isValidEmail(CharSequence email) {
        if (email == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }


    /**
     * Validate password with regular expression
     *
     * @param password password for validation
     * @return true valid password, false invalid password
     */
    public static boolean isValidPassword(final String password) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();

    }

}
