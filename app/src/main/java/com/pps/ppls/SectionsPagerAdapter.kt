package com.pps.ppls

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.pps.ppls.database.DbManager
import com.pps.ppls.model.Word
import java.util.concurrent.CopyOnWriteArrayList

/**
 * Created by paul on 18/02/15.
 */
class SectionsPagerAdapter(fragmentManager: FragmentManager, val context: Context) : FragmentStatePagerAdapter(fragmentManager) {
    private val words = CopyOnWriteArrayList<Word>()

    init {
        words.addAll(DbManager.getInstance(context).allWords)
        words.shuffle()
    }

    override fun getItem(position: Int): Fragment {
        return WordImageFragment.newInstance(words[position])
    }

    override fun getCount(): Int {
        return words.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return words[position].name
    }

    fun getWordAt(index: Int): Word {
        return words[index]
    }
}