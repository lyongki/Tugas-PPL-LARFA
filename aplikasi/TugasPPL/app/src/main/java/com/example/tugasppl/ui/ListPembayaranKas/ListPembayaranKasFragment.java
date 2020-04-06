package com.example.tugasppl.ui.ListPembayaranKas;

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

public class ListPembayaranKasFragment extends Fragment {

    private ListPembayaranKasViewModel listPembayaranKasViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listPembayaranKasViewModel =
                ViewModelProviders.of(this).get(ListPembayaranKasViewModel.class);
        View root = inflater.inflate(R.layout.fragment_list_pembayaran_kas, container, false);
        final TextView textView = root.findViewById(R.id.text_list);
        listPembayaranKasViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}