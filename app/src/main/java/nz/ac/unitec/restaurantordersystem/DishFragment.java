package nz.ac.unitec.restaurantordersystem;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Kay on 27/07/2016.
 */
public class DishFragment extends Fragment {
    //--field--
    private TextView dishName;
    private Dish mDish;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        dishName = (TextView)getActivity().findViewById(R.id.dishName);
        mDish = new Dish();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_dish, container, false);
        return v;
    }


}
