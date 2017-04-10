package kr.blogspot.httpcarelesssandbox.a170406hw;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by 윤현하 on 2017-04-06.
 */

public class databox implements Parcelable{

    String name;
    String phonenumber;
    String kind;
    String menu1;
    String menu2;
    String menu3;
    String website;
    String date;
    String categorynumber;

    protected databox(Parcel in) {
        name = in.readString();
        phonenumber = in.readString();
        kind = in.readString();
        menu1 = in.readString();
        menu2 = in.readString();
        menu3 = in.readString();
        website = in.readString();
        date = in.readString();
        categorynumber = in.readString();
    }

    public static final Creator<databox> CREATOR = new Creator<databox>() {
        @Override
        public databox createFromParcel(Parcel in) {
            return new databox(in);
        }

        @Override
        public databox[] newArray(int size) {
            return new databox[size];
        }
    };

    public databox() {

    }

    public void inputdata(String name, String phonenumber, String kind, String menu1, String menu2, String menu3,
                          String website, String date, String categorynumber){
        this.name=name;
        this.phonenumber=phonenumber;
        this.kind=kind;
        this.menu1=menu1;
        this.menu2=menu2;
        this.menu3=menu3;
        this.website=website;
        this.date=date;
        this.categorynumber=categorynumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phonenumber);
        dest.writeString(kind);
        dest.writeString(menu1);
        dest.writeString(menu2);
        dest.writeString(menu3);
        dest.writeString(website);
        dest.writeString(date);
        dest.writeString(categorynumber);
    }
}
