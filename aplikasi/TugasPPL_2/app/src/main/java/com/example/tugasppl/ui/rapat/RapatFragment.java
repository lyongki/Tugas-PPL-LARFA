package com.example.tugasppl.ui.rapat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tugasppl.R;

public class RapatFragment extends Fragment {

    private RapatViewModel rapatViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        rapatViewModel =
                ViewModelProviders.of(this).get(RapatViewModel.class);
        View root = inflater.inflate(R.layout.fragment_rapat, container, false);
        final TextView textView = root.findViewById(R.id.text_rapat);
        rapatViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}