package id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Model.CategoriesModel;
import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.R;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.Holder> {

    IBarangAdapter iMBarangAdapter;
    private List<CategoriesModel> mListData;
    private Context mContext;

    public CategoriesAdapter(List<CategoriesModel> mListData, Context mContext) {
        this.mListData = mListData;
        this.mContext = mContext;
        iMBarangAdapter = (IBarangAdapter) mContext;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.adapter_item_data, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        CategoriesModel model = mListData.get(position);

        holder.tvJenis.setText(model.getCategory());


    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public interface IBarangAdapter {
        void doClick(int pos);
    }


    public class Holder extends RecyclerView.ViewHolder {
        TextView tvJenis;

        public Holder(View itemView) {
            super(itemView);

            tvJenis = itemView.findViewById(R.id.tvJenis);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iMBarangAdapter.doClick(getAdapterPosition());
                }
            });
        }
    }
}
