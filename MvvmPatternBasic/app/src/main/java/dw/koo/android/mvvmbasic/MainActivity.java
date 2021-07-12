package dw.koo.android.mvvmbasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import dw.koo.android.mvvmbasic.databinding.ActivityMainBinding;
import dw.koo.android.mvvmbasic.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMainViewModel = new MainViewModel(this);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setModel(mMainViewModel);
        mMainViewModel.onCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainViewModel.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainViewModel.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMainViewModel.onPause();
    }
}