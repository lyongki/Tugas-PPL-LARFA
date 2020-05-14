package com.example.tugasppl.ui.evaluasi;

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

public class EvaluasiFragment extends Fragment {

    private EvaluasiViewModel evaluasiViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        evaluasiViewModel =
                ViewModelProviders.of(this).get(EvaluasiViewModel.class);
        View root = inflater.inflate(R.layout.fragment_evaluasi, container, false);
        final TextView textView = root.findViewById(R.id.text_evaluasi);
        evaluasiViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}