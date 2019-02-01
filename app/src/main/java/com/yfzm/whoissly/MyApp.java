package com.yfzm.whoissly;

import android.app.Application;

import java.util.List;


/**
 * Created by 42594 on 2018/2/21.
 */

public class MyApp extends Application {

    private String commonWord;
    private String spyWord;

    private int totalNum;
    private int spyNum;
    private int blankNum;
    private List<Integer> slyIndexList;
    private List<Integer> blankIndexList;

    private String[] playerNames = {"玩家1", "玩家2", "玩家3", "玩家4",
            "玩家5", "玩家6", "玩家7", "玩家8",
            "玩家9", "玩家10", "玩家11", "玩家12", };

    public boolean isBlankSingle() {
        return isBlankSingle;
    }

    public void setBlankSingle(boolean blankSingle) {
        isBlankSingle = blankSingle;
    }

    private boolean isBlankSingle;

    @Override
    public void onCreate() {
        super.onCreate();
        setCommonWord("苹果");
        setSpyWord("菠萝");
        setBlankSingle(false);
    }

    public String getCommonWord() {
        return commonWord;
    }

    public void setCommonWord(String s) {
        commonWord = s;
    }

    public String getSpyWord() {
        return spyWord;
    }

    public void setSpyWord(String s) {
        spyWord = s;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getSpyNum() {
        return spyNum;
    }

    public void setSpyNum(int spyNum) {
        this.spyNum = spyNum;
    }

    public int getBlankNum() {
        return blankNum;
    }

    public void setBlankNum(int blankNum) {
        this.blankNum = blankNum;
    }

    public List<Integer> getSlyIndexList() {
        return slyIndexList;
    }

    public void setSlyIndexList(List<Integer> slyIndexList) {
        this.slyIndexList = slyIndexList;
    }

    public List<Integer> getBlankIndexList() {
        return blankIndexList;
    }

    public void setBlankIndexList(List<Integer> blankIndexList) {
        this.blankIndexList = blankIndexList;
    }

    public String getPlayerName(int i) {
        return playerNames[i];
    }

    public void setPlayerNames(int i, String name) {
        playerNames[i] = name;
    }

    public boolean isBlank(Integer curNum) {
        for (Integer item: blankIndexList) {
            if (item.equals(curNum)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSpy(Integer curNum) {
        for (Integer item: slyIndexList) {
            if (item.equals(curNum)) {
                return true;
            }
        }
        return false;
    }

}
