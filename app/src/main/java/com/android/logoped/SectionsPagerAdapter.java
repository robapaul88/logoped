package com.android.logoped;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.android.logoped.WordImageFragment;
import com.android.logoped.model.Word;

import java.util.List;

/**
 * Created by paul on 18/02/15.
 */
public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
    private List<Word> mWords;

    public SectionsPagerAdapter(FragmentManager fm, List<Word> words) {
        super(fm);
        mWords = words;
    }

    @Override
    public Fragment getItem(int position) {
        return WordImageFragment.newInstance(mWords.get(position));
    }

    @Override
    public int getCount() {
        return mWords.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mWords.get(position).getName();
    }
}
