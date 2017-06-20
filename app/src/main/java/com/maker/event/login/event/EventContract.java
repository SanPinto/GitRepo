package com.date.book.login.event;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by sangeetha on 14/6/17.
 */

public class EventContract {

    public interface EentPresenterOps {

        void attachView(EventView view);

        void addContentFragment(AppCompatActivity activity, Fragment fragment, int containerId);
    }

    public interface EventView {

    }
}
