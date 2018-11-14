package com.pps.ppls;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pps.ppls.model.Word;
import com.pps.ppls.utils.AudioUtils;
import com.pps.ppls.utils.BitmapWorkerTask;
import com.pps.ppls.utils.ImageUtils;

/**
 * Created by paul on 16/02/15.
 */
public class WordImageFragment extends Fragment implements View.OnClickListener {

    public interface OnArrowPressListener {
        void onLeftArrowPressed();

        void onRightArrowPressed();
    }

    private static final String EXTRA_WORD_TO_DISPLAY = "EXTRA_WORD_TO_DISPLAY";
    private static final String TAG = WordImageFragment.class.getSimpleName();

    private ImageView mImageView;
    private Word mDisplayedWord;
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

    @Override public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (OnArrowPressListener) context;
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mImageView = rootView.findViewById(R.id.imv_image);
        BitmapWorkerTask bitmapWorkerTask = new BitmapWorkerTask(mImageView);
        if (mDisplayedWord != null) {
            bitmapWorkerTask.execute(ImageUtils.getImageResIdByName(getActivity(), mDisplayedWord.getImageName()));
            TextView textView = rootView.findViewById(R.id.word_name_tv);
            String wordName = mDisplayedWord.getName();
            if (!TextUtils.isEmpty(wordName)) {
                textView.setText(Html.fromHtml(wordName));
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
        if (drawable instanceof BitmapDrawable) {
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
                    mp.setOnCompletionListener(MediaPlayer::release);
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
