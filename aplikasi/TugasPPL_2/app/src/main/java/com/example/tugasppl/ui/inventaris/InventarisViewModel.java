package com.example.tugasppl.ui.inventaris;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InventarisViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public InventarisViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is inventaris fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
