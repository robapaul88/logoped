package com.pps.ppls;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;


public class AboutDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.about_fragment, null);
        WebView wv = v.findViewById(R.id.content);
        wv.loadUrl("file:///android_asset/description.html");
        builder.setView(v).setPositiveButton(R.string.lbl_inchidere, (dialog, id) -> {
        });
        return builder.create();
    }
}
