package com.pps.ppls;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pps.ppls.database.DbInitializer;
import com.pps.ppls.database.DbManager;
import com.pps.ppls.model.Word;
import com.pps.ppls.utils.ValidationUtils;

import java.lang.ref.WeakReference;
import java.util.List;

public class InitialActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = InitialActivity.class.getSimpleName();
    private static final String PREF_KEY_SHOULD_INIT_DB = "PREF_KEY_SHOULD_INIT_DB";
    private StartTestAsyncTask mResetAnswersTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_layout);

        findViewById(R.id.btn_start_test).setOnClickListener(this);
        findViewById(R.id.btn_close_app).setOnClickListener(this);
        findViewById(R.id.btn_about).setOnClickListener(this);
        findViewById(R.id.logo_cochlear).setOnClickListener(this);
        findViewById(R.id.logo_asttlr).setOnClickListener(this);
        findViewById(R.id.logo_pps).setOnClickListener(this);
        findViewById(R.id.logo_ubb).setOnClickListener(this);
        findViewById(R.id.logo_webs4all).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_test:
                if (mResetAnswersTask == null) {
                    mResetAnswersTask = new StartTestAsyncTask(InitialActivity.this);
                }
                if (mResetAnswersTask.getStatus() != AsyncTask.Status.RUNNING) {
                    mResetAnswersTask.execute();
                }
                break;
            case R.id.btn_close_app:
                new AlertDialog.Builder(this)
                        .setMessage(getString(R.string.lbl_are_you_sure_close_app))
                        .setPositiveButton(getString(R.string.lbl_yes), (dialog, which) -> finish())
                        .setNegativeButton(getString(R.string.lbl_no), null)
                        .create()
                        .show();
                break;
            case R.id.btn_about:
                openAboutDialog();
                break;
            case R.id.logo_cochlear:
                startBrowser("http://www.cochlear.com/wps/wcm/connect/intl/home/home");
                break;
            case R.id.logo_asttlr:
                startBrowser("http://www.asttlr.ro/");
                break;
            case R.id.logo_pps:
                startBrowser("http://psihoped.psiedu.ubbcluj.ro/");
                break;
            case R.id.logo_ubb:
                startBrowser("http://www.ubbcluj.ro/ro/");
                break;
            case R.id.logo_webs4all:
                startBrowser("http://www.WebS4All.Ro");
                break;
        }
    }

    private void startBrowser(String url) {
        if (ValidationUtils.isInternetConnected(this)) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        } else {
            Toast.makeText(this, getString(R.string.lbl_no_internet_connection), Toast.LENGTH_SHORT).show();
        }

    }

    private void openAboutDialog() {
        FragmentManager manager = getFragmentManager();
        AboutDialogFragment dialog = new AboutDialogFragment();
        dialog.show(manager, AboutDialogFragment.class.getSimpleName());
    }

    private static class StartTestAsyncTask extends AsyncTask<Void, Void, Boolean> {

        private ProgressDialog mProgressDialog;
        private WeakReference<Activity> activityRef;

        StartTestAsyncTask(Activity context) {
            this.activityRef = new WeakReference<>(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = ProgressDialog.show(activityRef.get(), "Va rugam asteptati!",
                    "Initializare date ...", true);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            if (PreferenceManager.getDefaultSharedPreferences(activityRef.get()).getBoolean(PREF_KEY_SHOULD_INIT_DB, true)) {
                List<Word> mWords = DbInitializer.getWordsList();
                Log.d(TAG, "Words to be inserted:\n" + mWords);
                if (DbManager.getInstance(activityRef.get()).insertWords(mWords)) {
                    PreferenceManager.getDefaultSharedPreferences(activityRef.get()).edit().putBoolean(PREF_KEY_SHOULD_INIT_DB, false).apply();
                    Log.d(TAG, "DB Initialized!");
                    return true;
                } else {
                    Log.e(TAG, "Error inserting words into db!");
                    return false;
                }
            } else {
                return DbManager.getInstance(activityRef.get()).resetSavedAnswers();
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
            if (activityRef.get() != null) {
                activityRef.get().finish();
                activityRef.get().startActivity(new Intent(activityRef.get(), MainActivity.class));
            }
        }
    }
}
