package id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Helper.DatabaseHelper;
import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Koneksi.VolleySingleton;
import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Model.BarangModel;
import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Model.CategoriesModel;
import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Model.CategoryTypesModel;
import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Model.UserModel;
import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Session.SessionPreference;

public class InputActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private final String url_cat = "http://10.1.5.24:8080/categories";
    private final String url_type = "http://10.1.5.24:8080/categorytypes";
    DatabaseHelper dbHelper;
    EditText noInventaris, namaAset, spesifikasi, tanggalPengadaan, namaRuangan, noAset, hargaSatuan, satuanalt, idInventori;
    Spinner spinner1, spinner2;
    Button btnTambah;
    UserModel user;
    ArrayList<String> category = new ArrayList<String>();
    ArrayList<String> elektronik = new ArrayList<String>();
    ArrayList<String> nonElektronik = new ArrayList<String>();
    ArrayAdapter<String> dataAdapter2;
    ArrayAdapter<String> dataAdapter1;
    List<CategoriesModel> categoryList;
    List<CategoryTypesModel> typeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        dbHelper = new DatabaseHelper(this);
        noInventaris = findViewById(R.id.noInventaris);
        namaAset = findViewById(R.id.namaAset);
        spesifikasi = findViewById(R.id.spesifikasi);
        tanggalPengadaan = findViewById(R.id.tanggalPengadaan);
        namaRuangan = findViewById(R.id.namaRuangan);
        noAset = findViewById(R.id.noAset);
        hargaSatuan = findViewById(R.id.hargaSatuan);
        satuanalt = findViewById(R.id.satuan);
        idInventori = findViewById(R.id.idInventaris);
        spinner1 = findViewById(R.id.spinener1);
        spinner2 = findViewById(R.id.spinener2);
        btnTambah = findViewById(R.id.btnTambah);

        if (!SessionPreference.getInstance(this).isLoaded()) {
            LoadCat();
            LoadType();
            SessionPreference.getInstance(this).Load("Loaded");
        }
        user = SessionPreference.getInstance(this).getUser();
        spinner1.setOnItemSelectedListener(this);
        dataAdapter1 = new ArrayAdapter<String>(InputActivity.this, android.R.layout.simple_spinner_dropdown_item, category);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter1);
        dataFill();

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

    }

    private void saveData() {
        String kategori;
        int type = 0;
        String nama = namaAset.getText().toString();
        String spek = spesifikasi.getText().toString();
        String date = tanggalPengadaan.getText().toString();
        String ruang = namaRuangan.getText().toString();

        String cat = spinner1.getSelectedItem().toString();
        if (cat.equals("elektronik")) {
            kategori = "A";
        } else {
            kategori = "B";
        }

        String tipe = spinner2.getSelectedItem().toString();
        switch (tipe) {
            case "laptop":
                type = 1;
                break;
            case "desktop":
                type = 2;
                break;
            case "lcd":
                type = 3;
                break;
            case "kamera":
                type = 4;
                break;
            case "elektronik lainnya":
                type = 5;
                break;
            case "meja":
                type = 6;
                break;
            case "lemari":
                type = 7;
                break;
            case "sofa":
                type = 8;
                break;
            case "kursi":
                type = 9;
                break;
            case "non elektronik lainnya":
                type = 10;
                break;
        }

        int harga = Integer.parseInt(hargaSatuan.getText().toString());
        int satuan = Integer.parseInt(satuanalt.getText().toString());

        BarangModel barang = new BarangModel(
                nama, spek, date, ruang, kategori, type, harga, satuan
        );
        dbHelper.insertBarang(barang);

        spinner1.setSelection(0);
        spinner2.setAdapter(null);
        namaAset.setText("");
        spesifikasi.setText("");
        tanggalPengadaan.setText("");
        namaRuangan.setText("");
        hargaSatuan.setText("");
        satuanalt.setText("");

        int id = dbHelper.getLastBarangID();
        Log.d("data", "" + id);
        String inventory = user.getBranch() + date.substring(0, 3) + date.substring(4, 5) + kategori + type + "/" + id;

        BarangModel barangU = new BarangModel(
                id, nama, spek, date, ruang, kategori, type, harga, satuan, inventory
        );
        dbHelper.updateBarang(barangU);

        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    private void LoadType() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_type, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        CategoryTypesModel type = new CategoryTypesModel(
                                obj.getInt("IDType"),
                                obj.getString("IDCategory"),
                                obj.getString("Type")
                        );
                        dbHelper.insertType(type);
                    }
                    //Log.d("data", db.getType().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void LoadCat() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_cat, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        CategoriesModel cat = new CategoriesModel(
                                obj.getString("IDCategory"),
                                obj.getString("Category")
                        );
                        dbHelper.insertCat(cat);
                    }
                    //Log.d("data", db.getCat().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void dataFill() {
        category.add("Pilih Kategori");
        elektronik.add("Pilih Barang");
        nonElektronik.add("Pilih Barang");
        categoryList = dbHelper.getCat();
        for (CategoriesModel cat : categoryList) {
            category.add(cat.getCategory());
        }
        dataAdapter1.notifyDataSetChanged();
        typeList = dbHelper.getType();
        for (CategoryTypesModel type : typeList) {
            if (type.getIdcategori().equals("A")) {
                elektronik.add(type.getTypes());
            } else {
                nonElektronik.add(type.getTypes());
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = parent.getItemAtPosition(position).toString();
        switch (selectedItem) {
            case "elektronik":
                setadapter(0);
                break;
            case "non elektronik":
                setadapter(1);
                break;
        }
    }

    private void setadapter(int a) {
        switch (a) {
            case 0:
                dataAdapter2 = new ArrayAdapter<String>(InputActivity.this, android.R.layout.simple_spinner_dropdown_item, elektronik);
                break;
            case 1:
                dataAdapter2 = new ArrayAdapter<String>(InputActivity.this, android.R.layout.simple_spinner_dropdown_item, nonElektronik);
                break;
        }
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter2);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
