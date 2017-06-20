package com.event.maker.login.event.presenter;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.event.maker.login.event.EventContract;
import com.event.maker.login.utils.FragmentHelper;


/**
 * Created by sangeetha on 14/6/17.
 */

public class EventPresenter implements EventContract.EentPresenterOps {
    private EventContract.EventView mView;

    @Override
    public void attachView(EventContract.EventView view) {
        mView = view;
    }

    @Override
    public void addContentFragment(AppCompatActivity activity, Fragment fragment, int containerId) {
        if (activity.findViewById(containerId) == null) {
            FragmentHelper.replaceFragment(activity, containerId, fragment);
        } else {
            FragmentHelper.replaceAndAddToBackStack(activity, containerId, fragment, fragment.getClass().getName());
        }
    }
}
