package edu.miami.c10404869.coralgablesmap;
//-----------------------------------------------------------------------------
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
//-----------------------------------------------------------------------------
public class BuildingViewer extends Activity {
//-----------------------------------------------------------------------------
    private int view_id;
    private String image_loc;
    private String audio_loc;
    private String desc_text;
    private int next_view_id;
    private int prev_view_id;
    private int toggle_view_id;
    private int extra_id;
    private double x;
    private double y;
//-----------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_viewer);
        //---Retrieve the appropriate view_id from the previous activity.
        Intent intent = getIntent();
        view_id = intent.getIntExtra("view_id",0);
        //---Initialize the SQLite Database.
        //---Populate UI elements from database.
        populate();
        //---Set onSwipeTouchListener to handle swipes/clicks.
        View screen = findViewById(R.id.screen);
        screen.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeLeft() {
                //---Move to previous by resetting view_id to prev_view_id;
                view_id = prev_view_id;
                //---Re-populate the database based on the new id.
                populate();
            }
            public void onSwipeRight() {
                //---Move to next by resetting view_id to next_view_id;
                view_id = next_view_id;
                //---Re-populate the database based on the new id.
                populate();
            }
        });
    }
//-----------------------------------------------------------------------------
    public void populate() {
        /*---Perform a database query and populate UI elements (image, text,
        audio) based on the info retrieved from the database query. Move button
        based on new x and y coordinates.*/

        //---Retrieve and assign global variable fields from database.

    }
//-----------------------------------------------------------------------------
}
