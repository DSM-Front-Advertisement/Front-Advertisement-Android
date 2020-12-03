package com.frontad.frontadvertisement.adquestion.view;

import android.net.Uri;

import com.frontad.frontadvertisement.adquestion.contract.AdQuestionContract;

import java.io.File;
import java.net.URI;


public interface ImageAdView extends AdQuestionContract.View {

    void attachAdImage();

    File getAdImage(Uri uri);
}
