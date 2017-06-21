package com.event.maker.login.utils;

/**
 * Created by sangeetha on 21/6/17.
 */

public class ApplicationUtils {

    public static boolean isValidEmail(CharSequence email) {
        if (email == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }
}
