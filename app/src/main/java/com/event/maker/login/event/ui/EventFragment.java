package com.event.maker.login.event.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;


import com.event.maker.login.event.presenter.EventPresenter;
import com.maker.event.R;

/**
 * Created by sangeetha on 14/6/17.
 */

public class EventFragment extends Fragment {

    private Spinner mSpinner;
    private EventPresenter mEventPresenter;

    public static EventFragment getInstance(EventPresenter presenter) {
        EventFragment fragment = new EventFragment();
//      Bundle bundle = new Bundle();
//      bundle.putParcelable("Presenter", (Parcelable) presenter);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_fragment, container, false);
        initViews(view);
        extractArguments();

        return view;
    }

    private void extractArguments() {
        if (getActivity().getIntent() != null) {
            mEventPresenter = getActivity().getIntent().getParcelableExtra("Presenter");
        }
    }


    private void initViews(View view) {
        mSpinner = (Spinner) view.findViewById(R.id.occasion_spinner);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
