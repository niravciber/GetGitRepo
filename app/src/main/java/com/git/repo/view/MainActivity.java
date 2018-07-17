package com.git.repo.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.git.repo.R;
import com.git.repo.Utils;
import com.git.repo.databinding.ActivityMainBinding;
import com.git.repo.model.Repo;
import com.git.repo.viewmodel.MainViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

import static android.view.View.GONE;

public class MainActivity extends DaggerAppCompatActivity implements EditText.OnEditorActionListener {

    MainViewModel mainViewModel;
    private ActivityMainBinding binding;
    @Inject
    ViewModelProvider.Factory viewmodelFactory;
    @Inject
    RepoAdapter repoAdapter;
    @Inject
    LinearLayoutManager linearLayoutManager;
    @Inject
    DividerItemDecoration dividerItemDecoration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mainViewModel = ViewModelProviders.of(this,viewmodelFactory).get(MainViewModel.class);
        //setToolbar();
        binding.recyleView.setAdapter(repoAdapter);
        binding.recyleView.addItemDecoration(dividerItemDecoration);
        binding.recyleView.setLayoutManager(linearLayoutManager);

        mainViewModel.fetchRepos();
        binding.editTextSearch.setOnEditorActionListener(this);
        Utils.hideKeyboard(this,binding.editTextSearch);
        mainViewModel.getLoading().observe(this, aBoolean -> {
            if(aBoolean){
                binding.progressBar.setVisibility(View.VISIBLE);
            }else{
                binding.progressBar.setVisibility(GONE);
            }
        });

        mainViewModel.getRepoLivedata().observe(this, repoAdapter::setRepoList);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED ){
            mainViewModel.searchRepos(v.getText().toString().trim());
            Utils.hideKeyboard(this,binding.editTextSearch);
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
