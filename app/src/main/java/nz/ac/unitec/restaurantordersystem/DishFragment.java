package nz.ac.unitec.restaurantordersystem;

import android.app.Fragment;
import android.os.Bundle;
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
    public static final String EXTRA_DISH_ID = "nz.ac.unitec.restaurantordersystem.dish_id";

    //--field--
    private TextView dishName;
    private Dish mDish;



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        UUID crimeId = (UUID)getActivity().getIntent()
                .getSerializableExtra(EXTRA_DISH_ID);
        mDish = DishLab.get(getActivity()).getDish(crimeId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_dish, container, false);
        dishName = (TextView)v.findViewById(R.id.dishName);
        return v;
    }


}
