package nz.ac.unitec.restaurantordersystem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by Kay on 27/07/2016.
 */
public class DishFragment extends Fragment {

    //--static--
    public static final String ARG_DISH_ID = "dish_id";

    //--field--
    private TextView mDishName;
    private TextView mDescription;
    private Dish mDish;


    public static DishFragment newInstance(UUID dishId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DISH_ID, dishId);
        DishFragment fragment = new DishFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        UUID dishId = (UUID)getArguments().getSerializable(ARG_DISH_ID);
        mDish = DishLab.get(getActivity()).getDish(dishId);

    }

    @Override
    public void onPause() {
        super.onPause();
        DishLab.get(getActivity())
                .updateDish(mDish);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_dish, container, false);
        mDishName = (TextView)v.findViewById(R.id.dishName);
        mDishName.setText(mDish.getName());
        mDescription = (TextView)v.findViewById(R.id.dishDescription);
        mDescription.setText(mDish.getDescription());
        return v;
    }


}
