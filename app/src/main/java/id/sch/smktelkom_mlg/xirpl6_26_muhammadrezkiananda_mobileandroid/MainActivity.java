package id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ajts.androidmads.library.SQLiteToExcel;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Adapter.BarangAdapter;
import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Adapter.CategoriesAdapter;
import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Helper.DatabaseHelper;
import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Model.BarangModel;
import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Model.CategoriesModel;
import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Model.UserModel;
import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Session.SessionPreference;


public class MainActivity extends AppCompatActivity implements CategoriesAdapter.IBarangAdapter {

    private static final String BARANG = "barang";
    private static final int REQUEST_CODE_ADD = 88;
    FloatingActionButton btnFloat;
    Button btnLogout, btnExport;
    TextView tvNama;
    TextView tvCabang;
    DatabaseHelper db;
    private String urlData = "http://10.1.5.24:8080/categories";
    BarangAdapter adapter;
    private CategoriesAdapter mAdapter;
    private ProgressDialog mProgressDialog;
    private List<CategoriesModel> mListData;
    List<BarangModel> barangList;
    private RecyclerView recyclerViewDataBarang, recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!SessionPreference.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        db = new DatabaseHelper(this);

        /*(recyclerViewDataBarang = findViewById(R.id.recyclerview);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading ...");
        mProgressDialog.show();
        mListData = new ArrayList<>();
        getDataVolley(); */

        btnFloat = findViewById(R.id.fab);
        btnFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goAdd();
            }
        });

        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionPreference.getInstance(getApplicationContext()).logout();
            }
        });

        UserModel mUser = SessionPreference.getInstance(getApplicationContext()).getUser();
        tvNama = findViewById(R.id.tvNama);
        tvNama.setText(mUser.getName());

        tvCabang = findViewById(R.id.tvCabang);
        tvCabang.setText(mUser.getBranch());

        barangList = db.getBarang();
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new BarangAdapter(this, barangList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        btnExport = findViewById(R.id.btnExport);
        btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String directory_path = Environment.getExternalStorageDirectory().getPath() + "/Backup/";
                File file = new File(directory_path);
                if (!file.exists()) {
                    file.mkdirs();
                }
                // Export SQLite DB as EXCEL FILE
                SQLiteToExcel sqliteToExcel = new SQLiteToExcel(getApplicationContext(), DatabaseHelper.DATABASE_NAME, directory_path);
                sqliteToExcel.exportAllTables("users.xls", new SQLiteToExcel.ExportListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted(String filePath) {
                        Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
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

    @Override
    public void onBackPressed() {

    }

    public void delete(int nama) {
        db.deleteBarang(nama);
    }
}
