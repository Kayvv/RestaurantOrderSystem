package nz.ac.unitec.restaurantordersystem;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kay on 27/07/2016.
 */
public class DishListFragment extends Fragment {
    private static final String TAG = "CrimeListFragment";

    private RecyclerView mDishRecyclerView;
    private DishAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_dish_list,container,false);

        mDishRecyclerView = (RecyclerView)view.findViewById(R.id.dish_recycler_view);
        mDishRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI(){
        DishLab dishLab = DishLab.get(getActivity());
        List<Dish> dishes = dishLab.getDishes();

        mAdapter = new DishAdapter(dishes);
        mDishRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        //Get the Crime from the adapter
        Dish c = ((DishAdapter)getListAdapter()).getItem(position);

        //Start CrimeActivity
        Intent i = new Intent(getActivity(), DishActivity.class);
        i.putExtra(DishFragment.EXTRA_DISH_ID, c.getId());
        startActivity(i);
    }

    private class DishAdapter extends RecyclerView.Adapter<DishHolder> {

        private List<Dish> mDishes;

        public DishAdapter(List<Dish> dishes){
            mDishes = dishes;
        }

        @Override
        public DishHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1,parent,false);
            return new DishHolder(view);
        }

        @Override
        public void onBindViewHolder(DishHolder holder, int position) {
            Dish dish = mDishes.get(position);
            holder.mTitleTextView.setText(dish.getName());
        }

        @Override
        public int getItemCount() {
            return mDishes.size();
        }

    }

    //define the ViewHolder by inner class
    private class DishHolder extends RecyclerView.ViewHolder{
        public TextView mTitleTextView;

        public DishHolder(View itemView) {
            super(itemView);

            mTitleTextView = (TextView)itemView;
        }

    }


}
