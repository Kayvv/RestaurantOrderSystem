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

import nz.ac.unitec.restaurantordersystem.database.OrderBaseHelper;
import nz.ac.unitec.restaurantordersystem.database.OrderCursorWrapper;
import nz.ac.unitec.restaurantordersystem.database.OrderDbSchema.OrderTable;

/**
 * Created by Kay on 21/12/2016.
 */
public class OrderLab {
    private static OrderLab sOrderLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;



    private OrderLab(Context appContext) {
        mContext = appContext.getApplicationContext();
        mDatabase = new OrderBaseHelper(mContext).getWritableDatabase();
    }

    public static OrderLab get(Context context){
        if(sOrderLab == null){
            sOrderLab = new OrderLab(context.getApplicationContext());
        }
        return sOrderLab;
    }

    public void addOrder(Order d){
        ContentValues values = getContentValues(d);
        mDatabase.insert(OrderTable.NAME,null,values);
    }

    public List<Order> getOrderes(){
        List<Order> Orderes = new ArrayList<>();
        OrderCursorWrapper cursor = queryOrderes(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Orderes.add(cursor.getOrder());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return Orderes;
    }

    public Order getOrder(UUID id){
        OrderCursorWrapper cursor = queryOrderes(
                OrderTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getOrder();
        } finally {
            cursor.close();
        }
    }

    public File getPhotoFile(Order Order) {
        File externalFilesDir = mContext
                .getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (externalFilesDir == null) {
            return null;
        }
        return new File(externalFilesDir, Order.getPhotoFilename());
    }

    public void updateOrder(Order Order) {
        String uuidString = Order.getId().toString();
        ContentValues values = getContentValues(Order);
        mDatabase.update(OrderTable.NAME, values,
                OrderTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    private static ContentValues getContentValues(Order Order){
        ContentValues values = new ContentValues();
        values.put(OrderTable.Cols.UUID,Order.getId().toString());
        values.put(OrderTable.Cols.NAME,Order.getName());
        values.put(OrderTable.Cols.DESCRIPTION,Order.getDescription());
        values.put(OrderTable.Cols.PRICE,"test price");
        values.put(OrderTable.Cols.IMAGE, "test image");

        return values;
    }


    private OrderCursorWrapper queryOrderes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                OrderTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new OrderCursorWrapper(cursor);
    }
}
