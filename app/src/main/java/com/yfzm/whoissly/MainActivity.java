package com.yfzm.whoissly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.xw.repo.BubbleSeekBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        BubbleSeekBar.OnProgressChangedListener, CheckBox.OnCheckedChangeListener {

    private MyApp myApp;
    private BubbleSeekBar bsbTotal;
    private BubbleSeekBar bsbSpy;
    private BubbleSeekBar bsbBlank;

    private CheckBox cbBlank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        myApp = (MyApp) getApplication();

        findViewById(R.id.btnStartGame).setOnClickListener(this);
        bsbTotal =  findViewById(R.id.bsbTotal);
        bsbSpy = findViewById(R.id.bsbSpy);
        bsbBlank = findViewById(R.id.bsbBlank);
        cbBlank = findViewById(R.id.cbBlankSingle);

//        bsbTotal.setOnClickListener(this);
//        bsbSpy.setOnClickListener(this);
//        bsbBlank.setOnClickListener(this);

        bsbTotal.setOnProgressChangedListener(this);
        bsbSpy.setOnProgressChangedListener(this);
        bsbBlank.setOnProgressChangedListener(this);

        cbBlank.setOnCheckedChangeListener(this);

        findViewById(R.id.btnSetNickName).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int totalNum = bsbTotal.getProgress();
        int spyNum = bsbSpy.getProgress();
        int blankNum = bsbBlank.getProgress();
        switch (v.getId()) {
            case R.id.btnStartGame:
                myApp.setTotalNum(totalNum);
                myApp.setSpyNum(spyNum);
                myApp.setBlankNum(blankNum);
                myApp.setBlankSingle(cbBlank.isChecked() && blankNum != 0);
                if (!myApp.isBlankSingle() && spyNum * 2 + blankNum * 2 >= totalNum
                        || myApp.isBlankSingle() && (spyNum >= totalNum - spyNum - blankNum || spyNum + blankNum >= totalNum)
                        || spyNum + blankNum == 0) {
                    Toast.makeText(MainActivity.this, "选择人数不合规则！", Toast.LENGTH_LONG).show();
                    return;
                }

                chooseWords();
                chooseSlyAndBlank(totalNum, spyNum, blankNum);
                startActivity(new Intent(MainActivity.this, ViewRole.class));
                break;

            case R.id.btnSetNickName:
                myApp.setTotalNum(bsbTotal.getProgress());
                myApp.setSpyNum(bsbSpy.getProgress());
                myApp.setBlankNum(bsbBlank.getProgress());
                startActivity(new Intent(MainActivity.this, SetName.class));
//                Toast.makeText(MainActivity.this, "该功能尚未开发", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void chooseSlyAndBlank(int total, int spyNum, int blankNum) {
        List<Integer> tempIndexList = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            tempIndexList.add(i);
        }
        Collections.shuffle(tempIndexList);
        Collections.shuffle(tempIndexList);

        List<Integer> slyIndexList = new ArrayList<>();
        List<Integer> blankIndexList = new ArrayList<>();

        int playerIndex = 0;
        for (; playerIndex < spyNum; playerIndex++) {
            slyIndexList.add(tempIndexList.get(playerIndex));
        }
        for (; playerIndex < spyNum + blankNum; playerIndex++) {
            blankIndexList.add(tempIndexList.get(playerIndex));
        }

        myApp.setSlyIndexList(slyIndexList);
        myApp.setBlankIndexList(blankIndexList);
    }

    private void chooseWords() {
        List<String> chosenWords = SpyWord.getWords();
        myApp.setCommonWord(chosenWords.get(0));
        myApp.setSpyWord(chosenWords.get(1));
    }

    @Override
    public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {
        switch (bubbleSeekBar.getId()) {
            case R.id.bsbTotal:
                myApp.setTotalNum(progress);
                break;
            case R.id.bsbSpy:
                myApp.setSpyNum(progress);
                break;
            case R.id.bsbBlank:
                myApp.setBlankNum(progress);
                break;
        }
    }

    @Override
    public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

    }

    @Override
    public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cbBlankSingle:
                myApp.setBlankSingle(isChecked && bsbBlank.getProgress() != 0);
                break;
        }
    }
}
