package com.frontad.frontadvertisement.detailad;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.frontad.frontadvertisement.base.BaseFragment;
import com.frontad.frontadvertisement.databinding.FragmentMediaAdBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;


public class MediaAdFragment extends BaseFragment<FragmentMediaAdBinding>  {


    @Override
    public FragmentMediaAdBinding setBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentMediaAdBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.adTitleTv.setText(getArguments().getString("advertisementTitle",""));
        binding.adSiteDetailTv.setText(getArguments().getString("subLink",""));
        binding.youTubePlayerView.play(getArguments().getString("youtubeLink",""),null);
        binding.adSiteDetailTv.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(binding.adSiteDetailTv.getText().toString()));
            startActivity(intent);
        });



        binding.adImgDownloadBtn.setOnClickListener(v -> {
            try {
                final Bitmap[] bitmap = {null};
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            bitmap[0] =Glide.with(requireActivity())
                                    .asBitmap()
                                    .load(String.format("https://img.youtube.com/vi/%s/0.jpg", getArguments().getString("youtubeLink","")))
                                    .submit()
                                    .get();
                        } catch (ExecutionException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                Thread t = new Thread(r);
                t.start();
                t.join();


                File filepath =  Environment.getExternalStorageDirectory();
                File dir = new File(filepath.getAbsolutePath()+"/FrontAd/");
                dir.mkdir();
                File file = new File(dir, System.currentTimeMillis()+".jpg");
                try {

                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    bitmap[0].compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    Toast.makeText(requireContext(), "다운로드에 성공하셨습니다", Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
    }
}