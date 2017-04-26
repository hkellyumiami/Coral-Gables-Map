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
    /*private String image_loc;
    private String audio_loc;
    private String text_loc;*/
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
                //---Identify the UI elements.
                TextView textView = (TextView)findViewById(R.id.text_desc);
                Button extraImages = (Button)findViewById(R.id.extra_images);
                Button toggleButton = (Button)findViewById(R.id.toggle);
                LinearLayout audioControls = (LinearLayout)findViewById(R.id.audio_controls);
                //---Change UI element visibility.
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
        //---Pause audio if playing.
        if (mediaPlayer.isPlaying()) mediaPlayer.pause();
    }
//-----------------------------------------------------------------------------
    @Override
    public void onDestroy() {
        super.onDestroy();
        //---Stop audio and release player.
        if (mediaPlayer.isPlaying()) mediaPlayer.stop();
        if (mediaPlayer != null) mediaPlayer.release();
    }
//-----------------------------------------------------------------------------
    public void populate() {

        //---Release mediaplayer if already exists.
        if (mediaPlayer != null) mediaPlayer.release();
        mediaPlayer = new MediaPlayer();

        //---Identify UI elements to manipulate.
        ImageView mainImage = (ImageView)findViewById(R.id.main_image);
        TextView textView = (TextView)findViewById(R.id.text_desc);
        Button extraImages = (Button)findViewById(R.id.extra_images);
        Button toggleButton = (Button)findViewById(R.id.toggle);
        textView.setMovementMethod(new ScrollingMovementMethod());

        //---Switch statement identified by the building's view_id.
        switch(view_id) {
            case 1:
                //---Set the background.
                mainImage.setImageResource(R.drawable.building01image01);

                //---Set the text description.
                textView.setText(R.string.building01text01);

                //---Set the buttons.
                extraImages.setBackgroundResource(R.drawable.building01externalbutton);
                toggleButton.setBackgroundResource(R.drawable.building01internaltoggle);

                //---Set the media player.
                mediaPlayer = MediaPlayer.create(this,R.raw.building01audio01);

                //---Set the variables for swipng, toggling, and extra ids.
                next_view_id = 2;
                prev_view_id = 3;
                toggle_view_id = 4;
                extra_id = 1;
                break;
            case 2:
                mainImage.setImageResource(R.drawable.building01image02);
                textView.setText(R.string.building01text02);
                mediaPlayer = MediaPlayer.create(this,R.raw.building01audio02);
                extraImages.setBackgroundResource(R.drawable.building01externalbutton);
                toggleButton.setBackgroundResource(R.drawable.building01internaltoggle);
                next_view_id = 3;
                prev_view_id = 1;
                toggle_view_id = 4;
                extra_id = 1;
                break;
            case 3:
                mainImage.setImageResource(R.drawable.building01image03);
                textView.setText(R.string.building01text03);
                mediaPlayer = MediaPlayer.create(this,R.raw.building01audio03);
                extraImages.setBackgroundResource(R.drawable.building01externalbutton);
                toggleButton.setBackgroundResource(R.drawable.building01internaltoggle);
                next_view_id = 1;
                prev_view_id = 2;
                toggle_view_id = 4;
                extra_id = 1;
                break;
            case 4:
                mainImage.setImageResource(R.drawable.building01image04);
                textView.setText(R.string.building01text04);
                mediaPlayer = MediaPlayer.create(this,R.raw.building01audio04);
                extraImages.setBackgroundResource(R.drawable.building01internalbutton);
                toggleButton.setBackgroundResource(R.drawable.building01externaltoggle);
                next_view_id = 5;
                prev_view_id = 6;
                toggle_view_id = 1;
                extra_id = 10;
                break;
            case 5:
                mainImage.setImageResource(R.drawable.building01image05);
                textView.setText(R.string.building01text05);
                mediaPlayer = MediaPlayer.create(this,R.raw.building01audio05);
                extraImages.setBackgroundResource(R.drawable.building01internalbutton);
                toggleButton.setBackgroundResource(R.drawable.building01externaltoggle);
                next_view_id = 6;
                prev_view_id = 4;
                toggle_view_id = 1;
                extra_id = 10;
                break;
            case 6:
                mainImage.setImageResource(R.drawable.building01image06);
                textView.setText(R.string.building01text06);
                mediaPlayer = MediaPlayer.create(this,R.raw.building01audio06);
                extraImages.setBackgroundResource(R.drawable.building01internalbutton);
                toggleButton.setBackgroundResource(R.drawable.building01externaltoggle);
                next_view_id = 4;
                prev_view_id = 5;
                toggle_view_id = 1;
                extra_id = 10;
                break;
            default:
                Log.i("Error: ", "Failed to identify building.");
                finish();
        }

        /*---The above switch statement should be replaced with an SQLite database
        in order to handle database queries to assign the same information. Should
        have fields for all of the global variables except mediaPlayer.

        Queries will identify each field (strings should be the drawable location)
        and then appropriately assign the variables. Instead of a switch statement,
        the populate() method should query the database, assign the variables, and
        update the views (buttons, textview, etc.) based on those variables.*/
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
                //---Start the next activity based on the extra_id.
                Intent data = new Intent();
                data.setClassName("edu.miami.c10404869.coralgablesmap",
                        "edu.miami.c10404869.coralgablesmap.AdditionalImages");
                data.putExtra("extra_id",extra_id);
                startActivity(data);
                break;
            case R.id.play:
                //---Play the media player.
                mediaPlayer.start();
                break;
            case R.id.pause:
                //---Pause the audio.
                mediaPlayer.pause();
                break;
            case R.id.dismiss:
                //---Stop the audio and change UI element visibility.
                if (mediaPlayer.isPlaying()) mediaPlayer.stop();
                audioControls.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                extraImages.setVisibility(View.VISIBLE);
                toggleButton.setVisibility(View.VISIBLE);
                break;
            case R.id.toggle:
                //---Populate based on the toggle_view_id.
                view_id = toggle_view_id;
                populate();
            default:
                break;
        }
    }
//-----------------------------------------------------------------------------
}
