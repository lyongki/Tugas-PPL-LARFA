package com.example.tugasppl.ui.rapat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RapatViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RapatViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is rapat fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}