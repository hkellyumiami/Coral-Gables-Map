package edu.miami.c10404869.coralgablesmap;
//-----------------------------------------------------------------------------
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.InputStream;

//-----------------------------------------------------------------------------
public class BuildingViewer extends Activity {
//-----------------------------------------------------------------------------
    private int view_id;
    private String image_loc;
    private String audio_loc;
    private String text_loc;
    private int next_view_id;
    private int prev_view_id;
    private int toggle_view_id;
    private int extra_id;
    private MediaPlayer mediaPlayer;
//-----------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_viewer);
        //---Retrieve the appropriate view_id from the previous activity.
        Intent intent = getIntent();
        view_id = intent.getIntExtra("view_id",0);
        //---Initialize the SQLite Database. (TO BE HADDED)
        //---Populate UI elements.
        populate();
        //---Set onSwipeTouchListener to handle swipes/clicks.
        ImageView main_image = (ImageView)findViewById(R.id.main_image);
        main_image.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeRight() {
                //---Move to previous by resetting view_id to prev_view_id;
                view_id = prev_view_id;
                //---Re-populate the database based on the new id.
                populate();
            }
            public void onSwipeLeft() {
                //---Move to next by resetting view_id to next_view_id;
                view_id = next_view_id;
                //---Re-populate the database based on the new id.
                populate();
            }
            public void onClick() {
                super.onClick();
                TextView textView = (TextView)findViewById(R.id.text_desc);
                Button extraImages = (Button)findViewById(R.id.extra_images);
                Button toggleButton = (Button)findViewById(R.id.toggle);
                LinearLayout audioControls = (LinearLayout)findViewById(R.id.audio_controls);
                audioControls.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                extraImages.setVisibility(View.GONE);
                toggleButton.setVisibility(View.GONE);
            }

        });
    }
//-----------------------------------------------------------------------------
    @Override
    public void onPause() {
        super.onPause();
        if (mediaPlayer.isPlaying()) mediaPlayer.pause();
    }
//-----------------------------------------------------------------------------
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer.isPlaying()) mediaPlayer.stop();
        if (mediaPlayer != null) mediaPlayer.release();
    }
//-----------------------------------------------------------------------------
    public void populate() {

        /*---In lieu of database functionality, uses switch statement to assign
        the proper assets to the appropriate UI elements.*/
        //---Release mediaplayer if already exists.
        if (mediaPlayer != null) mediaPlayer.release();
        mediaPlayer = new MediaPlayer();
        ImageView mainImage = (ImageView)findViewById(R.id.main_image);
        TextView textView = (TextView)findViewById(R.id.text_desc);
        textView.setMovementMethod(new ScrollingMovementMethod());
        switch(view_id) {
            case 1:
                mainImage.setImageResource(R.drawable.building01image01);
                textView.setText(R.string.building01text01);
                mediaPlayer = MediaPlayer.create(this,R.raw.building01audio01);
                next_view_id = 2;
                prev_view_id = 3;
                toggle_view_id = 4;
                extra_id = 1;
                break;
            case 2:
                mainImage.setImageResource(R.drawable.building01image02);
                textView.setText(R.string.building01text02);
                mediaPlayer = MediaPlayer.create(this,R.raw.building01audio02);
                next_view_id = 3;
                prev_view_id = 1;
                toggle_view_id = 4;
                extra_id = 1;
                break;
            case 3:
                mainImage.setImageResource(R.drawable.building01image03);
                textView.setText(R.string.building01text03);
                mediaPlayer = MediaPlayer.create(this,R.raw.building01audio03);
                next_view_id = 1;
                prev_view_id = 2;
                toggle_view_id = 4;
                extra_id = 1;
                break;
            case 4:
                mainImage.setImageResource(R.drawable.building01image04);
                textView.setText(R.string.building01text04);
                mediaPlayer = MediaPlayer.create(this,R.raw.building01audio04);
                next_view_id = 5;
                prev_view_id = 6;
                toggle_view_id = 1;
                extra_id = 10;
                break;
            case 5:
                mainImage.setImageResource(R.drawable.building01image05);
                textView.setText(R.string.building01text05);
                mediaPlayer = MediaPlayer.create(this,R.raw.building01audio05);
                next_view_id = 6;
                prev_view_id = 4;
                toggle_view_id = 1;
                extra_id = 10;
                break;
            case 6:
                mainImage.setImageResource(R.drawable.building01image06);
                textView.setText(R.string.building01text06);
                mediaPlayer = MediaPlayer.create(this,R.raw.building01audio06);
                next_view_id = 4;
                prev_view_id = 5;
                toggle_view_id = 1;
                extra_id = 10;
                break;
            default:
                Log.i("Error: ", "Failed to identify building.");
                finish();
        }

        /*---TO BE ADDED: The above switch statement will be replaced in order to
        handle database queries to assign the same information. This will perform
        a database query and populate UI elements (image, text, audio) based on
        the info retrieved from the database query.*/
    }
//-----------------------------------------------------------------------------
    public void myClickHandler (View view) {

        //---Identfiy UI elements.
        TextView textView = (TextView)findViewById(R.id.text_desc);
        Button extraImages = (Button)findViewById(R.id.extra_images);
        Button toggleButton = (Button)findViewById(R.id.toggle);
        LinearLayout audioControls = (LinearLayout)findViewById(R.id.audio_controls);

        //---Handle clicks.
        switch(view.getId()) {
            case R.id.extra_images:
                Intent data = new Intent();
                data.setClassName("edu.miami.c10404869.coralgablesmap",
                        "edu.miami.c10404869.coralgablesmap.AdditionalImages");
                data.putExtra("extra_id",extra_id);
                startActivity(data);
                break;
            case R.id.play:
                mediaPlayer.start();
                break;
            case R.id.toggle:
                view_id = toggle_view_id;
                populate();
            case R.id.pause:
                mediaPlayer.pause();
                break;
            case R.id.dismiss:
                if (mediaPlayer.isPlaying()) mediaPlayer.stop();
                audioControls.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                extraImages.setVisibility(View.VISIBLE);
                toggleButton.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }
//-----------------------------------------------------------------------------
}
