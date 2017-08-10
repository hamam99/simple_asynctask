
package com.code_breaker.tesandroidbootcamp.entity;

import java.io.Serializable;

public class Wisata implements Serializable {

    private String namaPariwisata;
    private String alamatPariwisata;
    private String detailPariwisata;
    private String gambarPariwisata;

    public String getNamaPariwisata() {
        return namaPariwisata;
    }

    public void setNamaPariwisata(String namaPariwisata) {
        this.namaPariwisata = namaPariwisata;
    }

    public String getAlamatPariwisata() {
        return alamatPariwisata;
    }

    public void setAlamatPariwisata(String alamatPariwisata) {
        this.alamatPariwisata = alamatPariwisata;
    }

    public String getDetailPariwisata() {
        return detailPariwisata;
    }

    public void setDetailPariwisata(String detailPariwisata) {
        this.detailPariwisata = detailPariwisata;
    }

    public String getGambarPariwisata() {
        return gambarPariwisata;
    }

    public void setGambarPariwisata(String gambarPariwisata) {
        this.gambarPariwisata = gambarPariwisata;
    }

}
