package nz.ac.unitec.restaurantordersystem;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

/**
 * Created by Kay on 2016/12/21.
 */
public class ShoppingCartFragment extends BaseListFragment {

    private DishAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onCreate","shopping");
    }

    public void updateUI(){
        ShoppingCart shoppingCart = ShoppingCart.get(getActivity());
        List<Dish> dishes = shoppingCart.getDishes();
        if (mAdapter == null) {
            mAdapter = new DishAdapter(dishes);
            Log.d("test",dishes.toString());
            mDishRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setDishes(dishes);
            mAdapter.notifyDataSetChanged();
        }
    }
}
