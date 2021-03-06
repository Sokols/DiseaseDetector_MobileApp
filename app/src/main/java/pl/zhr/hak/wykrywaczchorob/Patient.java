package pl.zhr.hak.wykrywaczchorob;

import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Patient implements Parcelable {

    @PrimaryKey
    private int patientID;

    @ColumnInfo
    private String name;

    @ColumnInfo
    private String surname;

    @ColumnInfo
    private String sex;

    @ColumnInfo
    private int diseaseID;

    @ColumnInfo
    private int age;

    @ColumnInfo
    private String addedBy;

    @ColumnInfo
    private String date;

    private boolean isChecked;

    @Ignore
    public Patient(String name, String surname, String sex, int diseaseID, int age, String addedBy, String date, boolean isChecked) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.diseaseID = diseaseID;
        this.age = age;
        this.addedBy = addedBy;
        this.date = date;
        this.isChecked = isChecked;
    }

    public Patient(int patientID, String name, String surname, String sex, int diseaseID, int age, String addedBy, String date, boolean isChecked) {
        this.patientID = patientID;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.diseaseID = diseaseID;
        this.age = age;
        this.addedBy = addedBy;
        this.date = date;
        this.isChecked = isChecked;
    }

    public int getPatientID() {
        return this.patientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSex() {
        return sex;
    }

    public int getDiseaseID() {
        return diseaseID;
    }

    public int getAge() {
        return age;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public String getDate() {
        return date;
    }

    boolean getIsChecked() {
        return isChecked;
    }

    void setIsChecked(boolean checked) {
        isChecked = checked;
    }

    public String getRealSex(Context context, String sex) {
        if (sex.equals("female")) {
            return context.getString(R.string.female);
        } else {
            return context.getString(R.string.male);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(patientID);
        out.writeString(name);
        out.writeString(surname);
        out.writeString(sex);
        out.writeInt(diseaseID);
        out.writeInt(age);
        out.writeString(addedBy);
        out.writeString(date);
        out.writeBoolean(isChecked);
    }

    public static final Parcelable.Creator<Patient> CREATOR = new Parcelable.Creator<Patient>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        public Patient createFromParcel(Parcel in) {
            return new Patient(in);
        }

        public Patient[] newArray(int size) {
            return new Patient[size];
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private Patient(Parcel in) {
        patientID = in.readInt();
        name = in.readString();
        surname = in.readString();
        sex = in.readString();
        diseaseID = in.readInt();
        age = in.readInt();
        addedBy = in.readString();
        date = in.readString();
        isChecked = in.readBoolean();
    }
}
