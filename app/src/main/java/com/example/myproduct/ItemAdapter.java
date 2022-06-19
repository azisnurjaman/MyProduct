package com.example.myproduct;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private ArrayList<Item> listItem;
    private Context context;
    private ItemListener listener;

    public ItemAdapter(ArrayList<Item> listItem, Context context, ItemListener listener){
        this.listItem = listItem;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final String np = listItem.get(position).getNamaProduk();
        final String jp = listItem.get(position).getJenisProduk();
        final Integer h = listItem.get(position).getHarga();
        final Integer s = listItem.get(position).getStok();

        holder.txtNamaProduk.setText(np);
        holder.txtJenisProduk.setText(jp);
        holder.txtHarga.setText("Rp. " + String.valueOf(h));
        holder.txtStok.setText(String.valueOf(s));
        holder.imgItemMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.imgItemMenu);
                popupMenu.inflate(R.menu.item_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.action_edit:
                                Intent intent = new Intent(context, ItemActivity.class);
                                intent.putExtra("data", listItem.get(position));
                                context.startActivity(intent);
                                return true;

                            case R.id.action_delete:
                                listener.delete(listItem.get(position), position);
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
        return listItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgItem, imgItemMenu;
        private TextView txtNamaProduk, txtJenisProduk, txtHarga, txtStok;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.ImgItem);
            txtNamaProduk = itemView.findViewById(R.id.TxtNamaProduk);
            txtJenisProduk = itemView.findViewById(R.id.TxtJenisProduk);
            txtHarga = itemView.findViewById(R.id.TxtHarga);
            txtStok = itemView.findViewById(R.id.TxtStok);
            imgItemMenu = itemView.findViewById(R.id.ImgItemMenu);
        }
    }

    public interface ItemListener{
        void delete(Item item, int position);
    }
}
