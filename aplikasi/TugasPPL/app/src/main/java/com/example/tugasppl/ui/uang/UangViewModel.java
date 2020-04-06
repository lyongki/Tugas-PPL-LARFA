package com.example.tugasppl.ui.uang;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UangViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UangViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is uang fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}