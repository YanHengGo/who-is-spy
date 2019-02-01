package com.yfzm.whoissly;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.pedromassango.doubleclick.DoubleClick;
import com.pedromassango.doubleclick.DoubleClickListener;

import java.util.ArrayList;
import java.util.List;

public class Vote extends AppCompatActivity {

    private MyApp myApp;

    private Integer totalNum;
    private Integer spyNum;
    private Integer blankNum;
    private Integer commonNum;

    private boolean isBlankSingle;

    private List<Button> btnList = new ArrayList<>();
    SwitchCompat swtForget;
    TextView txtHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vote);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        myApp = (MyApp) getApplication();
        totalNum = myApp.getTotalNum();
        spyNum = myApp.getSpyNum();
//        liveNum = myApp.getTotalNum();

        blankNum = myApp.getBlankNum();
        commonNum = totalNum - spyNum - blankNum;

        isBlankSingle = myApp.isBlankSingle();

        initPlayer();

        swtForget = findViewById(R.id.swtForget);
        txtHint = findViewById(R.id.txtHint);
        swtForget.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    txtHint.setText("双击玩家查看词");
                } else {
                    txtHint.setText("双击选择要投出去的玩家");
                }
            }
        });

        findViewById(R.id.btnEndGame).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initPlayer() {
        btnList.add((Button) findViewById(R.id.btnPlayer1));
        btnList.add((Button) findViewById(R.id.btnPlayer2));
        btnList.add((Button) findViewById(R.id.btnPlayer3));
        btnList.add((Button) findViewById(R.id.btnPlayer4));
        btnList.add((Button) findViewById(R.id.btnPlayer5));
        btnList.add((Button) findViewById(R.id.btnPlayer6));
        btnList.add((Button) findViewById(R.id.btnPlayer7));
        btnList.add((Button) findViewById(R.id.btnPlayer8));
        btnList.add((Button) findViewById(R.id.btnPlayer9));
        btnList.add((Button) findViewById(R.id.btnPlayer10));
        btnList.add((Button) findViewById(R.id.btnPlayer11));
        btnList.add((Button) findViewById(R.id.btnPlayer12));

        int btnIndex;
        for (btnIndex = 0; btnIndex < totalNum; btnIndex++) {
            btnList.get(btnIndex).setAllCaps(false);
            btnList.get(btnIndex).setText(myApp.getPlayerName(btnIndex));
            final int finalBtnIndex = btnIndex;
            btnList.get(btnIndex).setOnClickListener(new DoubleClick(new DoubleClickListener() {
                @Override
                public void onSingleClick(View view) {
                    // Ignore
                }

                @Override
                public void onDoubleClick(View view) {
                    if (swtForget.isChecked()) {
                        txtHint.setText(myApp.isSpy(finalBtnIndex) ? myApp.getSpyWord()
                                : myApp.isBlank(finalBtnIndex) ? "空白"
                                : myApp.getCommonWord());
                        return;
                    }

//                    liveNum--;
                    Button btn = (Button) view;
                    btn.setClickable(false);
                    if (myApp.isSpy(finalBtnIndex)) {
                        btn.setText("卧底");
                        btn.setTextColor(Color.RED);
                        spyNum--;
                    } else if (myApp.isBlank(finalBtnIndex)) {
                        btn.setText("空白");
                        btn.setTextColor(Color.BLUE);
                        blankNum--;
                    } else {
                        btn.setText("平民");
                        commonNum--;
                    }

                    if (isBlankSingle && blankNum == 0 && spyNum >= commonNum
                            || !isBlankSingle && (blankNum + spyNum) >= commonNum) {
                        Toast.makeText(Vote.this, "卧底胜利！", Toast.LENGTH_LONG).show();
                        endGame();
                    } else if (isBlankSingle && spyNum == 0 && blankNum > 0) {
                        Toast.makeText(Vote.this, "白板胜利！", Toast.LENGTH_LONG).show();
                        endGame();
                    } else if (blankNum == 0 && spyNum == 0) {
                        Toast.makeText(Vote.this, "平民胜利！", Toast.LENGTH_LONG).show();
                        endGame();
                    }
                }
            }));
        }
        for (; btnIndex < 12; btnIndex++) {
            btnList.get(btnIndex).setVisibility(View.INVISIBLE);
        }
    }

    private void endGame() {
        for (int i = 0; i < totalNum; i++) {
            Button btn = btnList.get(i);
            if (myApp.isSpy(i)) {
                btn.setText("卧底");
                btn.setTextColor(Color.RED);
            } else if (myApp.isBlank(i)) {
                btn.setText("空白");
                btn.setTextColor(Color.BLUE);
            } else {
                btn.setText("平民");
            }
            btn.setClickable(false);
        }
        swtForget.setClickable(false);
        txtHint.setText(myApp.getSpyWord() + "(卧底) vs " + myApp.getCommonWord());
    }

}
