package id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Model;

public class InventarisModel {
    int id_jenis, no_aset, harga_satuan, satuan;
    String no_inventaris, nama_aset, spesifikas, tanggal_pengadaan, id_kategori;

    public InventarisModel(int id_jenis, int no_aset, int harga_satuan, int satuan, String no_inventaris, String nama_aset, String spesifikas, String tanggal_pengadaan, String id_kategori) {
        this.id_jenis = id_jenis;
        this.no_aset = no_aset;
        this.harga_satuan = harga_satuan;
        this.satuan = satuan;
        this.no_inventaris = no_inventaris;
        this.nama_aset = nama_aset;
        this.spesifikas = spesifikas;
        this.tanggal_pengadaan = tanggal_pengadaan;
        this.id_kategori = id_kategori;
    }

    public int getId_jenis() {
        return id_jenis;
    }

    public int getNo_aset() {
        return no_aset;
    }

    public int getHarga_satuan() {
        return harga_satuan;
    }

    public int getSatuan() {
        return satuan;
    }

    public String getNo_inventaris() {
        return no_inventaris;
    }

    public String getNama_aset() {
        return nama_aset;
    }

    public String getSpesifikas() {
        return spesifikas;
    }

    public String getTanggal_pengadaan() {
        return tanggal_pengadaan;
    }

    public String getId_kategori() {
        return id_kategori;
    }
}
