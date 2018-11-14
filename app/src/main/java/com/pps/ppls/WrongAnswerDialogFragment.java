package com.pps.ppls;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class WrongAnswerDialogFragment extends DialogFragment implements
        AdapterView.OnItemClickListener {

    public interface WrongAnswerTypeListener {
        void onWrongAnswerSelected(int position);
    }

    private enum WordPronunciationError {
        POZITIE_INITIALA("Sunet omis"),
        POZITIE_MEDIANA("Sunet distorsionat"),
        POZITIE_FINALA("Sunet inlocuit");

        private String mMessage;

        WordPronunciationError(String message) {
            this.mMessage = message;
        }

        static String[] getMessagesArray() {
            final List<String> list = new ArrayList<>();
            for (WordPronunciationError error : values()) {
                list.add(error.mMessage);
            }
            return list.toArray(new String[values().length]);
        }

    }

    private ListView mylist;
    private WrongAnswerTypeListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (WrongAnswerTypeListener) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle(getString(R.string.lbl_choose_error_type_title));
        View view = inflater.inflate(R.layout.dialog_fragment, null, false);
        mylist = view.findViewById(R.id.list_view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, WordPronunciationError.getMessagesArray());

        mylist.setAdapter(adapter);

        mylist.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        mListener.onWrongAnswerSelected(position);
        dismiss();
    }
}