package nz.ac.unitec.restaurantordersystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by Kay on 27/07/2016.
 */
public class Order {
    //--field--
    private UUID mId;
    private List<UUID> mOrderedDish;
    private List<Integer> mDishCount;
    private float mTotalPrice;
    private String mState;
    private Boolean mPaid;



    public Order(List<UUID> orderedDish, List<Integer> dishCount){
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

    public List<UUID> getOrderedDish() {
        return mOrderedDish;
    }

    public String getOrderedDishToString() {
        String dishIds= "";
        for(UUID dishId : mOrderedDish){
            dishId.toString();
            dishIds += dishId +",";
        }
        return dishIds;
    }

    public void setOrderedDish(String mDishId) {
        List<String> myList = new ArrayList<>(Arrays.asList(mDishId.split(",")));
        for(int i = 0; i <myList.size();i++){
            UUID uid = UUID.fromString(myList.get(i));
            this.mOrderedDish.add(uid);
        }
    }


    public void setId(UUID mId) {
        this.mId = mId;
    }

    public List<Integer> getDishCount() {
        return mDishCount;
    }

    public String getDishCountToString() {
        String dishCount= "";
        for(Integer dishcount : mDishCount){
            dishcount.toString();
            dishCount += dishcount +",";
        }
        return dishCount;
    }

    public void setDishCount(String mDishCount) {
        List<String> myList = new ArrayList<>(Arrays.asList(mDishCount.split(",")));
        for(int i = 0; i <myList.size();i++){
            Integer count = Integer.parseInt(myList.get(i));
            this.mDishCount.add(count);
        }
    }

    public String getState() {
        return mState;
    }

    public void setState(String mState) {
        this.mState = mState;
    }

    public Boolean isPaid() {
        return mPaid;
    }

    public void setPaid(Boolean mPaid) {
        this.mPaid = mPaid;
    }

    public float getPrice() {
        return mTotalPrice;
    }

    public void setPrice(float price) {
        mTotalPrice = price;
    }

}
