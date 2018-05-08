package id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Adapter.CategoriesAdapter;
import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Model.CategoriesModel;


public class MainActivity extends AppCompatActivity implements CategoriesAdapter.IBarangAdapter {

    private static final String BARANG = "barang";
    private static final int REQUEST_CODE_ADD = 88;
    FloatingActionButton btnFloat;
    private String urlData = "http://10.1.5.24:8080/categories";
    private RecyclerView recyclerViewDataBarang;
    private CategoriesAdapter mAdapter;
    private ProgressDialog mProgressDialog;
    private List<CategoriesModel> mListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewDataBarang = findViewById(R.id.recyclerview);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading ...");
        mProgressDialog.show();

        mListData = new ArrayList<>();

        getDataVolley();

        btnFloat = findViewById(R.id.fab);
        btnFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goAdd();
            }
        });
    }

    private void goAdd() {
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivityForResult(intent, REQUEST_CODE_ADD);
    }

    private void getDataVolley() {

        final StringRequest request = new StringRequest(Request.Method.POST, urlData,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mProgressDialog.dismiss();
                        iniData(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void iniData(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String kategori = obj.getString("Category");
                Log.d("data", "onResponse: " + kategori);
                CategoriesModel mCategory = new CategoriesModel();
                mCategory.setCategory(kategori);

                //add model ke list
                mListData.add(mCategory);

                //passing data list ke adapter
                mAdapter = new CategoriesAdapter(mListData, MainActivity.this);
                mAdapter.notifyDataSetChanged();
                recyclerViewDataBarang.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerViewDataBarang.setItemAnimator(new DefaultItemAnimator());
                recyclerViewDataBarang.setAdapter(mAdapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doClick(int pos) {
        Intent intent = new Intent(this, BarangActivity.class);
        intent.putExtra(BARANG, mListData.get(pos));
        startActivity(intent);
    }

}
