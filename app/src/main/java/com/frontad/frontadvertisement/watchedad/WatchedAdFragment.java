package com.frontad.frontadvertisement.watchedad;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frontad.frontadvertisement.EndlessRecyclerViewScrollListener;
import com.frontad.frontadvertisement.R;
import com.frontad.frontadvertisement.main.AdAdapter;
import com.frontad.frontadvertisement.main.model.Ad;
import com.frontad.frontadvertisement.base.BaseFragment;
import com.frontad.frontadvertisement.databinding.FragmentWatchedAdBinding;
import com.frontad.frontadvertisement.view.RecyclerDecoration;
import com.frontad.frontadvertisement.view.RecyclerItemClickListener;

import java.util.List;

public class WatchedAdFragment extends BaseFragment<FragmentWatchedAdBinding> implements WatchedAdContract.View, RecyclerItemClickListener {

    private WatchedAdContract.Presenter presenter;
    private AdAdapter adAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter= new WatchedAdPresenter(this);

        adAdapter = new AdAdapter();
        adAdapter.setClickListener(this);
        adAdapter.setHasStableIds(true);
        setRecyclerView(binding.adLayout.adRv, adAdapter);
        presenter.onCreate();

    }

    @Override
    public FragmentWatchedAdBinding setBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentWatchedAdBinding.inflate(inflater, container, false);
    }

    @Override
    public void loadWatchedAds(List<Ad> ads) {
        adAdapter.setNewAdData(ads);
    }

    @Override
    public WatchedAdDao setWatchedAdForLoading() {
       return WatchedAdDatabase.getInstance(requireActivity().getApplicationContext()).watchedAdDao();
    }

    @Override
    public void onClick(int navAction, int position, Ad ad) {
        Bundle bundle = new Bundle();
        bundle.putString("advertisementTitle",adAdapter.adList.get(position).advertisementTitle);
        bundle.putString("subLink",adAdapter.adList.get(position).subLink);
        bundle.putInt("imageId",adAdapter.adList.get(position).imageId);
        bundle.putString("youtubeLink",adAdapter.adList.get(position).youtubeLink);
        presenter.onAdClicked(ad.advertisementId);
        Navigation.findNavController(requireActivity(), R.id.main_container)
                .navigate(navAction,bundle);
    }




    private void setRecyclerView(RecyclerView recyclerView, AdAdapter adapter){

        recyclerView.addItemDecoration(new RecyclerDecoration(3));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(25);
        recyclerView.setAdapter(adapter);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener((StaggeredGridLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
//                presenter.loadAds(totalItemsCount+1);
            }
        });

    }
}