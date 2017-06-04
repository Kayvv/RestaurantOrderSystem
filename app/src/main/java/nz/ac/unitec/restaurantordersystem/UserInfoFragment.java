package nz.ac.unitec.restaurantordersystem;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Kay on 2017/1/8.
 */

public class UserInfoFragment extends Fragment {

    private User mUser = new User();
    private TextView mUserName;
    private TextView mUserPhone;
    private TextView mUserAddress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_user_info,container,false);
        mUserName = (TextView)view.findViewById(R.id.user_name);
        mUserPhone = (TextView)view.findViewById(R.id.user_phone);
        mUserAddress = (TextView)view.findViewById(R.id.user_address);
        mUserName.setText(mUser.getName());
        mUserPhone.setText(String.valueOf(mUser.getmPhoneNumber()));
        mUserAddress.setText(mUser.getmAddress());


        return view;
    }
}
