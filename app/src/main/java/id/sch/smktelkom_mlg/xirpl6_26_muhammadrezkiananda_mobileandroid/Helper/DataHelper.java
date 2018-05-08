package id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {

    private static final String NO_INVENTARIS = "no_inventaris";
    private static final String NAMA_ASET = "nama_aset";
    private static final String SPESIFIKASI = "spesifikasi";
    private static final String TANGGAL_PENGADAAN = "tanggal_pengadaan";
    private static final String NAMA_RUANGAN = "nama_ruangan";
    private static final String ID_KATEGORI = "id_kategori";
    private static final String ID_JENIS = "id_jenis";
    private static final String NO_ASET = "no_aset";
    private static final String HARGA_SATUAN = "harga_satuan";
    private static final String SATUAN = "satuan";

    private static final String DATABASE_NAME = "inventaris.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = " CREATE TABLE INVENTARIS("
                + NO_INVENTARIS + " text primary key, "
                + NAMA_ASET + " varchar(100) not null, "
                + SPESIFIKASI + " varchar(1000) not null, "
                + TANGGAL_PENGADAAN + " date not null, "
                + NAMA_RUANGAN + " varchar(200) not null, "
                + ID_KATEGORI + " varchar(1) not null, "
                + ID_JENIS + " int not null, "
                + NO_ASET + " int not null, "
                + HARGA_SATUAN + " int not null, "
                + SATUAN + " int not null );";

        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
