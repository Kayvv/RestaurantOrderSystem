package nz.ac.unitec.restaurantordersystem.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import nz.ac.unitec.restaurantordersystem.database.DishDbSchema.DishTable;
import nz.ac.unitec.restaurantordersystem.database.OrderDbSchema.OrderTable;

/**
 * Created by Kay on 16/08/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "DishBase.db";
    private static final String CREATE_DISH = "create table "+ DishTable.NAME+"("+
            " _id integer primary key autoincrement, "+
            DishTable.Cols.UUID+", "+
            DishTable.Cols.NAME+", "+
            DishTable.Cols.DESCRIPTION+", "+
            DishTable.Cols.PRICE+", "+
            DishTable.Cols.IMAGE+")";

    private static final String CREATE_ORDER="create table "+OrderTable.NAME+"("+
            " _id integer primary key autoincrement, "+
            OrderTable.Cols.UUID+", "+
            OrderTable.Cols.DISHID+", "+
            OrderTable.Cols.COUNT+", "+
            OrderTable.Cols.PRICE+")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DISH);
        db.execSQL(CREATE_ORDER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
