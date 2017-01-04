package nz.ac.unitec.restaurantordersystem;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.UUID;


/**
 * Created by Kay on 2016/12/31.
 */

public class OrderListFragment extends Fragment{

    private RecyclerView mDishRecyclerView;
    private OrderAdapter mAdapter;

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
        mDishRecyclerView.setAdapter(mAdapter);
        updateUI();
    }





    public void updateUI(){
        OrderLab orderLab = OrderLab.get(getActivity());
        List<Order> orders = orderLab.getOrders();
        if (mAdapter == null) {
            mAdapter = new OrderAdapter(orders);
            mDishRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setOrders(orders);
            mAdapter.notifyDataSetChanged();
        }
    }



    public class OrderAdapter extends RecyclerView.Adapter<OrderHolder> {

        private List<Order> mOrders;

        public OrderAdapter(List<Order> orders){
            mOrders = orders;
        }

        @Override
        public OrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_order, parent, false);
            return new OrderHolder(view);
        }

        @Override
        public void onBindViewHolder(OrderHolder holder, int position) {
            Order order = mOrders.get(position);
            holder.bindOrder(order);
        }

        @Override
        public int getItemCount() {
            return mOrders.size();
        }

        public void setOrders(List<Order> orders) {
            mOrders = orders;
        }

    }

    //define the ViewHolder by inner class
    private class OrderHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mOrderedDishTextView;
        private TextView mDateTextView;
        private Order mOrder;
        private Dish mDish;

        public OrderHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mOrderedDishTextView = (TextView)
                    itemView.findViewById(R.id.ordered_dishes);
        }

        public void bindOrder(Order order) {
            mOrder = order;
            String orderedDishes = "";
            List<java.util.UUID> dishIds=mOrder.getOrderedDish();
            List<Integer> dishCount = mOrder.getDishCount();
            for (int i = 0; i < dishIds.size(); i++){
                mDish = DishLab.get(getActivity()).getDish(dishIds.get(i));
                Integer count = dishCount.get(i);
                orderedDishes += mDish.getName() + " X ";
                orderedDishes += count.toString() + "\n";
            }
            mOrderedDishTextView.setText(orderedDishes);
        }

        @Override
        public void onClick(View v) {
            Intent intent = DishPagerActivity.newIntent(getActivity(), mDish.getId());
            startActivity(intent);
        }

    }

}
