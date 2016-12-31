package nz.ac.unitec.restaurantordersystem;

import java.util.List;
import java.util.UUID;

/**
 * Created by Kay on 27/07/2016.
 */
public class Order {
    //--field--
    private UUID mId;
    private List<Dish> mOrderedDish;
    private List<Integer> mDishCount;
    private float mTotalPrice;
    private String mState;
    private Boolean mPaid;



    public Order(List<Dish> orderedDish, List<Integer> dishCount){
        mId = UUID.randomUUID();
        mOrderedDish = orderedDish;
        mDishCount = dishCount;
        mPaid = false;
    }

    public Order(UUID id){
        mId = id;
    }


    public UUID getId() {
        return mId;
    }


    public float getPrice() {
        return mTotalPrice;
    }

    public void setPrice(float price) {
        mTotalPrice = price;
    }

}
