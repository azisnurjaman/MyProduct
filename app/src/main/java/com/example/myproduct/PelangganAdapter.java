package com.example.myproduct;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PelangganAdapter extends RecyclerView.Adapter<PelangganAdapter.ViewHolder>{
    private ArrayList<Pelanggan> listPelanggan;
    private Context context;
    private PelangganListener listener;

    public PelangganAdapter(ArrayList<Pelanggan> listPelanggan, Context context, PelangganAdapter.PelangganListener listener){
        this.listPelanggan = listPelanggan;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PelangganAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pelanggan, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull PelangganAdapter.ViewHolder holder, int position) {
        final String np = listPelanggan.get(position).getNamaPelanggan();
        final String jk = listPelanggan.get(position).getJenisKelamin();

        holder.txtNamaPelanggan.setText(np);
        holder.txtJenisKelamin.setText(jk);
        holder.imgPelangganMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.imgPelangganMenu);
                popupMenu.inflate(R.menu.pelanggan_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuPelanggan) {
                        switch (menuPelanggan.getItemId()){
                            case R.id.action_edit:
                                Intent intent = new Intent(context, PelangganActivity.class);
                                intent.putExtra("dataPelanggan", listPelanggan.get(position));
                                context.startActivity(intent);
                                return true;

                            case R.id.action_delete:
                                listener.delete(listPelanggan.get(position), position);
                                return true;

                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listPelanggan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgPelangganMenu;
        private TextView txtNamaPelanggan, txtJenisKelamin;

        public ViewHolder(@NonNull View pelangganView) {
            super(pelangganView);
            txtNamaPelanggan = pelangganView.findViewById(R.id.TxtNamaPelanggan);
            txtJenisKelamin = pelangganView.findViewById(R.id.TxtJenisKelamin);
            imgPelangganMenu = pelangganView.findViewById(R.id.ImgPelangganMenu);
        }
    }

    public interface PelangganListener{
        void delete(Pelanggan pelanggan, int position);
    }
}
