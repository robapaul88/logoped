package com.android.logoped;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.logoped.database.DbManager;
import com.android.logoped.model.Word;
import com.android.logoped.utils.ReportUtils;
import com.android.logoped.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.List;


public class TestReportActivity extends Activity implements LoaderCallbacks<Cursor> {

    private static final String TAG = TestReportActivity.class.getSimpleName();
    private AutoCompleteTextView mEmailView;
    private EditText mNumePacient;
    private TextView tv1RaspunsuriCorecte, tv2greseliinitiale, tv3greselimediane, tv4greselifinale, tv5coclusive, tv6cfrictive,
            tv7cafricate, tv8cnazale, tv9csonante, tv10cbilabiale, tv11clabiodentale, tv12capicodentale, tv13calveolare,
            tv14cpalatale, tv15cvelare, tv16claringale, tv17vanterioare, tv18vmediane, tv19vposterioare, tv20vdeschise,
            tv21vsemideschise, tv22vinchise, tv23vrotunjite, tv24vnerotunjite;
    private StringBuilder mEmailBodyStringBuilder;
    private String[] mReportData;

    private int answeredCorrectly, wrongFirstPart, wrongMiddle, wrongEnd, mNbConsoaneAlveolare, mCorectConsoaneAlveolare, mNbConsoaneApicoDentale, mCorectConsoaneApicoDentale, mNbConsoaneBilabiale,
            mNbConsoaneLabiodentale, mCorectConsoaneBilabiale, mCorectConsoaneLabiodentale, mNbConsoaneLaringale, mCorectConsoaneLaringale,
            mNbConsoanePalatale, mCorectConsoanePalatale, mNbConsoaneVelare, mCorectConsoaneVelare, mNbConsoaneAfricate, mCorectConsoaneAfricate,
            mNbConsoaneFricative, mNbConsoaneNazale, mCorectConsoaneNazale, mNbConsoaneOclusive, mCorectConsoaneOclusive, mNbConsoaneSonante,
            mCorectConsoaneSonante, mNbVocaleAnterioare, mCorectVocaleAnterioare, mNbVocalePosterioare, mNbVocaleDeschise, mCorectVocaleInchise,
            mCorectVocaleNerotunjite, mNbVocaleNerotunjite, mCorectVocaleRotunjite, mNbVocaleSemideschise, mNbVocaleRotunjite, mNbVocaleInchise,
            mCorectVocaleSemideschise, mCorectVocaleDeschise, mCorectVocalePosterioare, mNbVocaleMediane, mCorectVocaleMediane, mCorectConsoaneFricative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_report_layout);

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        populateAutoComplete();

        mNumePacient = (EditText) findViewById(R.id.pacient);

        Button sendReportBtn = (Button) findViewById(R.id.send_email_btn);
        sendReportBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSendingReport();
            }
        });

        initTextViews();

        new ComputeResultsTask(this).execute();
    }

    private void initTextViews() {
        tv1RaspunsuriCorecte = (TextView) findViewById(R.id.txtReport1raspunsuricorecte);
        tv2greseliinitiale = (TextView) findViewById(R.id.txtReport2greseliinitiale);
        tv3greselimediane = (TextView) findViewById(R.id.txtReport3greselimediane);
        tv4greselifinale = (TextView) findViewById(R.id.txtReport4greselifinale);
        tv5coclusive = (TextView) findViewById(R.id.txtReport5coclusive);
        tv6cfrictive = (TextView) findViewById(R.id.txtReport6cfrictive);
        tv7cafricate = (TextView) findViewById(R.id.txtReport7cafricate);
        tv8cnazale = (TextView) findViewById(R.id.txtReport8cnazale);
        tv9csonante = (TextView) findViewById(R.id.txtReport9csonante);
        tv10cbilabiale = (TextView) findViewById(R.id.txtReport10cbilabiale);
        tv11clabiodentale = (TextView) findViewById(R.id.txtReport11clabiodentale);
        tv12capicodentale = (TextView) findViewById(R.id.txtReport12capicodentale);
        tv13calveolare = (TextView) findViewById(R.id.txtReport13calveolare);
        tv14cpalatale = (TextView) findViewById(R.id.txtReport14cpalatale);
        tv15cvelare = (TextView) findViewById(R.id.txtReport15cvelare);
        tv16claringale = (TextView) findViewById(R.id.txtReport16claringale);
        tv17vanterioare = (TextView) findViewById(R.id.txtReport17vanterioare);
        tv18vmediane = (TextView) findViewById(R.id.txtReport18vmediane);
        tv19vposterioare = (TextView) findViewById(R.id.txtReport19vposterioare);
        tv20vdeschise = (TextView) findViewById(R.id.txtReport20vdeschise);
        tv21vsemideschise = (TextView) findViewById(R.id.txtReport21vsemideschise);
        tv22vinchise = (TextView) findViewById(R.id.txtReport22vinchise);
        tv23vrotunjite = (TextView) findViewById(R.id.txtReport23vrotunjite);
        tv24vnerotunjite = (TextView) findViewById(R.id.txtReport24vnerotunjite);
    }

    private class ComputeResultsTask extends AsyncTask<Void, Void, Void> {

        private ProgressDialog mProgressDialog;
        private Context mContext;

        private ComputeResultsTask(Context context) {
            this.mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = ProgressDialog.show(mContext, "Va rugam asteptati!",
                    "Calculare raport in progress ...", true);
            mReportData = new String[24];
            resetNumbers();
        }

        @Override
        protected Void doInBackground(Void... params) {
            List<Word> words = DbManager.getInstance(mContext).getAllAnsweredWords();
            Log.d(TAG, "Answered words:\n" + words);
            if (words == null || words.size() == 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                words = DbManager.getInstance(mContext).getAllAnsweredWords();
            }
            int answeredSize = words.size();
            for (Word w : words) {
                switch (w.getAnswerType()) {
                    case Word.ANSWERED_CORRECTLY_ID:
                        answeredCorrectly++;
                        break;

                    case Word.ANSWERED_WRONG_FIRST_PART_ID:
                        wrongFirstPart++;
                        break;

                    case Word.ANSWERED_WRONG_MIDDLE_ID:
                        wrongMiddle++;
                        break;

                    case Word.ANSWERED_WRONG_LAST_PART_ID:
                        wrongEnd++;
                        break;
                }
            }
            for (Word word : words) {
                if (ReportUtils.ConsoaneProducere.AFRICATE.contains(word.getFonem())) {
                    mNbConsoaneAfricate++;
                    if (word.getAnswerType() == Word.ANSWERED_CORRECTLY_ID) {
                        mCorectConsoaneAfricate++;
                    }
                }
                if (ReportUtils.ConsoaneProducere.FRICATIVE.contains(word.getFonem())) {
                    mNbConsoaneFricative++;
                    if (word.getAnswerType() == Word.ANSWERED_CORRECTLY_ID) {
                        mCorectConsoaneFricative++;
                    }
                }
                if (ReportUtils.ConsoaneProducere.NAZALE.contains(word.getFonem())) {
                    mNbConsoaneNazale++;
                    if (word.getAnswerType() == Word.ANSWERED_CORRECTLY_ID) {
                        mCorectConsoaneNazale++;
                    }
                }
                if (ReportUtils.ConsoaneProducere.OCLUSIVE.contains(word.getFonem())) {
                    mNbConsoaneOclusive++;
                    if (word.getAnswerType() == Word.ANSWERED_CORRECTLY_ID) {
                        mCorectConsoaneOclusive++;
                    }
                }
                if (ReportUtils.ConsoaneProducere.SONANTE.contains(word.getFonem())) {
                    mNbConsoaneSonante++;
                    if (word.getAnswerType() == Word.ANSWERED_CORRECTLY_ID) {
                        mCorectConsoaneSonante++;
                    }
                }
                if (ReportUtils.ConsoaneArticulare.ALVEOLARE.contains(word.getFonem())) {
                    mNbConsoaneAlveolare++;
                    if (word.getAnswerType() == Word.ANSWERED_CORRECTLY_ID) {
                        mCorectConsoaneAlveolare++;
                    }
                }
                if (ReportUtils.ConsoaneArticulare.APICO_DENTALE.contains(word.getFonem())) {
                    mNbConsoaneApicoDentale++;
                    if (word.getAnswerType() == Word.ANSWERED_CORRECTLY_ID) {
                        mCorectConsoaneApicoDentale++;
                    }
                }
                if (ReportUtils.ConsoaneArticulare.BILABIALE.contains(word.getFonem())) {
                    mNbConsoaneBilabiale++;
                    if (word.getAnswerType() == Word.ANSWERED_CORRECTLY_ID) {
                        mCorectConsoaneBilabiale++;
                    }
                }
                if (ReportUtils.ConsoaneArticulare.LABIODENTALE.contains(word.getFonem())) {
                    mNbConsoaneLabiodentale++;
                    if (word.getAnswerType() == Word.ANSWERED_CORRECTLY_ID) {
                        mCorectConsoaneLabiodentale++;
                    }
                }
                if (ReportUtils.ConsoaneArticulare.LARINGALE.contains(word.getFonem())) {
                    mNbConsoaneLaringale++;
                    if (word.getAnswerType() == Word.ANSWERED_CORRECTLY_ID) {
                        mCorectConsoaneLaringale++;
                    }
                }
                if (ReportUtils.ConsoaneArticulare.PALATALE.contains(word.getFonem())) {
                    mNbConsoanePalatale++;
                    if (word.getAnswerType() == Word.ANSWERED_CORRECTLY_ID) {
                        mCorectConsoanePalatale++;
                    }
                }
                if (ReportUtils.ConsoaneArticulare.VELARE.contains(word.getFonem())) {
                    mNbConsoaneVelare++;
                    if (word.getAnswerType() == Word.ANSWERED_CORRECTLY_ID) {
                        mCorectConsoaneVelare++;
                    }
                }
                if (ReportUtils.VocaleArticulare.ANTERIOARE.contains(word.getFonem())) {
                    mNbVocaleAnterioare++;
                    if (word.getAnswerType() == Word.ANSWERED_CORRECTLY_ID) {
                        mCorectVocaleAnterioare++;
                    }
                }
                if (ReportUtils.VocaleArticulare.MEDIANE.contains(word.getFonem())) {
                    mNbVocaleMediane++;
                    if (word.getAnswerType() == Word.ANSWERED_CORRECTLY_ID) {
                        mCorectVocaleMediane++;
                    }
                }
                if (ReportUtils.VocaleArticulare.POSTERIOARE.contains(word.getFonem())) {
                    mNbVocalePosterioare++;
                    if (word.getAnswerType() == Word.ANSWERED_CORRECTLY_ID) {
                        mCorectVocalePosterioare++;
                    }
                }
                if (ReportUtils.VocaleDeschidereCavitateBucala.DESCHISE.contains(word.getFonem())) {
                    mNbVocaleDeschise++;
                    if (word.getAnswerType() == Word.ANSWERED_CORRECTLY_ID) {
                        mCorectVocaleDeschise++;
                    }
                }
                if (ReportUtils.VocaleDeschidereCavitateBucala.SEMIDESCHISE.contains(word.getFonem())) {
                    mNbVocaleSemideschise++;
                    if (word.getAnswerType() == Word.ANSWERED_CORRECTLY_ID) {
                        mCorectVocaleSemideschise++;
                    }
                }
                if (ReportUtils.VocaleDeschidereCavitateBucala.INCHISE.contains(word.getFonem())) {
                    mNbVocaleInchise++;
                    if (word.getAnswerType() == Word.ANSWERED_CORRECTLY_ID) {
                        mCorectVocaleInchise++;
                    }
                }
                if (ReportUtils.VocaleParticipareBuze.ROTUNJITE.contains(word.getFonem())) {
                    mNbVocaleRotunjite++;
                    if (word.getAnswerType() == Word.ANSWERED_CORRECTLY_ID) {
                        mCorectVocaleRotunjite++;
                    }
                }
                if (ReportUtils.VocaleParticipareBuze.NEROTUNJITE.contains(word.getFonem())) {
                    mNbVocaleNerotunjite++;
                    if (word.getAnswerType() == Word.ANSWERED_CORRECTLY_ID) {
                        mCorectVocaleNerotunjite++;
                    }
                }
            }
            float fullPercentage = answeredSize == 0 ? 0 : (float) answeredCorrectly * 100 / answeredSize;
            float fullAnterioarePercentage = answeredSize == 0 ? 0 : (float) wrongFirstPart * 100 / answeredSize;
            float fullMedianePercentage = answeredSize == 0 ? 0 : (float) wrongMiddle * 100 / answeredSize;
            float fullPosterioarePercentage = answeredSize == 0 ? 0 : (float) wrongEnd * 100 / answeredSize;
            float cOclusivePercentage = mNbConsoaneOclusive == 0 ? 0 : (float) mCorectConsoaneOclusive * 100 / mNbConsoaneOclusive;
            float cFricativePercentage = mNbConsoaneFricative == 0 ? 0 : (float) mCorectConsoaneFricative * 100 / mNbConsoaneFricative;
            float cAfricatePercentage = mNbConsoaneAfricate == 0 ? 0 : (float) mCorectConsoaneAfricate * 100 / mNbConsoaneAfricate;
            float cNazalePercentage = mNbConsoaneNazale == 0 ? 0 : (float) mCorectConsoaneNazale * 100 / mNbConsoaneNazale;
            float cSonantePercentage = mNbConsoaneSonante == 0 ? 0 : (float) mCorectConsoaneSonante * 100 / mNbConsoaneSonante;
            float cBilabialePercentage = mNbConsoaneBilabiale == 0 ? 0 : (float) mCorectConsoaneBilabiale * 100 / mNbConsoaneBilabiale;
            float cLabiodentalePercentage = mNbConsoaneLabiodentale == 0 ? 0 : (float) mCorectConsoaneLabiodentale * 100 / mNbConsoaneLabiodentale;
            float cApicoDentalePercentage = mNbConsoaneApicoDentale == 0 ? 0 : (float) mCorectConsoaneApicoDentale * 100 / mNbConsoaneApicoDentale;
            float cAlveolarePercentage = mNbConsoaneAlveolare == 0 ? 0 : (float) mCorectConsoaneAlveolare * 100 / mNbConsoaneAlveolare;
            float cPalatalePercentage = mNbConsoanePalatale == 0 ? 0 : (float) mCorectConsoanePalatale * 100 / mNbConsoanePalatale;
            float cVelarePercentage = mNbConsoaneVelare == 0 ? 0 : (float) mCorectConsoaneVelare * 100 / mNbConsoaneVelare;
            float cLaringalePercentage = mNbConsoaneLaringale == 0 ? 0 : (float) mCorectConsoaneLaringale * 100 / mNbConsoaneLaringale;
            float vAnterioarePercentage = mNbVocaleAnterioare == 0 ? 0 : (float) mCorectVocaleAnterioare * 100 / mNbVocaleAnterioare;
            float vMedianePercentage = mNbVocaleMediane == 0 ? 0 : (float) mCorectVocaleMediane * 100 / mNbVocaleMediane;
            float vPosterioarePercentage = mNbVocalePosterioare == 0 ? 0 : (float) mCorectVocalePosterioare * 100 / mNbVocalePosterioare;
            float vDeschisePercentage = mNbVocaleDeschise == 0 ? 0 : (float) mCorectVocaleDeschise * 100 / mNbVocaleDeschise;
            float vSemideschisePercentage = mNbVocaleSemideschise == 0 ? 0 : (float) mCorectVocaleSemideschise * 100 / mNbVocaleSemideschise;
            float vInchisePercentage = mNbVocaleInchise == 0 ? 0 : (float) mCorectVocaleInchise * 100 / mNbVocaleInchise;
            float vRotunjitePercentage = mNbVocaleRotunjite == 0 ? 0 : (float) mCorectVocaleRotunjite * 100 / mNbVocaleRotunjite;
            float vNerotunjitePercentage = mNbVocaleNerotunjite == 0 ? 0 : (float) mCorectVocaleNerotunjite * 100 / mNbVocaleNerotunjite;

            mEmailBodyStringBuilder = new StringBuilder("Raport test:\n\n");
            mReportData[0] = " " + answeredCorrectly + " / " + answeredSize + " = " + String.format("%.2f", fullPercentage) + "%";
            mEmailBodyStringBuilder.append(getString(R.string.lbl_raspunsuri_corecte)).append(mReportData[0]).append("\n");
            mReportData[1] = " " + wrongFirstPart + " / " + answeredSize + " = " + String.format("%.2f", fullAnterioarePercentage) + "%";
            mEmailBodyStringBuilder.append(getString(R.string.lbl_greseli_initiale)).append(mReportData[1]).append("\n");
            mReportData[2] = " " + wrongMiddle + " / " + answeredSize + " = " + String.format("%.2f", fullMedianePercentage) + "%";
            mEmailBodyStringBuilder.append(getString(R.string.lbl_greseli_mediane)).append(mReportData[2]).append("\n");
            mReportData[3] = " " + wrongEnd + " / " + answeredSize + " = " + String.format("%.2f", fullPosterioarePercentage) + "%";
            mEmailBodyStringBuilder.append(getString(R.string.lbl_greseli_finale)).append(mReportData[3]).append("\n");
            mEmailBodyStringBuilder.append("\n").append(getString(R.string.lbl_consoane)).append("\n").append("\n");
            mEmailBodyStringBuilder.append(getString(R.string.lbl_dupa_mod_producere)).append("\n");
            mReportData[4] = " " + mCorectConsoaneOclusive + " / " + mNbConsoaneOclusive + " = " + String.format("%.2f", cOclusivePercentage) + "%";
            mEmailBodyStringBuilder.append(getString(R.string.lbl_oclusive)).append(mReportData[4]).append("\n");
            mReportData[5] = " " + mCorectConsoaneFricative + " / " + mNbConsoaneFricative + " = " + String.format("%.2f", cFricativePercentage) + "%";
            mEmailBodyStringBuilder.append(getString(R.string.lbl_fricative)).append(mReportData[5]).append("\n");
            mReportData[6] = " " + mCorectConsoaneAfricate + " / " + mNbConsoaneAfricate + " = " + String.format("%.2f", cAfricatePercentage) + "%";
            mEmailBodyStringBuilder.append(getString(R.string.lbl_africate)).append(mReportData[6]).append("\n");
            mReportData[7] = " " + mCorectConsoaneNazale + " / " + mNbConsoaneNazale + " = " + String.format("%.2f", cNazalePercentage) + "%";
            mEmailBodyStringBuilder.append(getString(R.string.lbl_nazale)).append(mReportData[7]).append("\n");
            mReportData[8] = " " + mCorectConsoaneSonante + " / " + mNbConsoaneSonante + " = " + String.format("%.2f", cSonantePercentage) + "%";
            mEmailBodyStringBuilder.append(getString(R.string.lbl_sonante)).append(mReportData[8]).append("\n");
            mReportData[9] = " " + mCorectConsoaneBilabiale + " / " + mNbConsoaneBilabiale + " = " + String.format("%.2f", cBilabialePercentage) + "%";
            mEmailBodyStringBuilder.append("\n").append(getString(R.string.lbl_dupa_mod_articulare)).append("\n");
            mEmailBodyStringBuilder.append(getString(R.string.lbl_bilabiale)).append(mReportData[9]).append("\n");
            mReportData[10] = " " + mCorectConsoaneLabiodentale + " / " + mNbConsoaneLabiodentale + " = " + String.format("%.2f", cLabiodentalePercentage) + "%";
            mEmailBodyStringBuilder.append(getString(R.string.lbl_labiodentale)).append(mReportData[10]).append("\n");
            mReportData[11] = " " + mCorectConsoaneApicoDentale + " / " + mNbConsoaneApicoDentale + " = " + String.format("%.2f", cApicoDentalePercentage) + "%";
            mEmailBodyStringBuilder.append(getString(R.string.lbl_apicodentale)).append(mReportData[11]).append("\n");
            mReportData[12] = " " + mCorectConsoaneAlveolare + " / " + mNbConsoaneAlveolare + " = " + String.format("%.2f", cAlveolarePercentage) + "%";
            mEmailBodyStringBuilder.append(getString(R.string.lbl_alveolare)).append(mReportData[12]).append("\n");
            mReportData[13] = " " + mCorectConsoanePalatale + " / " + mNbConsoanePalatale + " = " + String.format("%.2f", cPalatalePercentage) + "%";
            mEmailBodyStringBuilder.append(getString(R.string.lbl_palatale)).append(mReportData[13]).append("\n");
            mReportData[14] = " " + mCorectConsoaneVelare + " / " + mNbConsoaneVelare + " = " + String.format("%.2f", cVelarePercentage) + "%";
            mEmailBodyStringBuilder.append(getString(R.string.lbl_velare)).append(mReportData[14]).append("\n");
            mReportData[15] = " " + mCorectConsoaneLaringale + " / " + mNbConsoaneLaringale + " = " + String.format("%.2f", cLaringalePercentage) + "%";
            mEmailBodyStringBuilder.append(getString(R.string.lbl_laringale)).append(mReportData[15]).append("\n");
            mEmailBodyStringBuilder.append("\n").append(getString(R.string.lbl_vocale)).append("\n").append("\n");
            mEmailBodyStringBuilder.append(getString(R.string.lbl_dupa_zona_articulare)).append("\n");
            mReportData[16] = " " + mCorectVocaleAnterioare + " / " + mNbVocaleAnterioare + " = " + String.format("%.2f", vAnterioarePercentage) + "%";
            mEmailBodyStringBuilder.append(getString(R.string.lbl_anterioare)).append(mReportData[16]).append("\n");
            mReportData[17] = " " + mCorectVocaleMediane + " / " + mNbVocaleMediane + " = " + String.format("%.2f", vMedianePercentage) + "%";
            mEmailBodyStringBuilder.append(getString(R.string.lbl_mediane)).append(mReportData[17]).append("\n");
            mReportData[18] = " " + mCorectVocalePosterioare + " / " + mNbVocalePosterioare + " = " + String.format("%.2f", vPosterioarePercentage) + "%";
            mEmailBodyStringBuilder.append(getString(R.string.lbl_posterioare)).append(mReportData[18]).append("\n");
            mEmailBodyStringBuilder.append(getString(R.string.lbl_dupa_grad_deschidere_cavitate_bucala)).append("\n");
            mReportData[19] = " " + mCorectVocaleDeschise + " / " + mNbVocaleDeschise + " = " + String.format("%.2f", vDeschisePercentage) + "%";
            mEmailBodyStringBuilder.append(getString(R.string.lbl_deschise)).append(mReportData[19]).append("\n");
            mReportData[20] = " " + mCorectVocaleSemideschise + " / " + mNbVocaleSemideschise + " = " + String.format("%.2f", vSemideschisePercentage) + "%";
            mEmailBodyStringBuilder.append(getString(R.string.lbl_semideschise)).append(mReportData[20]).append("\n");
            mReportData[21] = " " + mCorectVocaleInchise + " / " + mNbVocaleInchise + " = " + String.format("%.2f", vInchisePercentage) + "%";
            mEmailBodyStringBuilder.append(getString(R.string.lbl_inchise)).append(mReportData[21]).append("\n");
            mEmailBodyStringBuilder.append(getString(R.string.lbl_dupa_rotunjire_buze)).append("\n");
            mReportData[22] = " " + mCorectVocaleRotunjite + " / " + mNbVocaleRotunjite + " = " + String.format("%.2f", vRotunjitePercentage) + "%";
            mEmailBodyStringBuilder.append(getString(R.string.lbl_rotunjite)).append(mReportData[22]).append("\n");
            mReportData[23] = " " + mCorectVocaleNerotunjite + " / " + mNbVocaleNerotunjite + " = " + String.format("%.2f", vNerotunjitePercentage) + "%";
            mEmailBodyStringBuilder.append(getString(R.string.lbl_nerotunjite)).append(mReportData[23]).append("\n");

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }

            tv1RaspunsuriCorecte.setText(mReportData[0]);
            tv2greseliinitiale.setText(mReportData[1]);
            tv3greselimediane.setText(mReportData[2]);
            tv4greselifinale.setText(mReportData[3]);
            tv5coclusive.setText(mReportData[4]);
            tv6cfrictive.setText(mReportData[5]);
            tv7cafricate.setText(mReportData[6]);
            tv8cnazale.setText(mReportData[7]);
            tv9csonante.setText(mReportData[8]);
            tv10cbilabiale.setText(mReportData[9]);
            tv11clabiodentale.setText(mReportData[10]);
            tv12capicodentale.setText(mReportData[11]);
            tv13calveolare.setText(mReportData[12]);
            tv14cpalatale.setText(mReportData[13]);
            tv15cvelare.setText(mReportData[14]);
            tv16claringale.setText(mReportData[15]);
            tv17vanterioare.setText(mReportData[16]);
            tv18vmediane.setText(mReportData[17]);
            tv19vposterioare.setText(mReportData[18]);
            tv20vdeschise.setText(mReportData[19]);
            tv21vsemideschise.setText(mReportData[20]);
            tv22vinchise.setText(mReportData[21]);
            tv23vrotunjite.setText(mReportData[22]);
            tv24vnerotunjite.setText(mReportData[23]);
        }
    }

    private void resetNumbers() {
        answeredCorrectly = 0;
        wrongFirstPart = 0;
        wrongMiddle = 0;
        wrongEnd = 0;
        mNbConsoaneAlveolare = 0;
        mCorectConsoaneAlveolare = 0;
        mNbConsoaneApicoDentale = 0;
        mCorectConsoaneApicoDentale = 0;
        mNbConsoaneBilabiale = 0;
        mNbConsoaneLabiodentale = 0;
        mCorectConsoaneBilabiale = 0;
        mCorectConsoaneLabiodentale = 0;
        mNbConsoaneLaringale = 0;
        mCorectConsoaneLaringale = 0;
        mNbConsoanePalatale = 0;
        mCorectConsoanePalatale = 0;
        mNbConsoaneVelare = 0;
        mCorectConsoaneVelare = 0;
        mNbConsoaneAfricate = 0;
        mCorectConsoaneAfricate = 0;
        mNbConsoaneFricative = 0;
        mNbConsoaneNazale = 0;
        mCorectConsoaneNazale = 0;
        mNbConsoaneOclusive = 0;
        mCorectConsoaneOclusive = 0;
        mNbConsoaneSonante = 0;
        mCorectConsoaneSonante = 0;
        mNbVocaleAnterioare = 0;
        mCorectVocaleAnterioare = 0;
        mNbVocalePosterioare = 0;
        mNbVocaleDeschise = 0;
        mCorectVocaleInchise = 0;
        mCorectVocaleNerotunjite = 0;
        mNbVocaleNerotunjite = 0;
        mCorectVocaleRotunjite = 0;
        mNbVocaleSemideschise = 0;
        mNbVocaleRotunjite = 0;
        mNbVocaleInchise = 0;
        mCorectVocaleSemideschise = 0;
        mCorectVocaleDeschise = 0;
        mCorectVocalePosterioare = 0;
        mNbVocaleMediane = 0;
        mCorectVocaleMediane = 0;
        mCorectConsoaneFricative = 0;
    }


    private void populateAutoComplete() {
        getLoaderManager().initLoader(0, null, this);
    }

    public void attemptSendingReport() {
        mEmailView.setError(null);

        String email = mEmailView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!ValidationUtils.isValidEmailAddress(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            sendEmail();
        }
    }

    public void sendEmail() {
        if (isInternetConnected()) {
            Intent msg = new Intent(Intent.ACTION_SEND);
            msg.putExtra(Intent.EXTRA_EMAIL, new String[]{mEmailView.getText().toString()});
            String str = mNumePacient.getText().toString();
            msg.putExtra(Intent.EXTRA_SUBJECT,
                    "Raport test" + (TextUtils.isEmpty(str) ? "" : " : " + str));
            msg.putExtra(Intent.EXTRA_TEXT, (CharSequence) mEmailBodyStringBuilder);

            msg.setType("message/rfc822");
            startActivity(Intent.createChooser(msg, "Alegeti clientul de email:"));
        } else {
            Toast.makeText(this, getString(R.string.lbl_no_internet_connection), Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isInternetConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(TestReportActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }
}



