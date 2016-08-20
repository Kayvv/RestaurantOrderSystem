package nz.ac.unitec.restaurantordersystem;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.UUID;

/**
 * Created by Kay on 27/07/2016.
 */
public class DishFragment extends Fragment {

    //--static--
    public static final String ARG_DISH_ID = "dish_id";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_CONTACT = 1;
    private static final int REQUEST_PHOTO= 2;

    //--field--
    private File mPhotoFile;
    private TextView mDishName;
    private TextView mDescription;
    private Dish mDish;
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;


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
        mPhotoFile = DishLab.get(getActivity()).getPhotoFile(mDish);

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

        PackageManager packageManager = getActivity().getPackageManager();

        mPhotoButton = (ImageButton) v.findViewById(R.id.dish_camera);
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        boolean canTakePhoto = mPhotoFile != null &&
                captureImage.resolveActivity(packageManager)!= null;
        mPhotoButton.setEnabled(canTakePhoto);

        if(canTakePhoto){
            Uri uri = Uri.fromFile(mPhotoFile);
            captureImage.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        }

        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(captureImage,REQUEST_PHOTO);
            }
        });


        mPhotoView = (ImageView) v.findViewById(R.id.dish_photo);
        updatePhotoView();

        return v;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {

        } else if (requestCode == REQUEST_CONTACT && data != null) {

        } else if (requestCode == REQUEST_PHOTO) {
            updatePhotoView();
        }
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
