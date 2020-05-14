package com.example.tugasppl.ui.evaluasi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EvaluasiViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EvaluasiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is evaluasi fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}