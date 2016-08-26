package nz.ac.unitec.restaurantordersystem;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

/**
 * Created by Kay on 2016/8/21.
 */
public class DishAddActivity extends Activity {

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
    private Button mSaveDish;
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_add);
        mDish = new Dish();

        mPhotoFile = DishLab.get(this).getPhotoFile(mDish);

        mDishName = (EditText)findViewById(R.id.dish_title);
        mDishName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s!=null){
                    mDish.setName(s.toString());
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSaveDish = (Button)findViewById(R.id.dishSave);
        mSaveDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DishLab.get(mContext).addDish(mDish);
                finish();
            }
        });


        PackageManager packageManager = getPackageManager();

        mPhotoButton = (ImageButton) findViewById(R.id.dish_camera);
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
                startActivityForResult(captureImage, REQUEST_PHOTO);
            }
        });


        mPhotoView = (ImageView) findViewById(R.id.dish_photo);
        updatePhotoView();
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
                    mPhotoFile.getPath(),this);
            mPhotoView.setImageBitmap(bitmap);
        }
    }

}
