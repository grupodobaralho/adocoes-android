package br.pucrs.ages.adocoes.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by matheusvaccaro on 13/10/17.
 */

public class RefMidia implements Serializable, Parcelable {

    @SerializedName("_id")
    private String id;
    private String type;
    private String descricao;
    private boolean principal;

    public RefMidia() {
    }

    public RefMidia(String id, String type, String descricao, boolean principal) {
        this.id = id;
        this.type = type;
        this.descricao = descricao;
        this.principal = principal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public RefMidia(Parcel in){
        String[] data = new String[4];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method
        this.id = data[0];
        this.type = data[1];
        this.descricao = data[2];
        this.principal = Boolean.valueOf(data[3]);

    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(
                new String[] {
                        this.id,
                        this.type,
                        this.descricao,
                        Boolean.toString(this.principal)
        });
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public RefMidia createFromParcel(Parcel in) {
            return new RefMidia(in);
        }

        public RefMidia[] newArray(int size) {
            return new RefMidia[size];
        }
    };
}

