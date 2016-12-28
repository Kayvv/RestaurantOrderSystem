package nz.ac.unitec.restaurantordersystem.database;

/**
 * Created by Kay on 21/12/2016.
 */
public class OrderDbSchema {
    public static final class OrderTable{
        public static final String NAME = "order";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String DESCRIPTION = "description";
            public static final String PRICE = "price";
        }
    }


}
