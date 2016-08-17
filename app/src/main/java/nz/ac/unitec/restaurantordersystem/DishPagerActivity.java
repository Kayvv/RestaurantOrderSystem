package nz.ac.unitec.restaurantordersystem;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by Kay on 14/08/2016.
 */
public class DishPagerActivity extends AppCompatActivity {

    //--static--
    private static final String EXTRA_DISH_ID = "nz.ac.unitec.restaurantordersystem.dish_id";

    private ViewPager mViewPager;
    private List<Dish> mDishes;

    public static Intent newIntent(Context packageContext, UUID dishId) {
        Intent intent = new Intent(packageContext, DishPagerActivity.class);
        intent.putExtra(EXTRA_DISH_ID, dishId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_pager);

        UUID dishId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_DISH_ID);

        mViewPager = (ViewPager)findViewById(R.id.activity_dish_pager_view_pager);

        mDishes = DishLab.get(this).getDishes();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Dish dish = mDishes.get(position);
                return DishFragment.newInstance(dish.getId());
            }

            @Override
            public int getCount() {
                return mDishes.size();
            }
        });

        for (int i = 0; i < mDishes.size(); i++) {
            if (mDishes.get(i).getId().equals(dishId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
