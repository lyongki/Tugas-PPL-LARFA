package com.example.tugasppl.ui.surat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tugasppl.R;

public class SuratFragment extends Fragment {

    private SuratViewModel suratViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        suratViewModel =
                ViewModelProviders.of(this).get(SuratViewModel.class);
        View root = inflater.inflate(R.layout.fragment_surat, container, false);
        final TextView textView = root.findViewById(R.id.text_surat);
        suratViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        final Spinner dropdown = root.findViewById(R.id.spinerr_surat);
        String[] items = new String[]{"Surat Masuk","Surat Keluar"};
        dropdown.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,items));
        return root;
    }
}