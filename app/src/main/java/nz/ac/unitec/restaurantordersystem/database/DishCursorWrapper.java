package nz.ac.unitec.restaurantordersystem.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import nz.ac.unitec.restaurantordersystem.Dish;
import nz.ac.unitec.restaurantordersystem.database.DishDbSchema.DishTable;

/**
 * Created by Kay on 2016/8/19.
 */
public class DishCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public DishCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Dish getDish() {
        String uuidString = getString(getColumnIndex(DishTable.Cols.UUID));
        String name = getString(getColumnIndex(DishTable.Cols.NAME));
        String description = getString(getColumnIndex(DishTable.Cols.DESCRIPTION));
        Float price = getFloat(getColumnIndex(DishTable.Cols.PRICE));
        String image = getString(getColumnIndex(DishTable.Cols.IMAGE));

        Dish dish = new Dish(UUID.fromString(uuidString));
        dish.setName(name);
        dish.setDescription(description);
        dish.setPrice(price);
        dish.setImage(image);
        return dish;
    }
}
