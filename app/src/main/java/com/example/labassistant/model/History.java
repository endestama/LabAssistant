package com.example.labassistant.model;

public class History {
    private String hisTanggal;
    private String hisMatkul;
    private String hisJobsheet;
    private String hisSemester;
    private String hisStatus;

    public History() {
    }

    public String getHisStatus() {
        return hisStatus;
    }

    public void setHisStatus(String hisStatus) {
        this.hisStatus = hisStatus;
    }

    public String getHisTanggal() {
        return hisTanggal;
    }

    public void setHisTanggal(String hisTanggal) {
        this.hisTanggal = hisTanggal;
    }

    public String getHisMatkul() {
        return hisMatkul;
    }

    public void setHisMatkul(String hisMatkul) {
        this.hisMatkul = hisMatkul;
    }

    public String getHisJobsheet() {
        return hisJobsheet;
    }

    public void setHisJobsheet(String hisJobsheet) {
        this.hisJobsheet = hisJobsheet;
    }

    public String getHisSemester() {
        return hisSemester;
    }

    public void setHisSemester(String hisSemester) {
        this.hisSemester = hisSemester;
    }
}
