package com.example.cm.challengeme;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TitlesFragment extends ListFragment{
    public static String KEY_EXTRA_INDEX = "com.example.challengeme.TitlesFragment.INDEX";
    private boolean mDualPane;
    int mCurCheckPosition = 0;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Populate list with static array of titles.
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1, Shakespeare.TITLES));

        // Check if we have a frame in which to embed the details fragment directly.
        // Fragment directly in the containing UI.
        View detailsFrame = getActivity().findViewById(R.id.details);
        mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        if(savedInstanceState != null) {
            // Restore last state for checked position.
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }

        if(mDualPane) {
            // In dual-pane mode the list view highlights the selected items.
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            // Make sure our UI is in the correct state.
            showDetails(mCurCheckPosition);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetails(position);
    }

    /**
     * Helper function to show the details of a selected item, either by displaying a fragment in-place in the current UI, or
     * starting a whole new acticity in which it is displayed.
     */
    private void showDetails(int index) {
        mCurCheckPosition = index;

        if(mDualPane) {
            // We display everything in place with fragments, so update the list to highlight the selected
            // item and show the data.
            getListView().setItemChecked(index, true);

            // Check which fragment is currently displayed, replace if needed.
            DetailsFragment details = (DetailsFragment)
                    getFragmentManager().findFragmentById(R.id.details);

            if(details == null || details.getShownIndex() != index) {
                // Make new fragement to show this selection.
                details = DetailsFragment.newInstance(index);

                // Execute a transaction, replacing any existing fragment with this one
                // inside the frame.
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                if(index == 0) {
                    ft.replace(R.id.details, details);
                } else {
                    ft.replace(R.id.details, details);
                }
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        } else {
            // Otherwise we need to lauch a new activity to display
            // the dialog fragment with the selected text.
            Intent intent = new Intent();
            intent.setClass(getActivity(), DetailsActivity.class);
            intent.putExtra(KEY_EXTRA_INDEX, index);
            startActivity(intent);
        }
    }

    public static class Shakespeare {
        public static String[] TITLES = {"Hamlet", "Romeo"};
        public static String[] DIALOGUES = {"I am the prince and the king", "Juliette is a bitch"};
    }
}

