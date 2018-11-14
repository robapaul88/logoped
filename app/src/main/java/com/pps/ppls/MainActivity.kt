package com.pps.ppls

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.pps.ppls.database.DbManager
import com.pps.ppls.interfaces.OnAnswerSelectedListener
import com.pps.ppls.model.Word

class MainActivity : AppCompatActivity(), OnAnswerSelectedListener, WrongAnswerDialogFragment.WrongAnswerTypeListener, WordImageFragment.OnArrowPressListener {

    private lateinit var viewPager: ViewPager
    private val wordsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.pager)
        viewPager.adapter = wordsPagerAdapter
        findViewById<View>(R.id.btn_wrong).setOnClickListener { onWrongAnswerListener() }
        findViewById<View>(R.id.btn_right).setOnClickListener { onRightAnswerListener() }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_settings) {
            AlertDialog.Builder(this)
                    .setMessage(getString(R.string.lbl_are_you_sure_you_want_to_exit))
                    .setPositiveButton(getString(R.string.lbl_yes)) { _, _ ->
                        finish()
                        startActivity(Intent(this@MainActivity, TestReportActivity::class.java))
                    }
                    .setNegativeButton(getString(R.string.lbl_no), null)
                    .create()
                    .show()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onWrongAnswerListener() {
        WrongAnswerDialogFragment().show(fragmentManager, WrongAnswerDialogFragment::class.java.simpleName)
    }

    override fun onRightAnswerListener() {
        val word = wordsPagerAdapter.getWordAt(viewPager.currentItem)
        word.answerType = Word.AnswerType.ANSWERED_CORRECTLY.mCode
        DbManager.getInstance(this).updateWordAnswer(word)
        moveToNextWord()
    }

    private fun moveToNextWord() {
        if (viewPager.currentItem < wordsPagerAdapter.count - 1) {
            viewPager.currentItem = viewPager.currentItem + 1
        } else {
            onTestDone()
        }
    }

    override fun onWrongAnswerSelected(position: Int) {
        val word = wordsPagerAdapter.getWordAt(viewPager.currentItem)
        word.answerType = Word.AnswerType.getByCode(position).mCode
        DbManager.getInstance(this).updateWordAnswer(word)
        moveToNextWord()
    }

    override fun onLeftArrowPressed() {
        if (viewPager.currentItem > 0) {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    override fun onRightArrowPressed() {
        moveToNextWord()
    }

    private fun onTestDone() {
        AlertDialog.Builder(this@MainActivity)
                .setMessage("Testul s-a terminat!").setPositiveButton("Vizualizare raport") { _, _ ->
                    finish()
                    startActivity(Intent(this@MainActivity, TestReportActivity::class.java))
                }.create().show()
    }
}
