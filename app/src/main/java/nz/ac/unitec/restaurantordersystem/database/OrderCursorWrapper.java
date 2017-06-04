package nz.ac.unitec.restaurantordersystem.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.util.Log;

import java.util.UUID;

import nz.ac.unitec.restaurantordersystem.Order;
import nz.ac.unitec.restaurantordersystem.database.OrderDbSchema.OrderTable;

/**
 * Created by Kay on 21/12/2016.
 */
public class OrderCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public OrderCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Order getOrder() {
        String uuidString = getString(getColumnIndex(OrderTable.Cols.UUID));
        String dishId = getString(getColumnIndex(OrderTable.Cols.DISHID));
        String dishCount = getString(getColumnIndex(OrderTable.Cols.COUNT));
        Float price = getFloat(getColumnIndex(OrderTable.Cols.PRICE));

        Order order = new Order(UUID.fromString(uuidString));
        order.setOrderedDish(dishId);
        order.setDishCount(dishCount);
        order.setPrice(price);
        return order;
    }
}
