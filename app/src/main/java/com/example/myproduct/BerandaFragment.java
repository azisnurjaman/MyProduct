package com.example.myproduct;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class BerandaFragment extends Fragment {
    private String TAG = BerandaFragment.class.getSimpleName();
    private EditText editNamaProduk;
    private Button btnSubmit;
    private TextView txtNP;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_beranda, container, false);
        initView(v);
        return v;
    }

    private void initView(View v){
        editNamaProduk = v.findViewById(R.id.NamaProduk);
    }
}
