package com.git.repo.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.git.repo.R;
import com.git.repo.Utils;
import com.git.repo.databinding.ActivityMainBinding;
import com.git.repo.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity implements EditText.OnEditorActionListener {

    private MainViewModel mainViewModel;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        //setToolbar();
        binding.recyleView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyleView.setAdapter(new RepoAdapter(this,mainViewModel));
        mainViewModel.fetchRepos();
        binding.editTextSearch.setOnEditorActionListener(this);
        Utils.hideKeyboard(this,binding.editTextSearch);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == EditorInfo.IME_ACTION_SEARCH){
            mainViewModel.searchRepos(v.getText().toString().trim());
            return true;
        }
        return false;
    }

   /* private void setToolbar() {
        Toolbar toolbar = binding.toolbar.toolbar;
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        toolbar.setSubtitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        setTitle(R.string.app_name);
    }*/
}
