/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package b1.entity;

import java.util.Date;

/**
 *
 * @author DELL
 */
public class MauSanPham {
    private String idmau;
    private String tenmau;
      private int delete;
    private Date createdat;
    private String createby;
    private Date updateat;
    private String updateby;

    public MauSanPham() {
    }

    public MauSanPham(String idmau, String tenmau, int delete, String createby, String updateby) {
        this.idmau = idmau;
        this.tenmau = tenmau;
        this.delete = delete;
        this.createby = createby;
        this.updateby = updateby;
    }

   

    public String getIdmau() {
        return idmau;
    }

    public void setIdmau(String idmau) {
        this.idmau = idmau;
    }

    public String getTenmau() {
        return tenmau;
    }

    public void setTenmau(String tenmau) {
        this.tenmau = tenmau;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public Date getUpdateat() {
        return updateat;
    }

    public void setUpdateat(Date updateat) {
        this.updateat = updateat;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby;
    }

    @Override
    public String toString() {
        return "MauSanPham{" + "idmau=" + idmau + ", tenmau=" + tenmau + ", delete=" + delete + ", createdat=" + createdat + ", createby=" + createby + ", updateat=" + updateat + ", updateby=" + updateby + '}';
    }

    
    
    
    
}
