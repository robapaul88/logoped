package com.android.logoped;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.android.logoped.database.DbManager;
import com.android.logoped.interfaces.OnAnswerSelectedListener;
import com.android.logoped.model.Word;

import java.util.Collections;
import java.util.List;

public class MainActivity extends ActionBarActivity implements OnAnswerSelectedListener, View.OnClickListener, WrongAnswerDialogFragment.WrongAnswerTypeListener, WordImageFragment.OnArrowPressListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    private ViewPager mViewPager;
    private List<Word> mWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadSavedWords();
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), mWords);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        findViewById(R.id.btn_wrong).setOnClickListener(this);
        findViewById(R.id.btn_right).setOnClickListener(this);

    }

    private void loadSavedWords() {
        mWords = DbManager.getInstance(this).getAllWords();
        Collections.shuffle(mWords);
        Log.d(TAG, "Words from DB:\n" + mWords);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            new AlertDialog.Builder(this)
                    .setMessage(getString(R.string.lbl_are_you_sure_you_want_to_exit)).setPositiveButton(getString(R.string.lbl_yes), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    startActivity(new Intent(MainActivity.this, TestReportActivity.class));
                }
            }).setNegativeButton(getString(R.string.lbl_no), null).create().show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onWrongAnswerListener() {
        FragmentManager manager = getFragmentManager();
        WrongAnswerDialogFragment dialog = new WrongAnswerDialogFragment();
        dialog.show(manager, WrongAnswerDialogFragment.class.getSimpleName());
    }

    @Override
    public void onRightAnswerListener() {
        Word word = mWords.get(mViewPager.getCurrentItem());
        word.setAnswerType(Word.AnswerType.ANSWERED_CORRECTLY.mCode);
        DbManager.getInstance(this).updateWordAnswer(word);
        moveToNextWord();
    }

    private void moveToNextWord() {
        if (mViewPager.getCurrentItem() < mWords.size() - 1) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
        } else {
            new AlertDialog.Builder(this)
                    .setMessage("Testul s-a terminat!").setPositiveButton("Vizualizare raport", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    startActivity(new Intent(MainActivity.this, TestReportActivity.class));
                }
            }).create().show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_right:
                onRightAnswerListener();
                break;
            case R.id.btn_wrong:
                onWrongAnswerListener();
                break;
        }
    }

    @Override
    public void onWrongAnswerSelected(int position) {
        Word word = mWords.get(mViewPager.getCurrentItem());
        word.setAnswerType(Word.AnswerType.getByCode(position).mCode);
        DbManager.getInstance(this).updateWordAnswer(word);
        moveToNextWord();
    }

    @Override
    public void onLeftArrowPressed() {
        if (mViewPager.getCurrentItem() > 0) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
        }
    }

    @Override
    public void onRightArrowPressed() {
        moveToNextWord();
    }
}
