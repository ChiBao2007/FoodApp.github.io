package bao.huynh.food_app_arnc.MODEL;

import java.io.Serializable;

public class CATEGORIES implements Serializable {
    public String mathucan,tenthucan, hinhthucan,hinh;

    public CATEGORIES() {
    }

    public CATEGORIES(String mathucan, String tenthucan, String hinhthucan, String hinh) {
        this.mathucan = mathucan;
        this.tenthucan = tenthucan;
        this.hinhthucan = hinhthucan;
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

    public String getHinhthucan() {
        return hinhthucan;
    }

    public void setHinhthucan(String hinhthucan) {
        this.hinhthucan = hinhthucan;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }
}
