package kr.blogspot.httpcarelesssandbox.a170406hw;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 윤현하 on 2017-04-06.
 */

public class databox implements Parcelable{

    private String name="";
    private String phonenumber="";
    private String menu1="";
    private String menu2="";
    private String menu3="";
    private String website="";
    private String date="";
    private String categorynumber="";

    protected databox(Parcel in) {
        name = in.readString();
        phonenumber = in.readString();
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

    public void inputdata(String name, String phonenumber, String menu1, String menu2, String menu3,
                          String website, String date, String categorynumber){
        this.name=name;
        this.phonenumber=phonenumber;
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
        dest.writeString(menu1);
        dest.writeString(menu2);
        dest.writeString(menu3);
        dest.writeString(website);
        dest.writeString(date);
        dest.writeString(categorynumber);
    }
}
