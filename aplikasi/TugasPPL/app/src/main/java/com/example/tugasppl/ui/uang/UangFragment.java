package com.example.tugasppl.ui.uang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tugasppl.R;

public class UangFragment extends Fragment {

    private UangViewModel uangViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        uangViewModel =
                ViewModelProviders.of(this).get(UangViewModel.class);
        View root = inflater.inflate(R.layout.fragment_uang_masuk_keluar, container, false);
        final TextView textView = root.findViewById(R.id.text_uang);
        uangViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final Spinner dropdown = root.findViewById(R.id.spinerr_uang);
        String[] items = new String[]{"Uang Masuk","Uang Keluar"};
        dropdown.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,items));
        return root;
    }
}