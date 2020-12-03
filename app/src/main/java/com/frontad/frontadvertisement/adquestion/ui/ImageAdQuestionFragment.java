package com.frontad.frontadvertisement.adquestion.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.frontad.frontadvertisement.R;
import com.frontad.frontadvertisement.adquestion.model.AdQuestion;
import com.frontad.frontadvertisement.adquestion.presenter.ImageAdQuestionPresenter;
import com.frontad.frontadvertisement.adquestion.view.ImageAdView;
import com.frontad.frontadvertisement.base.BaseFragment;
import com.frontad.frontadvertisement.databinding.FragmentImageAdQuestionBinding;
import com.google.android.material.chip.Chip;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import static android.app.Activity.RESULT_OK;


public class ImageAdQuestionFragment extends BaseFragment<FragmentImageAdQuestionBinding> implements ImageAdView {

    private ImageAdQuestionPresenter presenter;
    private Uri path;

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new ImageAdQuestionPresenter(this);

        binding.imageAdAttachedBtn.setOnClickListener(v -> presenter.onAttachImage());

        binding.questBtn.setOnClickListener(v -> presenter.onQuestClicked());


    }

    @Override
    public FragmentImageAdQuestionBinding setBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentImageAdQuestionBinding.inflate(inflater, container, false);
    }


    @Override
    public void completeQuest() {
        Navigation.findNavController(requireActivity(), R.id.main_container)
                .navigate(R.id.action_imageAdQuestionFragment_to_mainFragment);
        Toast.makeText(requireActivity().getApplicationContext(), "문의가 완료되었습니다.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failQuest() {
        Toast.makeText(requireActivity().getApplicationContext(),"실패",Toast.LENGTH_SHORT).show();
    }

    @Override
    public AdQuestion getAdQuestion() {
        return new AdQuestion(
                binding.imageAdNameEt.getText().toString(),
                getCheckedChip(),
                binding.siteEt.getText().toString(),
                0, null,getAdImage(path));
    }

    @Override
    public String getCheckedChip() {
        return ((Chip)requireView().
                findViewById(binding.categoryCg.getCheckedChipId()))
                .getText().toString();
    }



    @Override
    public void attachAdImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 1);

    }

    @Override
    public File getAdImage(Uri uri) {
        File file = new File(String.format("%s/ad_image.png",requireActivity().getFilesDir().getPath()));
        try {
            InputStream inputStream =requireActivity().getContentResolver().openInputStream(uri);

            OutputStream outputStream = new FileOutputStream(file);

            byte[] buffer =new byte[10*1024];

            while (true) {
                int byteCount = inputStream.read(buffer);
                if(byteCount<0) break;
                outputStream.write(buffer, 0, byteCount);
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data !=null) {
            binding.imageAdAttachedImg.setVisibility(View.VISIBLE);
            binding.imageAdAttachedImg.setImageURI(data.getData());
            path = data.getData();


        }
    }
}
//    private fun fileToBody(imageFile: File): MultipartBody.Part {
//        val fileReqBody = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
//        return MultipartBody.Part.createFormData("file", imageFile.name, fileReqBody)
//        }        }
