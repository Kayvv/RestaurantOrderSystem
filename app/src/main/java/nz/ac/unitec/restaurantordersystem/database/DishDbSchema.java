package nz.ac.unitec.restaurantordersystem.database;

/**
 * Created by Kay on 14/08/2016.
 */
public class DishDbSchema {
    public static final class DishTable{
        public static final String NAME = "dish";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String DESCRIPTION = "description";
            public static final String PRICE = "price";
            public static final String IMAGE = "image";
        }
    }


}
