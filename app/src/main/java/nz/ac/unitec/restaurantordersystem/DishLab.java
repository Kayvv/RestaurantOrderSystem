package nz.ac.unitec.restaurantordersystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import nz.ac.unitec.restaurantordersystem.database.DishBaseHelper;
import nz.ac.unitec.restaurantordersystem.database.DishCursorWrapper;
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
    }

    public List<Dish> getDishes(){
        List<Dish> dishes = new ArrayList<>();
        DishCursorWrapper cursor = queryDishes(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                dishes.add(cursor.getDish());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return dishes;
    }

    public Dish getDish(UUID id){
        DishCursorWrapper cursor = queryDishes(
                DishTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getDish();
        } finally {
            cursor.close();
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

    public void updateDish(Dish dish) {
        String uuidString = dish.getId().toString();
        ContentValues values = getContentValues(dish);
        mDatabase.update(DishTable.NAME, values,
                DishTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    private static ContentValues getContentValues(Dish dish){
        ContentValues values = new ContentValues();
        values.put(DishTable.Cols.UUID,dish.getId().toString());
        values.put(DishTable.Cols.NAME,dish.getName());
        values.put(DishTable.Cols.DESCRIPTION,dish.getDescription());
        values.put(DishTable.Cols.PRICE,"test price");
        values.put(DishTable.Cols.IMAGE, "test image");

        return values;
    }


    private DishCursorWrapper queryDishes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                DishTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new DishCursorWrapper(cursor);
    }
}
