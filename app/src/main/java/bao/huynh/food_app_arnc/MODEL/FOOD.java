package bao.huynh.food_app_arnc.MODEL;

import java.io.Serializable;

public class FOOD implements Serializable {
 public String mathucan, tenthucan, dongia, mota,hinhthucan, maloaithucan, xuatxu, danhgia, soluongdanhgia,thoigian, soluongban,hinh;

    public FOOD() {
    }

    public FOOD(String mathucan, String tenthucan, String dongia, String mota, String hinhthucan, String maloaithucan, String xuatxu, String danhgia, String soluongdanhgia, String thoigian, String soluongban, String hinh) {
        this.mathucan = mathucan;
        this.tenthucan = tenthucan;
        this.dongia = dongia;
        this.mota = mota;
        this.hinhthucan = hinhthucan;
        this.maloaithucan = maloaithucan;
        this.xuatxu = xuatxu;
        this.danhgia = danhgia;
        this.soluongdanhgia = soluongdanhgia;
        this.thoigian = thoigian;
        this.soluongban = soluongban;
        this.hinh = hinh;
    }

    public String getMathucan() {
        return mathucan;
    }

    public void setMathucan(String mathucan) {
        this.mathucan = mathucan;
    }

    public String getTenthucan() {
        return tenthucan;
    }

    public void setTenthucan(String tenthucan) {
        this.tenthucan = tenthucan;
    }

    public String getDongia() {
        return dongia;
    }

    public void setDongia(String dongia) {
        this.dongia = dongia;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getHinhthucan() {
        return hinhthucan;
    }

    public void setHinhthucan(String hinhthucan) {
        this.hinhthucan = hinhthucan;
    }

    public String getMaloaithucan() {
        return maloaithucan;
    }

    public void setMaloaithucan(String maloaithucan) {
        this.maloaithucan = maloaithucan;
    }

    public String getXuatxu() {
        return xuatxu;
    }

    public void setXuatxu(String xuatxu) {
        this.xuatxu = xuatxu;
    }

    public String getDanhgia() {
        return danhgia;
    }

    public void setDanhgia(String danhgia) {
        this.danhgia = danhgia;
    }

    public String getSoluongdanhgia() {
        return soluongdanhgia;
    }

    public void setSoluongdanhgia(String soluongdanhgia) {
        this.soluongdanhgia = soluongdanhgia;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getSoluongban() {
        return soluongban;
    }

    public void setSoluongban(String soluongban) {
        this.soluongban = soluongban;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }
}
