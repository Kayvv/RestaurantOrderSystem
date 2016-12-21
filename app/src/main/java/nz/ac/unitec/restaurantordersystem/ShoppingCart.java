package nz.ac.unitec.restaurantordersystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

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
        orderedDishes.add(d);
    }

    public List<Dish> getDishes(){
        return orderedDishes;
    }

    public File getPhotoFile(Dish dish) {
        File externalFilesDir = mContext
                .getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (externalFilesDir == null) {
            return null;
        }
        return new File(externalFilesDir, dish.getPhotoFilename());
    }


}
