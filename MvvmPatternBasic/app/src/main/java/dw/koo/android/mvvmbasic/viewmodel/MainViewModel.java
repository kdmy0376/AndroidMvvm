package dw.koo.android.mvvmbasic.viewmodel;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.RatingBar;

import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import dw.koo.android.mvvmbasic.MainActivity;

public class MainViewModel extends BaseViewModel {
    public final ObservableField<String> helloText = new ObservableField<>();

    public ObservableField<String> mUserName = new ObservableField<>();
    public ObservableField<String> mEmail = new ObservableField<>();
    public ObservableInt mScore = new ObservableInt();
    public ObservableBoolean mIsValid = new ObservableBoolean();

    private MainActivity mActivity;

    public MainViewModel(MainActivity activity) {
        mActivity = activity;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        helloText.set("Hello!!!");

        mScore.set(0);
        mIsValid.set(false);

        mUserName.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) { validation();
            }
        });

        mEmail.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) { validation();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public RatingBar.OnRatingBarChangeListener mScoreChangeListener = (ratingBar, rating, fromUser) -> {
        mScore.set((int) rating);
        validation();
    };

    private void validation() {
        boolean isValidName = !TextUtils.isEmpty(mUserName.get());
        boolean isValidEmail = !TextUtils.isEmpty(mEmail.get()) && Patterns.EMAIL_ADDRESS.matcher(mEmail.get()).matches();
        boolean isValidScore = mScore.get() > 0;
        mIsValid.set(isValidName && isValidEmail && isValidScore);
    }

    public void showCurrentTime() {
        helloText.set(String.valueOf(System.currentTimeMillis()));
    }

    public final View.OnClickListener mCurrentTimeCLickListener = v -> showCurrentTime();

    public final View.OnClickListener mOkClickListener = v -> mActivity.finish();
}