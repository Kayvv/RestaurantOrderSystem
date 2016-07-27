package nz.ac.unitec.restaurantordersystem;

import android.app.Fragment;

/**
 * Created by Kay on 27/07/2016.
 */
public class DishListActivity extends BaseActivity{
    @Override
    public Fragment createFragment() {
        Fragment fg = new DishListFragment();
        return fg;
    }
}
