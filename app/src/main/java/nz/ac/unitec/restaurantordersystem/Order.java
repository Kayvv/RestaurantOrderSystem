package nz.ac.unitec.restaurantordersystem;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Kay on 27/07/2016.
 */
public class Order {
    //--field--
    private UUID mId;
    private ArrayList<Dish> mOrderedDish;
    private float mTotaPrice;
    private String mState;
    private Boolean mPaid;


    //--field--
    private String mName;
    private String mDescription;
    private float mPrice;
    private String mImage;


    public Order(){
        mId = UUID.randomUUID();
        mName = "Dish test";
        mDescription = "Description test";
    }

    public Order(UUID id){
        mId = id;
        mName = "Dish test";
        mDescription = "Description test";
    }


    public UUID getId() {
        return mId;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public float getPrice() {
        return mPrice;
    }

    public void setPrice(float price) {
        mPrice = price;
    }

    public String getPhotoFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }

}
