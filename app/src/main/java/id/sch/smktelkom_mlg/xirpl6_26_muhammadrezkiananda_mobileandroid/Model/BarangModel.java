package id.sch.smktelkom_mlg.xirpl6_26_muhammadrezkiananda_mobileandroid.Model;

public class BarangModel {
    int id, idType, harga, satuan;
    String spek, ruang, tgl, idCategory, nama, idInventaris;

    public BarangModel(int id, String nama, String spek, String tgl, String ruang,
                       String idCategory, int idType, int harga, int satuan, String idInventaris) {
        this.id = id;
        this.idType = idType;
        this.harga = harga;
        this.satuan = satuan;
        this.spek = spek;
        this.ruang = ruang;
        this.tgl = tgl;
        this.idCategory = idCategory;
        this.nama = nama;
        this.idInventaris = idInventaris;
    }

    public BarangModel(String nama, String spek, String tgl, String ruang,
                       String idCategory, int idType, int harga, int satuan) {
        this.idType = idType;
        this.harga = harga;
        this.satuan = satuan;
        this.spek = spek;
        this.ruang = ruang;
        this.tgl = tgl;
        this.idCategory = idCategory;
        this.nama = nama;
    }

    public BarangModel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getIdType() {
        return idType;
    }

    public int getHarga() {
        return harga;
    }

    public int getSatuan() {
        return satuan;
    }

    public String getSpek() {
        return spek;
    }

    public String getRuang() {
        return ruang;
    }

    public String getTgl() {
        return tgl;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public String getNama() {
        return nama;
    }

    public String getIdInventaris() {
        return idInventaris;
    }
}
