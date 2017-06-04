package nz.ac.unitec.restaurantordersystem;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kay on 2016/12/21.
 */
public class ShoppingCart {

    private static ShoppingCart sShoppingCart;
    private Context mContext;
    private List<Dish> orderedDishes=new ArrayList<>();
    private List<Integer> dishCount=new ArrayList<>();
    private Float mTotalPrice;



    private ShoppingCart(Context appContext) {
        mContext = appContext.getApplicationContext();
    }

    public static ShoppingCart get(Context context){
        if(sShoppingCart == null){
            sShoppingCart = new ShoppingCart(context.getApplicationContext());
        }
        return sShoppingCart;
    }

    public void addDish(Dish d){
        Boolean alreadyIn = false;
        if(orderedDishes.size()== 0){
            orderedDishes.add(d);
            dishCount.add(1);
        }else{
            for(int i = 0;i<orderedDishes.size();i++){
                if (d == orderedDishes.get(i)) {
                    dishCount.set(i, dishCount.get(i) + 1);
                    alreadyIn =true;
                }
            }
            if(alreadyIn){
            }
            else {
                orderedDishes.add(d);
                dishCount.add(1);
            }
        }
    }

    public List<Dish> getDishes(){
        return orderedDishes;
    }

    public List<Integer> getDishCount(){
        return dishCount;
    }

    public void decreaseCount(Dish d){
        if(orderedDishes.size()== 0){
            orderedDishes.add(d);
            dishCount.add(1);
        }else{
            for(int i = 0;i<orderedDishes.size();i++){
                if (d == orderedDishes.get(i)) {
                    if(dishCount.get(i)>0){
                        dishCount.set(i, dishCount.get(i) - 1);
                    }
                }
            }
        }
        setTotalPrice(d);
    }

    public void renewList(){
            for(int i = 0;i<dishCount.size();i++){
                if (dishCount.get(i) <= 0) {
                    dishCount.remove(i);
                    orderedDishes.remove(i);
                    Log.d("remove",dishCount.toString());
                }
            }
    }

    public File getPhotoFile(Dish dish) {
        File externalFilesDir = mContext
                .getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (externalFilesDir == null) {
            return null;
        }
        return new File(externalFilesDir, dish.getPhotoFilename());
    }

    public Float getTotalPrice() {
        return mTotalPrice;
    }

    public void setTotalPrice(Dish d) {
        d.getPrice();

        this.mTotalPrice = mTotalPrice;
    }
}
