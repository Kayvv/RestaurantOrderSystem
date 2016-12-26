package nz.ac.unitec.restaurantordersystem;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
 * Created by Kay on 27/07/2016.
 */
public class DishListFragment extends Fragment {
    private static final String TAG = "DishListFragment";
    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";

    private RecyclerView mDishRecyclerView;
    private DishAdapter mAdapter;
    private boolean mSubtitleVisible;

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

        if (savedInstanceState != null) {
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mDishRecyclerView.setAdapter(mAdapter);
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_dish_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.menu_item_show_subtitle);
        if (mSubtitleVisible) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        } else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_dish:
                Intent intent = new Intent(getActivity(),DishAddActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_item_show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle() {
        DishLab dishLab = DishLab.get(getActivity());
        int dishCount = dishLab.getDishes().size();
        String subtitle = getString(R.string.subtitle_format, dishCount);
        if (!mSubtitleVisible) {
            subtitle = null;
        }
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    public void updateUI(){
        DishLab dishLab = DishLab.get(getActivity());
        List<Dish> dishes = dishLab.getDishes();
        if (mAdapter == null) {
            mAdapter = new DishAdapter(dishes);
            mDishRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setDishes(dishes);
            mAdapter.notifyDataSetChanged();
        }
        updateSubtitle();
    }



    public class DishAdapter extends RecyclerView.Adapter<DishListFragment.DishHolder> {

        private List<Dish> mDishes;

        public DishAdapter(List<Dish> dishes){
            mDishes = dishes;
        }

        @Override
        public DishListFragment.DishHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_dish, parent, false);
            return new DishListFragment.DishHolder(view);
        }

        @Override
        public void onBindViewHolder(DishListFragment.DishHolder holder, int position) {
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
