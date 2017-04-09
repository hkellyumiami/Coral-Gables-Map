package edu.miami.c10404869.coralgablesmap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    private static final int CALL_MAP = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void onClick(View view) {

        switch (view.getId()){
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
                break;
            default:
                break;
        }
    }

    public void onActivityResult(int request, int result, Intent data) {

        super.onActivityResult(request,result,data);
        switch (request) {
            case CALL_MAP:
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

        Intent data = new Intent();
        data.putExtra("view_id",view_id);



    }
}
