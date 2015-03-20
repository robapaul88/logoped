package com.android.logoped;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.logoped.model.Word;
import com.android.logoped.utils.AudioUtils;
import com.android.logoped.utils.BitmapWorkerTask;
import com.android.logoped.utils.ImageUtils;

/**
 * Created by paul on 16/02/15.
 */
public class WordImageFragment extends Fragment implements View.OnClickListener {

    public interface OnArrowPressListener {
        public void onLeftArrowPressed();

        public void onRightArrowPressed();
    }

    private static final String EXTRA_WORD_TO_DISPLAY = "EXTRA_WORD_TO_DISPLAY";
    private static final String TAG = WordImageFragment.class.getSimpleName();

    private ImageView mImageView;
    private TextView mTextView;
    private Word mDisplayedWord;
    private BitmapWorkerTask mBitmapTask;
    private OnArrowPressListener mListener;

    public static WordImageFragment newInstance(Word word) {
        WordImageFragment fragment = new WordImageFragment();
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_WORD_TO_DISPLAY, word);
        fragment.setArguments(args);
        return fragment;
    }

    public WordImageFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (OnArrowPressListener) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDisplayedWord = getArguments().getParcelable(EXTRA_WORD_TO_DISPLAY);
            Log.d(TAG, "Displaying:" + mDisplayedWord);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mImageView = (ImageView) rootView.findViewById(R.id.imv_image);
        mBitmapTask = new BitmapWorkerTask(mImageView);
        if (mDisplayedWord != null) {
            mBitmapTask.execute(ImageUtils.getImageResIdByName(getActivity(), mDisplayedWord.getImageName()));
            mTextView = (TextView) rootView.findViewById(R.id.word_name_tv);
            String wordName = mDisplayedWord.getName();
            if (!TextUtils.isEmpty(wordName)) {
                mTextView.setText(Html.fromHtml(wordName));
            }
        }
        rootView.findViewById(R.id.main_frame).setOnClickListener(this);
        rootView.findViewById(R.id.arrow_left).setOnClickListener(this);
        rootView.findViewById(R.id.arrow_right).setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Drawable drawable = mImageView.getDrawable();
        if (drawable != null && drawable instanceof BitmapDrawable) {
            Bitmap bmp = ((BitmapDrawable) drawable).getBitmap();
            if (bmp != null && !bmp.isRecycled()) {
                bmp.recycle();
            }
        }
        mImageView.setImageBitmap(null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_frame:
                int soundId = AudioUtils.getRawResIdByName(getActivity(), mDisplayedWord.getAudioName());
                if (soundId != 0) {
                    final MediaPlayer mp = MediaPlayer.create(getActivity(), soundId);
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }

                    });
                    mp.start();
                }
                break;
            case R.id.arrow_left:
                mListener.onLeftArrowPressed();
                break;
            case R.id.arrow_right:
                mListener.onRightArrowPressed();
                break;
        }
    }
}
