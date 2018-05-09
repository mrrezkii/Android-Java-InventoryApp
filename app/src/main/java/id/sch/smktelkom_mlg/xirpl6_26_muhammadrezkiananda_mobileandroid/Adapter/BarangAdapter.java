package id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Helper.DatabaseHelper;
import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.MainActivity;
import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Model.BarangModel;
import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.R;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.ViewHolder> {
    String kategori, type;
    List<BarangModel> mListData;
    DatabaseHelper db;
    private Context context;


    public BarangAdapter(Context context, List<BarangModel> mListData) {
        this.mListData = mListData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_data_alt, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final BarangModel mBarang = mListData.get(position);
        holder.tvJenis.setText(kategori);
        holder.tvTanggal.setText(mBarang.getTgl());
        holder.tvBarang.setText(type);

        if (mBarang.getIdCategory().equals("A")) {
            kategori = "Elektronik";
        } else {
            kategori = "Non-Elektronik";
        }
        switch (mBarang.getIdType()) {
            case 1:
                type = "Laptop";
                break;
            case 2:
                type = "Desktop";
                break;
            case 3:
                type = "LCD";
                break;
            case 4:
                type = "Kamera";
                break;
            case 5:
                type = "Elektronik Lainya";
                break;
            case 6:
                type = "Meja";
                break;
            case 7:
                type = "Lemari";
                break;
            case 8:
                type = "Sofa";
                break;
            case 9:
                type = "Kursi";
                break;
            case 10:
                type = "Non-Elektronik Lainnya";
                break;
        }
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).delete(mBarang.getId());

            }
        });
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvBarang, tvJenis, tvTanggal, btnDelete;
        BarangModel bm;

        public ViewHolder(View itemView) {
            super(itemView);
            tvBarang = itemView.findViewById(R.id.tvBarang);
            tvJenis = itemView.findViewById(R.id.tvJenis);
            tvTanggal = itemView.findViewById(R.id.tvTanggal);
            btnDelete = itemView.findViewById(R.id.btnDelete);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent update = new Intent(context, InputActivity.class);
                    update.putExtra("update", 1);
                    update.putExtra("IDInventory", bm.getId());
                    update.putExtra("Name", bm.getNama());
                    update.putExtra("Spec", bm.getSpek());
                    update.putExtra("Date", bm.getTgl());
                    update.putExtra("Room", bm.getRuang());
                    update.putExtra("IDCategory", bm.getIdCategory());
                    update.putExtra("IDType", bm.getIdType());
                    update.putExtra("ItemPrice", bm.getHarga());
                    update.putExtra("Quantity", bm.getSatuan());

                    context.startActivity(update);
                }
            });*/

        }
    }
}
