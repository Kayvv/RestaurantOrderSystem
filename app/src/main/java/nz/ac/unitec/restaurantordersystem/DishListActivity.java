package nz.ac.unitec.restaurantordersystem;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

/**
 * Created by Kay on 27/07/2016.
 */
public class DishListActivity extends BaseActivity{

    private RadioButton mShoppingCart;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        bindUI();
    }


    @Override
    public Fragment createFragment() {
        Fragment fg = new DishListFragment();
        return fg;
    }

    private void bindUI(){
        mShoppingCart = (RadioButton) findViewById(R.id.shopping_cart);
        mShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fg = new ShoppingCartFragment();
                changeFragment(fg);
                Log.d("shoppingcart","shoppingcart");
            }
        });
    }


}
