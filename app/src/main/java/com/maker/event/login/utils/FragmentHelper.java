package com.date.book.login.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by sangeetha on 14/6/17.
 */

public class FragmentHelper {

    public static void replaceAndAddToBackStack(final AppCompatActivity activity, final int containerId,
                                                final Fragment fragment, String tag) {
        try {
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
            transaction.replace(containerId, fragment);
            transaction.addToBackStack(tag);
            transaction.commitAllowingStateLoss();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }


    public static void replaceFragment(final AppCompatActivity activity, final int containerId,
                                       final Fragment fragment) {
        try {
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
            transaction.replace(containerId, fragment);
            transaction.commitAllowingStateLoss();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

}
