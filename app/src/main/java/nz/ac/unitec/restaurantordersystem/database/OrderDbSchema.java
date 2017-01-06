package nz.ac.unitec.restaurantordersystem.database;

/**
 * Created by Kay on 21/12/2016.
 */
public class OrderDbSchema {
    public static final class OrderTable{
        public static final String NAME = "OrderList";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String DISHID = "dishid";
            public static final String COUNT = "count";
            public static final String PRICE = "price";
        }
    }


}
