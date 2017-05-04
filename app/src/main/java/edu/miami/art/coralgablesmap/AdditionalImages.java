package edu.miami.art.coralgablesmap;
//-----------------------------------------------------------------------------
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

//-----------------------------------------------------------------------------
public class AdditionalImages extends Activity {
//-----------------------------------------------------------------------------
    private int extra_id;
    private int next_extra_id;
    private int prev_extra_id;
//-----------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Starts activity and inflates layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_images);
        //Determines which image to open
        Intent intent = getIntent();
        extra_id = intent.getIntExtra("extra_id", 0);
        //Populates Layout
        populate();
        //Initializes swipe functionality
        ImageView main_image = (ImageView)findViewById(R.id.main_image);
        main_image.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeRight() {
                extra_id = prev_extra_id;
                populate();
            }
            public void onSwipeLeft() {
                extra_id = next_extra_id;
                populate();
            }
        });
    }
//-----------------------------------------------------------------------------
    public void populate() {
        //Populates the main image and indicates the next and previous images
        ImageView mainImage = (ImageView)findViewById(R.id.main_image);
        TextView imageText = (TextView)findViewById(R.id.image_title);
        switch (extra_id) {
            case 1:
                mainImage.setImageResource(R.drawable.building01exterior01);
                imageText.setText(R.string.building01exterior01);
                next_extra_id = 2;
                prev_extra_id = 9;
                break;
            case 2:
                mainImage.setImageResource(R.drawable.building01exterior02);
                imageText.setText(R.string.building01exterior02);
                next_extra_id = 3;
                prev_extra_id = 1;
                break;
            case 3:
                mainImage.setImageResource(R.drawable.building01exterior03);
                imageText.setText(R.string.building01exterior03);
                next_extra_id = 4;
                prev_extra_id = 2;
                break;
            case 4:
                mainImage.setImageResource(R.drawable.building01exterior04);
                imageText.setText(R.string.building01exterior04);
                next_extra_id = 5;
                prev_extra_id = 3;
                break;
            case 5:
                mainImage.setImageResource(R.drawable.building01exterior05);
                imageText.setText(R.string.building01exterior05);
                next_extra_id = 6;
                prev_extra_id = 4;
                break;
            case 6:
                mainImage.setImageResource(R.drawable.building01exterior06);
                imageText.setText(R.string.building01exterior06);
                next_extra_id = 7;
                prev_extra_id = 5;
                break;
            case 7:
                mainImage.setImageResource(R.drawable.building01exterior07);
                imageText.setText(R.string.building01exterior07);
                next_extra_id = 8;
                prev_extra_id = 6;
                break;
            case 8:
                mainImage.setImageResource(R.drawable.building01exterior08);
                imageText.setText(R.string.building01exterior08);
                next_extra_id = 9;
                prev_extra_id = 7;
                break;
            case 9:
                mainImage.setImageResource(R.drawable.building01exterior09);
                imageText.setText(R.string.building01exterior09);
                next_extra_id = 1;
                prev_extra_id = 8;
                break;
            case 10:
                mainImage.setImageResource(R.drawable.building01interior01);
                imageText.setText(R.string.building01interior01);
                next_extra_id = 11;
                prev_extra_id = 14;
                break;
            case 11:
                mainImage.setImageResource(R.drawable.building01interior02);
                imageText.setText(R.string.building01interior02);
                next_extra_id = 12;
                prev_extra_id = 10;
                break;
            case 12:
                mainImage.setImageResource(R.drawable.building01interior03);
                imageText.setText(R.string.building01interior03);
                next_extra_id = 13;
                prev_extra_id = 11;
                break;
            case 13:
                mainImage.setImageResource(R.drawable.building01interior04);
                imageText.setText(R.string.building01interior04);
                next_extra_id = 14;
                prev_extra_id = 12;
                break;
            case 14:
                mainImage.setImageResource(R.drawable.building01interior05);
                imageText.setText(R.string.building01interior05);
                next_extra_id = 10;
                prev_extra_id = 13;
                break;
            default:
                break;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration config) {
        //Deals with change between horizontal and vertical configurations
        super.onConfigurationChanged(config);
        populate();
    }
}
