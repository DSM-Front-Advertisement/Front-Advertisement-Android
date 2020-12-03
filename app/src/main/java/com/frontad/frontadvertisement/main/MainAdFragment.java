package com.frontad.frontadvertisement.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.frontad.frontadvertisement.EndlessRecyclerViewScrollListener;
import com.frontad.frontadvertisement.main.model.Ad;
import com.frontad.frontadvertisement.base.BaseFragment;
import com.frontad.frontadvertisement.R;
import com.frontad.frontadvertisement.databinding.FragmentMainBinding;
import com.frontad.frontadvertisement.view.CategoryClickListener;
import com.frontad.frontadvertisement.view.RecyclerDecoration;
import com.frontad.frontadvertisement.view.RecyclerItemClickListener;
import com.frontad.frontadvertisement.watchedad.WatchedAdDao;
import com.frontad.frontadvertisement.watchedad.WatchedAdDatabase;

import java.util.ArrayList;
import java.util.List;


public class MainAdFragment extends BaseFragment<FragmentMainBinding> implements MainAdContract.View, RecyclerItemClickListener {

    private AdAdapter adAdapter;
    private int page =0;
    private MainAdContract.Presenter presenter;

    @Override
    public FragmentMainBinding setBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentMainBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new MainAdPresenter(this);


        binding.searchImg.setOnClickListener(v -> presenter.onSearchClicked());
        binding.adSearchEt.setOnEditorActionListener((v, actionId, event) -> {
            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                presenter.onSearchClicked();
                return true;
            }
            return false;
        });
        binding.adRefreshLayout.setOnRefreshListener(() -> {
            presenter.loadAds(0);
            page =0;
            binding.adRefreshLayout.setRefreshing(false);
        });

        binding.categoryChip.setOnClickListener(v -> {
            v.setVisibility(View.GONE);
            presenter.loadAds(0);
        });

        binding.loadMoreBtn.setOnClickListener(v -> {
            page++;
            presenter.loadMoreAds(page);
        });


        CategoryClickListener categoryClickListener = category -> {
            presenter.onCategoryClicked(category);
        };
        binding.mainNavigation.getHeaderView(0).findViewById(R.id.car_tv).setOnClickListener(v -> {
            presenter.loadAds(0);
            binding.categoryChip.setVisibility(View.VISIBLE);
            binding.categoryChip.setText("자동차");
            binding.mainLayout.closeDrawer(GravityCompat.START);

            categoryClickListener.onClick("자동차");
        });
        binding.mainNavigation.getHeaderView(0).findViewById(R.id.fashion_tv).setOnClickListener(v ->{
            presenter.loadAds(0);
            binding.categoryChip.setVisibility(View.VISIBLE);
            binding.categoryChip.setText("패션");
            binding.mainLayout.closeDrawer(GravityCompat.START);

            categoryClickListener.onClick("패션");});
        binding.mainNavigation.getHeaderView(0).findViewById(R.id.public_service_tv).setOnClickListener(v -> {
            presenter.loadAds(0);
            binding.categoryChip.setVisibility(View.VISIBLE);
            binding.categoryChip.setText("공익");
            binding.mainLayout.closeDrawer(GravityCompat.START);

            categoryClickListener.onClick("공익");
        });
        binding.mainNavigation.getHeaderView(0).findViewById(R.id.place_tv).setOnClickListener(v -> {
            presenter.loadAds(0);
            binding.categoryChip.setVisibility(View.VISIBLE);
            binding.categoryChip.setText("장소");
            binding.mainLayout.closeDrawer(GravityCompat.START);

            categoryClickListener.onClick("장소");
        });
        binding.mainNavigation.getHeaderView(0).findViewById(R.id.competition_tv).setOnClickListener(v -> {
            presenter.loadAds(0);
            binding.categoryChip.setVisibility(View.VISIBLE);
            binding.categoryChip.setText("대회");
            binding.mainLayout.closeDrawer(GravityCompat.START);

            categoryClickListener.onClick("대회");
        });
        binding.mainNavigation.getHeaderView(0).findViewById(R.id.game_tv).setOnClickListener(v -> {
            presenter.loadAds(0);
            binding.categoryChip.setVisibility(View.VISIBLE);
            binding.categoryChip.setText("게임");
            binding.mainLayout.closeDrawer(GravityCompat.START);

            categoryClickListener.onClick("게임");
        });
        binding.mainNavigation.getHeaderView(0).findViewById(R.id.electronics_tv).setOnClickListener(v -> {
            presenter.loadAds(0);
            binding.categoryChip.setVisibility(View.VISIBLE);
            binding.categoryChip.setText("전자기기");
            binding.mainLayout.closeDrawer(GravityCompat.START);

            categoryClickListener.onClick("전자기기");
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        binding.mainLayout.closeDrawer(GravityCompat.START);
        presenter.onCreate();
        presenter.loadAds(0);

    }



    @Override
    public void setAdDetailDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), binding.mainLayout, binding.mainToolbar, R.string.ad_content_tv,R.string.ad_content_tv);

        binding.mainLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void setNavigationToAdQuestion() {
        binding.mainNavigation.getHeaderView(0).findViewById(R.id.ad_question_tv)
                .setOnClickListener(v -> Navigation.findNavController(requireActivity(), R.id.main_container)
                        .navigate(R.id.action_mainFragment_to_chooseAdStyleFragment));
    }

    @Override
    public void setNavigationToWatchedAd() {
        binding.mainNavigation.getHeaderView(0).findViewById(R.id.watched_ad_tv)
                .setOnClickListener(v -> Navigation.findNavController(requireActivity(), R.id.main_container)
                        .navigate(R.id.action_mainFragment_to_watchedAdFragment));
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
//                presenter.loadMoreAds(page+1);
            }
        });

    }
    @Override
    public void setUpView() {
        adAdapter = new AdAdapter();
        adAdapter.setClickListener(this);
        adAdapter.setHasStableIds(true);
        setRecyclerView(binding.adRv, adAdapter);
    }

    @Override
    public String getInputKeyword() {
        return binding.adSearchEt.getText().toString();
    }

    @Override
    public WatchedAdDao setWatchedAdRecording() {
        return WatchedAdDatabase.getInstance(requireActivity().getApplicationContext()).watchedAdDao();

    }

    @Override
    public void showCategoryChip() {
        binding.categoryChip.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideCategoryChip() {
        binding.categoryChip.setText("");
        binding.categoryChip.setVisibility(View.GONE);

    }

    @Override
    public void showToast() {
        Toast.makeText(requireActivity().getApplicationContext(), "시청 기록 해당", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setAdContents(List<Ad> adContents) {
        adAdapter.setNewAdData(adContents);

    }

    @Override
    public void loadMoreAdContents(List<Ad> adContents) {
        adAdapter.addAds(adContents);
    }

    @Override
    public void sortByCategory(String category) {
        List<Ad> sortedAds = new ArrayList<>();
        for(int i = 0; i < adAdapter.adList.size(); i++){
            if(adAdapter.adList.get(i).advertisementCategoryType.equals(category)){
                sortedAds.add(adAdapter.adList.get(i));
            }
        }
        adAdapter.setNewAdData(sortedAds);
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
}