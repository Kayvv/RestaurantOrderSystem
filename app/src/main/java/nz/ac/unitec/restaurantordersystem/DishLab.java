package nz.ac.unitec.restaurantordersystem;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Kay on 27/07/2016.
 */
public class DishLab {
    private ArrayList<Dish> mDishes;
    private static DishLab sDishLab;
    private Context mAppContext;

    private DishLab(Context appContext) {
        mAppContext = appContext;
        mDishes = new ArrayList<Dish>();
        for (int i = 0; i < 100; i++) {
            Dish d = new Dish();
            d.setName("Dish #" + i);
            d.setDescription("Description#"+i);
            mDishes.add(d);
        }
    }

    public static DishLab get(Context context){
        if(sDishLab == null){
            sDishLab = new DishLab(context.getApplicationContext());
        }
        return sDishLab;
    }

    public ArrayList<Dish> getDishes(){
        return mDishes;
    }

    public Dish getDish(UUID id){
        for(Dish d : mDishes){
            if(d.getId().equals(id)){
                return d;
            }
        }
        return null;
    }
}
