package com.git.repo.view;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.git.repo.R;
import com.git.repo.databinding.RepoItemBinding;
import com.git.repo.model.Repo;
import com.git.repo.viewmodel.MainViewModel;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoViewholder> {

    private LifecycleOwner lifecycleOwner;
    private MainViewModel mainViewModel;
    private  List<Repo> repoList = Collections.emptyList();

    RepoAdapter(LifecycleOwner owner,MainViewModel mainViewModel){
        this.lifecycleOwner = owner;
        this.mainViewModel = mainViewModel;
        mainViewModel.getRepoLivedata().observe(owner, repos -> setRepoList(repos));
    }

    @NonNull
    @Override
    public RepoViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RepoItemBinding binding = DataBindingUtil.inflate(Objects.requireNonNull(layoutInflater), R.layout.repo_item,parent,false);
        return new RepoViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewholder holder, int position) {
        holder.update(repoList.get(position));
    }

    public void setRepoList(List<Repo> repoList) {
        if(repoList!=null) {
            this.repoList = repoList;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    public static class RepoViewholder extends RecyclerView.ViewHolder{

        RepoItemBinding binding;
        public RepoViewholder(RepoItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
            itemView.getRoot().setOnClickListener(v -> {
                Toast.makeText(v.getContext(),"Click",Toast.LENGTH_SHORT).show();
            });
        }

        public void update(Repo repo){
            binding.setVariable(BR.repoItem,repo);
            binding.executePendingBindings();
        }
    }
}
