package nz.ac.unitec.restaurantordersystem.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import nz.ac.unitec.restaurantordersystem.database.OrderDbSchema.OrderTable;

/**
 * Created by Kay on 21/12/2016.
 */
public class OrderBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "dishBase.db";

    public OrderBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ OrderTable.NAME+"("+
                " _id integer primary key autoincrement, "+
                OrderTable.Cols.UUID+", "+
                OrderTable.Cols.DISHID+", "+
                OrderTable.Cols.COUNT+", "+
                OrderTable.Cols.PRICE+")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
