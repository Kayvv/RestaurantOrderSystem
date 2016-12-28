package nz.ac.unitec.restaurantordersystem.database;

import android.database.Cursor;
import android.database.CursorWrapper;

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
        String name = getString(getColumnIndex(OrderTable.Cols.NAME));
        String description = getString(getColumnIndex(OrderTable.Cols.DESCRIPTION));
        Float price = getFloat(getColumnIndex(OrderTable.Cols.PRICE));

        Order Order = new Order(UUID.fromString(uuidString));
        Order.setName(name);
        Order.setDescription(description);
        Order.setPrice(price);
        return Order;
    }
}
