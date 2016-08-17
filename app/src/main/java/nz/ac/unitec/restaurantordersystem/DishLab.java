package nz.ac.unitec.restaurantordersystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import nz.ac.unitec.restaurantordersystem.database.DishBaseHelper;
import nz.ac.unitec.restaurantordersystem.database.DishDbSchema.DishTable;

/**
 * Created by Kay on 27/07/2016.
 */
public class DishLab {
    private static DishLab sDishLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;



    private DishLab(Context appContext) {
        mContext = appContext.getApplicationContext();
        mDatabase = new DishBaseHelper(mContext).getWritableDatabase();
    }

    public static DishLab get(Context context){
        if(sDishLab == null){
            sDishLab = new DishLab(context.getApplicationContext());
        }
        return sDishLab;
    }

    public void addDish(Dish d){
        ContentValues values = getContentValues(d);
        mDatabase.insert(DishTable.NAME,null,values);
    };

    public List<Dish> getDishes(){
        return new ArrayList<>();
    }

    public Dish getDish(UUID id){
        return null;
    }

    private static ContentValues getContentValues(Dish dish){
        ContentValues values = new ContentValues();
        values.put(DishTable.Cols.UUID,dish.getId().toString());
        values.put(DishTable.Cols.NAME,dish.getName());
        values.put(DishTable.Cols.DESCRIPTION,dish.getDescription());
        values.put(DishTable.Cols.PRICE,"test price");
        values.put(DishTable.Cols.IMAGE,"test image");

        return values;
    }
}
