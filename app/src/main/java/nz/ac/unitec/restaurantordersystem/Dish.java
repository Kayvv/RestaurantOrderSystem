package nz.ac.unitec.restaurantordersystem;

import java.util.UUID;

/**
 * Created by Kay on 27/07/2016.
 */
public class Dish {
    //--field--
    private UUID mId;
    private String mName;
    private String mDescription;
    private float mPrice;
    private String mImage;


    public Dish(){
        mId = UUID.randomUUID();
        mName = "Dish test";
        mDescription = "Description test";
    }

    public Dish(UUID id){
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
