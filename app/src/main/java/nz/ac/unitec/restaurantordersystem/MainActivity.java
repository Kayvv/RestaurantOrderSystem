package nz.ac.unitec.restaurantordersystem;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

/**
 * Created by Kay on 27/07/2016.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private RadioButton mDishList;
    private RadioButton mShoppingCart;
    private RadioButton mOrderList;
    private RadioButton mUserInfo;
    private Fragment mDishListFragment = new DishListFragment();
    private Fragment mShoppingCartFragment = new ShoppingCartFragment();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        setFragment(mDishListFragment);
        bindUI();
    }

    private void bindUI(){
        mDishList = (RadioButton) findViewById(R.id.dish_list);
        mShoppingCart = (RadioButton) findViewById(R.id.shopping_cart);
        mOrderList = (RadioButton) findViewById(R.id.order_list);
        mUserInfo = (RadioButton) findViewById(R.id.user_info);
        mDishList.setOnClickListener(this);
        mShoppingCart.setOnClickListener(this);
        mOrderList.setOnClickListener(this);
        mUserInfo.setOnClickListener(this);
    }


    public void setFragment(Fragment fg){
        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fg)
                    .commit();
        }else{
            fm.beginTransaction()
                    .replace(R.id.fragmentContainer, fg)
                    .commit();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dish_list:
                setFragment(mDishListFragment);
                Log.d("shoppingcart","shoppingcart");
                break;
            case R.id.shopping_cart:
                setFragment(mShoppingCartFragment);
                Log.d("shoppingcart","shoppingcart");
                break;
            case R.id.order_list:
                setFragment(mShoppingCartFragment);
                Log.d("shoppingcart","shoppingcart");
                break;
            case R.id.user_info:
                setFragment(mShoppingCartFragment);
                Log.d("shoppingcart","shoppingcart");
                break;
            default:
                break;
        }
    }
}
