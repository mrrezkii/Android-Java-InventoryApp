package id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Model.BarangModel;
import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Model.CategoriesModel;
import id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Model.CategoryTypesModel;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DBInventory";
    private static final String TAG = "DBInventory";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_BARANG = "barang";
    private static final String TABLE_CAT = "category";
    private static final String TABLE_TYPE = "type";

    private static final String KEY_IDCATEGORY = "IDCategory";
    private static final String KEY_IDTYPE = "IDType";
    private static final String KEY_CATEGORY = "Category";
    private static final String KEY_TYPE = "Type";

    private static final String KEY_IDASSET = "IDAsset";
    private static final String KEY_NAMA = "Name";
    private static final String KEY_SPEC = "Spec";
    private static final String KEY_RUANG = "Room";
    private static final String KEY_TANGGAL = "Date";
    private static final String KEY_HARGA = "ItemPrice";
    private static final String KEY_SATUAN = "Quantity";
    private static final String KEY_NOINVENTARIS = "IDInventory";

    private static final String CREATE_TABLE_BARANG = "CREATE TABLE " + TABLE_BARANG + " (" +
            KEY_IDASSET + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAMA + " VARCHAR(100)," +
            KEY_SPEC + " TEXT," + KEY_TANGGAL + " DATE," + KEY_RUANG + " VARCHAR(200)," +
            KEY_IDCATEGORY + " VARCHAR(1)," + KEY_IDTYPE + " INTEGER," + KEY_HARGA + " INTEGER," +
            KEY_SATUAN + " INTEGER, " + KEY_NOINVENTARIS + " VARCHAR(100))";

    private static final String CREATE_TABLE_CAT = "CREATE TABLE " + TABLE_CAT + " (" +
            KEY_IDCATEGORY + " VARCHAR(1)," + KEY_CATEGORY + " VARCHAR(50))";

    private static final String CREATE_TABLE_TYPE = "CREATE TABLE " + TABLE_TYPE + " (" +
            KEY_IDTYPE + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_IDCATEGORY + " VARCHAR(1)," + KEY_TYPE + " VARCHAR(50))";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BARANG);
        db.execSQL(CREATE_TABLE_CAT);
        db.execSQL(CREATE_TABLE_TYPE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BARANG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TYPE);
    }

    public void insertCat(CategoriesModel cat) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IDCATEGORY, cat.getIdcategory());
        values.put(KEY_CATEGORY, cat.getCategory());

        db.insert(TABLE_CAT, null, values);
    }

    public List<CategoriesModel> getCat() {
        List<CategoriesModel> catList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_CAT;
        Log.e("Query :", selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                CategoriesModel cat = new CategoriesModel(
                        c.getString(c.getColumnIndex(KEY_IDCATEGORY)),
                        c.getString(c.getColumnIndex(KEY_CATEGORY))
                );
                catList.add(cat);
            } while (c.moveToNext());
        }
        return catList;
    }

    public void insertType(CategoryTypesModel type) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IDTYPE, type.getIdtypes());
        values.put(KEY_IDCATEGORY, type.getIdcategori());
        values.put(KEY_TYPE, type.getTypes());

        db.insert(TABLE_TYPE, null, values);
    }

    public List<CategoryTypesModel> getType() {
        List<CategoryTypesModel> typeList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_TYPE;
        Log.e("Query :", selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                CategoryTypesModel type = new CategoryTypesModel(
                        c.getInt(c.getColumnIndex(KEY_IDTYPE)),
                        c.getString(c.getColumnIndex(KEY_IDCATEGORY)),
                        c.getString(c.getColumnIndex(KEY_TYPE))
                );
                typeList.add(type);
            } while (c.moveToNext());
        }
        return typeList;
    }

    public void insertBarang(BarangModel barang) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAMA, barang.getNama());
        values.put(KEY_SPEC, barang.getSpek());
        values.put(KEY_TANGGAL, barang.getTgl());
        values.put(KEY_RUANG, barang.getRuang());
        values.put(KEY_IDCATEGORY, barang.getIdCategory());
        values.put(KEY_IDTYPE, barang.getIdType());
        values.put(KEY_HARGA, barang.getHarga());
        values.put(KEY_SATUAN, barang.getSatuan());

        db.insert(TABLE_BARANG, null, values);
    }

    public List<BarangModel> getBarang() {
        List<BarangModel> barangList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_BARANG;

        Log.e("Query :", selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {

                BarangModel barang = new BarangModel(
                        c.getInt(c.getColumnIndex(KEY_IDASSET)),
                        c.getString(c.getColumnIndex(KEY_NAMA)),
                        c.getString(c.getColumnIndex(KEY_SPEC)),
                        c.getString(c.getColumnIndex(KEY_TANGGAL)),
                        c.getString(c.getColumnIndex(KEY_RUANG)),
                        c.getString(c.getColumnIndex(KEY_IDCATEGORY)),
                        c.getInt(c.getColumnIndex(KEY_IDTYPE)),
                        c.getInt(c.getColumnIndex(KEY_HARGA)),
                        c.getInt(c.getColumnIndex(KEY_SATUAN)),
                        c.getString(c.getColumnIndex(KEY_NOINVENTARIS))
                );
                barangList.add(barang);
            } while (c.moveToNext());
        }
        return barangList;
    }


    public int getLastBarangID() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_BARANG + " ORDER BY " + KEY_IDASSET + " DESC LIMIT 1";
        Log.e("db", selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        return c.getInt(c.getColumnIndex(KEY_IDASSET));
    }

    public int updateBarang(BarangModel barang) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAMA, barang.getNama());
        values.put(KEY_SPEC, barang.getSpek());
        values.put(KEY_TANGGAL, barang.getTgl());
        values.put(KEY_RUANG, barang.getRuang());
        values.put(KEY_IDCATEGORY, barang.getIdCategory());
        values.put(KEY_IDTYPE, barang.getIdType());
        values.put(KEY_HARGA, barang.getHarga());
        values.put(KEY_SATUAN, barang.getSatuan());
        values.put(KEY_NOINVENTARIS, barang.getIdInventaris());

        // updating row
        return db.update(TABLE_BARANG, values, KEY_IDASSET + " = ?",
                new String[]{String.valueOf(barang.getId())});
    }

    public void deleteBarang(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BARANG, KEY_IDASSET + " = ?",
                new String[]{String.valueOf(id)});
    }
}
