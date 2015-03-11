package com.android.logoped;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.logoped.database.DbInitializer;
import com.android.logoped.database.DbManager;
import com.android.logoped.model.Word;

import java.util.List;

public class InitialActivity extends ActionBarActivity implements View.OnClickListener {

    public static final String TAG = InitialActivity.class.getSimpleName();
    private static final String PREF_KEY_LICENCE_DATE = "PREF_KEY_LICENCE_DATE";
    private static final String PREF_KEY_SHOULD_INIT_DB = "PREF_KEY_SHOULD_INIT_DB";
    private StartTestAsyncTask mResetAnswersTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_layout);

        //makeTheLicenceCheck();

        Button mStartTestBtn = (Button) findViewById(R.id.btn_start_test);
        mStartTestBtn.setOnClickListener(this);
        Button mCloseAppBtn = (Button) findViewById(R.id.btn_close_app);
        mCloseAppBtn.setOnClickListener(this);
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
                        .setMessage(getString(R.string.lbl_are_you_sure_close_app)).setPositiveButton(getString(R.string.lbl_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton(getString(R.string.lbl_no), null).create().show();
                break;
        }
    }

    private boolean makeTheLicenceCheck() {
        //only allow the app to run for one hour after it was first started
        long now = System.currentTimeMillis();
        long expiry = PreferenceManager.getDefaultSharedPreferences(this).getLong(PREF_KEY_LICENCE_DATE, 0);
        if (expiry == 0) {
            expiry = now + DateUtils.HOUR_IN_MILLIS;
        }
        if (now > expiry) {
            Toast.makeText(this, "Acest demo ruleaza doar pentru o ora!", Toast.LENGTH_LONG).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    });
                }
            }, 5000);
            return false;
        }
        return true;
    }

    private class StartTestAsyncTask extends AsyncTask<Void, Void, Boolean> {

        private ProgressDialog mProgressDialog;
        private Context mContext;

        private StartTestAsyncTask(Context context) {
            this.mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = ProgressDialog.show(mContext, "Va rugam asteptati!",
                    "Initializare date ...", true);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            if (PreferenceManager.getDefaultSharedPreferences(InitialActivity.this).getBoolean(PREF_KEY_SHOULD_INIT_DB, true)) {
                List<Word> mWords = DbInitializer.getWordsList();
                Log.d(TAG, "Words to be inserted:\n" + mWords);
                if (DbManager.getInstance(InitialActivity.this).insertWords(mWords)) {
                    PreferenceManager.getDefaultSharedPreferences(InitialActivity.this).edit().putBoolean(PREF_KEY_SHOULD_INIT_DB, false).apply();
                    Log.d(TAG, "DB Initialized!");
                    return true;
                } else {
                    Log.e(TAG, "Error inserting words into db!");
                    return false;
                }
            } else {
                return DbManager.getInstance(mContext).resetSavedAnswers();
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
            finish();
            startActivity(new Intent(InitialActivity.this, MainActivity.class));
        }
    }
}
