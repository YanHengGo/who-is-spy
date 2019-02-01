package com.yfzm.whoissly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class SetName extends AppCompatActivity {

    private MyApp myApp;
    private int totalNum;

    private List<EditText> edtPlayerNames = new ArrayList<>();
    private List<LinearLayout> llPlayerNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_name);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        myApp = (MyApp) getApplication();
        totalNum = myApp.getTotalNum();

        initialize();

        findViewById(R.id.btnSaveName).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < totalNum; i++) {
                    String playerName = edtPlayerNames.get(i).getText().toString();
                    if (!playerName.equals("")) {
                        myApp.setPlayerNames(i, playerName);
                    }
                }
                finish();
            }
        });

        findViewById(R.id.btnCancelSetName).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initialize() {
        edtPlayerNames.add((EditText) findViewById(R.id.edtPlayer1));
        edtPlayerNames.add((EditText) findViewById(R.id.edtPlayer2));
        edtPlayerNames.add((EditText) findViewById(R.id.edtPlayer3));
        edtPlayerNames.add((EditText) findViewById(R.id.edtPlayer4));
        edtPlayerNames.add((EditText) findViewById(R.id.edtPlayer5));
        edtPlayerNames.add((EditText) findViewById(R.id.edtPlayer6));
        edtPlayerNames.add((EditText) findViewById(R.id.edtPlayer7));
        edtPlayerNames.add((EditText) findViewById(R.id.edtPlayer8));
        edtPlayerNames.add((EditText) findViewById(R.id.edtPlayer9));
        edtPlayerNames.add((EditText) findViewById(R.id.edtPlayer10));
        edtPlayerNames.add((EditText) findViewById(R.id.edtPlayer11));
        edtPlayerNames.add((EditText) findViewById(R.id.edtPlayer12));

        llPlayerNames.add((LinearLayout) findViewById(R.id.llSetPlayer1));
        llPlayerNames.add((LinearLayout) findViewById(R.id.llSetPlayer2));
        llPlayerNames.add((LinearLayout) findViewById(R.id.llSetPlayer3));
        llPlayerNames.add((LinearLayout) findViewById(R.id.llSetPlayer4));
        llPlayerNames.add((LinearLayout) findViewById(R.id.llSetPlayer5));
        llPlayerNames.add((LinearLayout) findViewById(R.id.llSetPlayer6));
        llPlayerNames.add((LinearLayout) findViewById(R.id.llSetPlayer7));
        llPlayerNames.add((LinearLayout) findViewById(R.id.llSetPlayer8));
        llPlayerNames.add((LinearLayout) findViewById(R.id.llSetPlayer9));
        llPlayerNames.add((LinearLayout) findViewById(R.id.llSetPlayer10));
        llPlayerNames.add((LinearLayout) findViewById(R.id.llSetPlayer11));
        llPlayerNames.add((LinearLayout) findViewById(R.id.llSetPlayer12));

        int edIndex = 0;
        for (; edIndex < totalNum; edIndex++) {
            edtPlayerNames.get(edIndex).setText(myApp.getPlayerName(edIndex));
        }
        for (; edIndex < 12; edIndex++) {
            llPlayerNames.get(edIndex).setVisibility(View.GONE);
        }
    }
}
