package com.frontad.frontadvertisement.detailad;

import android.Manifest;
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
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.frontad.frontadvertisement.base.BaseFragment;
import com.frontad.frontadvertisement.databinding.FragmentImageAdBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;


public class ImageAdFragment extends BaseFragment<FragmentImageAdBinding> {


    @Override
    public FragmentImageAdBinding setBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentImageAdBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.adTitleTv.setText(getArguments().getString("advertisementTitle",""));
        binding.adSiteDetailTv.setText(getArguments().getString("subLink",""));
        Glide.with(binding.adImg)
                .load(String.format("https://s3-1-advertisement.s3.ap-northeast-2.amazonaws.com/%s",getArguments().getInt("imageId")))
                .into(binding.adImg);
        binding.adSiteDetailTv.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(binding.adSiteDetailTv.getText().toString()));
            startActivity(intent);
        });


        binding.adImgDownloadBtn.setOnClickListener(v -> {

            Bitmap bitmap = ((BitmapDrawable)binding.adImg.getDrawable()).getBitmap();

            File filepath =  Environment.getExternalStorageDirectory();
            File dir = new File(filepath.getAbsolutePath()+"/FrontAd/");
            dir.mkdir();
            File file = new File(dir, System.currentTimeMillis()+".jpg");
            try {

                FileOutputStream fileOutputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                Toast.makeText(requireContext(), "다운로드에 성공하셨습니다", Toast.LENGTH_LONG).show();

            } catch (IOException e) {
                e.printStackTrace();
            }


        });

    }
}