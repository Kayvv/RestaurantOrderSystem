package nz.ac.unitec.restaurantordersystem;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

/**
 * Created by Kay on 2016/12/22.
 */
public class BaseListFragment extends Fragment {

    private RecyclerView mDishRecyclerView;
    private DishAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_dish_list,container,false);

        mDishRecyclerView = (RecyclerView)view.findViewById(R.id.dish_recycler_view);
        mDishRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_dish_list, menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_dish:
                Intent intent = new Intent(getActivity(),DishAddActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void updateUI(){
        DishLab dishLab = DishLab.get(getActivity());
        List<Dish> dishes = dishLab.getDishes();
        if (mAdapter == null) {
            mAdapter = new DishAdapter(dishes);
            mDishRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setDishes(dishes);
            mAdapter.notifyDataSetChanged();
        }
    }


    private class DishAdapter extends RecyclerView.Adapter<DishHolder> {

        private List<Dish> mDishes;

        public DishAdapter(List<Dish> dishes){
            mDishes = dishes;
        }

        @Override
        public DishHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_dish, parent, false);
            return new DishHolder(view);
        }

        @Override
        public void onBindViewHolder(DishHolder holder, int position) {
            Dish dish = mDishes.get(position);
            holder.bindDish(dish);
        }

        @Override
        public int getItemCount() {
            return mDishes.size();
        }

        public void setDishes(List<Dish> dishes) {
            mDishes = dishes;
        }

    }

    //define the ViewHolder by inner class
    private class DishHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mPhotoView;
        private File mPhotoFile;

        private Dish mDish;

        public DishHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mPhotoView = (ImageView)
                    itemView.findViewById(R.id.dish_photo);
            mTitleTextView = (TextView)
                    itemView.findViewById(R.id.list_item_dish_title_text_view);
            mDateTextView = (TextView)
                    itemView.findViewById(R.id.list_item_dish_date_text_view);
        }

        public void bindDish(Dish dish) {
            mDish = dish;
            mPhotoFile = DishLab.get(getActivity()).getPhotoFile(mDish);
            mTitleTextView.setText(mDish.getName());
            mDateTextView.setText(mDish.getDescription());
            updatePhotoView();
        }

        @Override
        public void onClick(View v) {
            Intent intent = DishPagerActivity.newIntent(getActivity(), mDish.getId());
            startActivity(intent);
        }

        private void updatePhotoView(){
            if(mPhotoFile == null|| !mPhotoFile.exists()){
                mPhotoView.setImageDrawable(null);
            }else{
                Bitmap bitmap = PictureUtils.getScaledBitmap(
                        mPhotoFile.getPath(),getActivity());
                mPhotoView.setImageBitmap(bitmap);
            }
        }
    }

}
