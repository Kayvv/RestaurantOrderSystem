package nz.ac.unitec.restaurantordersystem.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import nz.ac.unitec.restaurantordersystem.database.DishDbSchema.DishTable;

/**
 * Created by Kay on 16/08/2016.
 */
public class DishBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "dishBase.db";

    public DishBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ DishTable.NAME+"("+
                " _id integer primary key autoincrement, "+
                DishTable.Cols.UUID+", "+
                DishTable.Cols.NAME+", "+
                DishTable.Cols.DESCRIPTION+", "+
                DishTable.Cols.PRICE+", "+
                DishTable.Cols.IMAGE+")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
