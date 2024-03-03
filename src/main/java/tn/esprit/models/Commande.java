package tn.esprit.models;
import java.sql.Date;

public class Commande {
    private int idc;
    private int idUser;
    private int idItem;
    private float total;
    private Date dateC;

    public Commande(int idc, int idUser, int idItem, float total, Date dateC) {
        this.idc = idc;
        this.idUser = idUser;
        this.idItem = idItem;
        this.total = total;
        this.dateC = dateC;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getDateC() {
        return dateC;
    }

    public void setDateC(Date dateC) {
        this.dateC = dateC;
    }
}
