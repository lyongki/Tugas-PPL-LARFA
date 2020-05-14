package com.example.tugasppl.ui.surat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SuratViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SuratViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is surat fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}