package com.example.myproduct;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PelangganFragment extends Fragment implements PelangganAdapter.PelangganListener{
    private String TAG = PelangganFragment.class.getSimpleName();
    private FloatingActionButton add2;
    private RecyclerView viewPelanggan;
    private ArrayList<Pelanggan> listPelanggan;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pelanggan, container, false);
        initView(v);
        layoutManager = new LinearLayoutManager(getActivity());
        viewPelanggan.setLayoutManager(layoutManager);

        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PelangganActivity.class);
                startActivity(intent);
            }
        });

        FirebaseUtils2.getRefrence(FirebaseUtils2.ITEMS_PATH).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPelanggan = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Pelanggan pelanggan = snapshot.getValue(Pelanggan.class);
                    pelanggan.setKey2(snapshot.getKey());
                    listPelanggan.add(pelanggan);
                }
                adapter = new PelangganAdapter(listPelanggan, getActivity(), PelangganFragment.this);
                viewPelanggan.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, databaseError.getDetails() + " | " + databaseError.getMessage());
            }
        });
        return v;
    }

    private void initView(View v) {
        viewPelanggan = v.findViewById(R.id.ViewPelanggan);
        add2 = v.findViewById(R.id.Add2);
    }

    @Override
    public void delete(Pelanggan pelanggan, int position) {
        FirebaseUtils2.getRefrence(FirebaseUtils2.ITEMS_PATH).child(pelanggan.getKey2()).removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getActivity(), "Pelanggan Berhasil Dihapus.", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
