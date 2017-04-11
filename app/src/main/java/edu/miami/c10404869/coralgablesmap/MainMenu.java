package edu.miami.c10404869.coralgablesmap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    private static final int CALL_MAP = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Starts activity and inflates layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void onClickListener(View view) {
        //Handles clicks on menu buttons
        switch (view.getId()){
            //Calls building viewer method with corresponding building id
            case R.id.building01:
                callBuildingViewer(1);
                break;
            case R.id.building02:
                break;
            case R.id.building03:
                break;
            case R.id.building04:
                break;
            case R.id.building05:
                break;
            case R.id.building06:
                break;
            case R.id.building07:
                break;
            case R.id.building08:
                break;
            case R.id.map_button:
                //Starts Map Activity for result
                Intent data = new Intent();
                data.setClassName("edu.miami.c10404869.coralgablesmap",
                        "edu.miami.c10404869.coralgablesmap.MapsActivity");
                startActivityForResult(data,CALL_MAP);
                break;
            default:
                break;
        }
    }

    public void onActivityResult(int request, int result, Intent data) {
        //Handles results from activities
        super.onActivityResult(request,result,data);
        switch (request) {
            case CALL_MAP:
                //If building was selected, gets its building id from intent returned by Map Activity
                if (result == RESULT_OK) {
                    int view_id = data.getIntExtra("view_id", 0);
                    callBuildingViewer(view_id);
                }
                break;
            default:
                break;
        }
    }

    public void callBuildingViewer(int view_id) {
        //Puts building id into intent and starts Building Viewer Activity
        Intent data = new Intent();
        data.setClassName("edu.miami.c10404869.coralgablesmap",
                        "edu.miami.c10404869.coralgablesmap.BuildingViewer");
        data.putExtra("view_id",view_id);
        startActivity(data);
    }
}
