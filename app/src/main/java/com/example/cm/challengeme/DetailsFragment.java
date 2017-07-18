package com.example.cm.challengeme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

    /**
     * Returns the index of the shown element.
     */
    public int getShownIndex() {
        return getArguments().getInt(TitlesFragment.KEY_EXTRA_INDEX, 0);
    }

    /**
     * Create a new instance of details fragment instanciated to show the details at 'index".
     */
    public static DetailsFragment newInstance(int index) {
        DetailsFragment f = new DetailsFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt(TitlesFragment.KEY_EXTRA_INDEX, index);
        f.setArguments(args);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(container == null) {
            return null;
        }

        ScrollView scroller = new ScrollView(getActivity());
        TextView text = new TextView(getActivity());
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getActivity().getResources().getDisplayMetrics());
        text.setPadding(padding, padding, padding, padding);
        scroller.addView(text);
        text.setText(TitlesFragment.Shakespeare.DIALOGUES[getShownIndex()]);
        return scroller;
    }


}
