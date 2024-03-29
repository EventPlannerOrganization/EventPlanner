package models;

import java.util.Objects;


public class Name {
    private String fName;
    private String mName;
    private String lName;

    public Name(String fName, String mName, String lName) {
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(fName, name.fName) && Objects.equals(mName, name.mName) && Objects.equals(lName, name.lName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fName, mName, lName);
    }

    @Override
    public String toString() {
        return "Name: " + fName + " " +mName + " " + lName ;
    }
}
