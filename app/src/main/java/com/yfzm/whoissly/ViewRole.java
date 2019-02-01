package com.yfzm.whoissly;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.pedromassango.doubleclick.DoubleClick;
import com.pedromassango.doubleclick.DoubleClickListener;

import java.util.List;

public class ViewRole extends AppCompatActivity {

    private MyApp myApp;

    private TextView txtViewRole;
    private int totalNum;
    private int curNum;
    private String commonWord;
    private String slyWord;
    private List<Integer> slyIndexList;
    private List<Integer> blankIndexList;

    private boolean isOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_role);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        myApp = (MyApp) getApplication();

        totalNum = myApp.getTotalNum();
        curNum = 0;
        commonWord = myApp.getCommonWord();
        slyWord = myApp.getSpyWord();
        slyIndexList = myApp.getSlyIndexList();
        blankIndexList = myApp.getBlankIndexList();

        isOpen = false;

        txtViewRole = findViewById(R.id.txtViewRole);

        txtViewRole.setText(myApp.getPlayerName(curNum));
        txtViewRole.setTextColor(Color.GRAY);

        findViewById(R.id.clViewRole).setOnClickListener(new DoubleClick(new DoubleClickListener() {
            @Override
            public void onSingleClick(View view) {
                // Do nothing
            }

            @Override
            public void onDoubleClick(View view) {
                changeText();
            }
        }));

    }

    private void changeText() {
        if (isOpen) {
            curNum++;
            if (curNum < totalNum) {
                txtViewRole.setText(myApp.getPlayerName(curNum));
                txtViewRole.setTextColor(Color.GRAY);
            } else {
                startActivity(new Intent(ViewRole.this, Vote.class));
                finish();
            }
            isOpen = !isOpen;
        } else {
            txtViewRole.setText(myApp.isSpy(curNum) ? slyWord : myApp.isBlank(curNum) ? "空白" : commonWord);
            txtViewRole.setTextColor(Color.DKGRAY);
            isOpen = !isOpen;
        }
    }


}
