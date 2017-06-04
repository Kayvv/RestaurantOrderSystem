package nz.ac.unitec.restaurantordersystem;

/**
 * Created by Kay on 27/07/2016.
 */
public class User {
    //--field--
    private String mName;
    private Integer mPhoneNumber;
    private String mAddress;

    public User(){
        mName = "Kay";
        mPhoneNumber = 12345678;
        mAddress = "Earth";
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public Integer getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(Integer mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }
}
