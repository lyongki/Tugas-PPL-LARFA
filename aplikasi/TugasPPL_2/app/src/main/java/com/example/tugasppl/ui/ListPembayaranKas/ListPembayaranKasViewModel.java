package com.example.tugasppl.ui.ListPembayaranKas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListPembayaranKasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ListPembayaranKasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is list fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}