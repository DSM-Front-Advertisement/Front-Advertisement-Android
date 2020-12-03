package com.frontad.frontadvertisement.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.frontad.frontadvertisement.R;
import com.frontad.frontadvertisement.main.model.Ad;
import com.frontad.frontadvertisement.base.BaseViewHolder;
import com.frontad.frontadvertisement.databinding.AdImageItemBinding;
import com.frontad.frontadvertisement.databinding.AdMediaItemBinding;
import com.frontad.frontadvertisement.view.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;


public class AdAdapter extends RecyclerView.Adapter<BaseViewHolder<? extends ViewBinding>>{

    private RecyclerItemClickListener clickListener;

    public List<Ad> adList = new ArrayList<>();

    private List<Ad> sortedList = new ArrayList<>();

    public void setNewAdData(List<Ad> adList){
        DiffCallback diffCallback = new DiffCallback(this.adList, adList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        diffResult.dispatchUpdatesTo(this);
        this.adList = adList;
    }

    @Override
    public void onViewAttachedToWindow(@NonNull BaseViewHolder<? extends ViewBinding> holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull BaseViewHolder<? extends ViewBinding> holder) {
        super.onViewDetachedFromWindow(holder);
    }

    public void sortAds(List<Ad> adList){
        this.sortedList = this.adList;
        DiffCallback diffCallback = new DiffCallback(this.adList, adList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        diffResult.dispatchUpdatesTo(this);
        this.sortedList = adList;
    }

    public void addAds(List<Ad> adList){
        DiffCallback diffCallback = new DiffCallback(this.adList, adList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        diffResult.dispatchUpdatesTo(this);
        this.adList.addAll(adList);
    }


    @NonNull
    @Override
    public BaseViewHolder<? extends ViewBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==0)
            return new ImageViewHolder(AdImageItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        else
            return new MediaViewHolder(AdMediaItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bind(adList.get(position));
        if (holder.getItemViewType()==0) {
            holder.itemView.setOnClickListener(v ->
                    clickListener.onClick(R.id.action_global_imageAdFragment, position, adList.get(position)));
        }
        else{
            holder.itemView.setOnClickListener(v ->
                    clickListener.onClick(R.id.action_global_mediaAdFragment, position, adList.get(position)));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return adList.get(position).mediaType;
    }

    @Override
    public int getItemCount() {
        return adList.size();
    }



    public void setClickListener(RecyclerItemClickListener clickListener){
        this.clickListener = clickListener;
    }



    static class ImageViewHolder extends BaseViewHolder<AdImageItemBinding> {
        public ImageViewHolder(@NonNull AdImageItemBinding binding) {
            super(binding);
        }

        public void bind(Ad ad){
            Glide.with(binding.adImg)
                    .load(String.format("https://s3-1-advertisement.s3.ap-northeast-2.amazonaws.com/%s",ad.imageId))
                    .error(R.drawable.ic_error)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.adImg);

        }
    }

    static class MediaViewHolder extends BaseViewHolder<AdMediaItemBinding> {
        public MediaViewHolder(@NonNull AdMediaItemBinding binding) {
            super(binding);
        }

        public void bind(Ad ad){
            Glide.with(binding.mediaThumbnailImg)
                    .load(String.format("https://img.youtube.com/vi/%s/0.jpg", ad.youtubeLink))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.mediaThumbnailImg);

        }
    }



}


class DiffCallback extends DiffUtil.Callback{
    private final List<Ad> oldList;
    private final List<Ad> newList;

    public DiffCallback(List<Ad> oldList, List<Ad> newList){
        this.oldList = oldList;
        this.newList = newList;
    }
    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition) == newList.get(newItemPosition);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return areItemsTheSame(oldItemPosition, newItemPosition);
    }
}
