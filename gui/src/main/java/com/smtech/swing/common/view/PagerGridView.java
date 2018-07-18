package com.smtech.swing.common.view;

import com.smtech.swing.common.btns.XButton;
import com.smtech.swing.common.layout.GridLayoutEx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class PagerGridView extends ViewGroup{

    private int maxNum;
    private XButton preBtn;
    private XButton nextBtn;
    private List<Component> componentList;
    //当前要显示控件的起始索引
    private int startIndex;
    private int endIndex;

    public PagerGridView() {
        this(1,8);
    }

    public PagerGridView(int row,int col) {
        setLayout(new GridLayoutEx(row,col,10,0));
        startIndex = 0;
        endIndex = -1;
        maxNum = row*col;
        componentList = new ArrayList<Component>();
    }


    @Override
    public Component add(Component comp) {
        componentList.add(comp);
        if(componentList.size() <= maxNum){
            endIndex++;
        }
        if(componentList.size() == (maxNum + 1)){
            preBtn = new XButton();
            preBtn.setPreferredSize(new Dimension(40,0));
            preBtn.setAction(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(startIndex > 0){
                        startIndex -= (maxNum-2);

                        removeAll();
                        if(startIndex == 1){
                            startIndex = 0;
                            endIndex = startIndex + (maxNum-2);
                        }else{
                            endIndex = startIndex + (maxNum-3);
                            PagerGridView.super.add(preBtn);

                        }
                        for(int i = startIndex;i<=endIndex;i++){
                            PagerGridView.super.add(componentList.get(i));
                        }
                        PagerGridView.super.add(nextBtn);
                        updateUI();

                    }
                }
            });
            preBtn.setText("上一页");

            nextBtn = new XButton();
            nextBtn.setPreferredSize(new Dimension(40,0));
            nextBtn.setAction(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(endIndex < componentList.size() - 1){
                        if(startIndex == 0){
                            startIndex += (maxNum-1);
                        }else{
                            startIndex += (maxNum-2);
                        }

                        endIndex += (maxNum - 2);

                        removeAll();
                        PagerGridView.super.add(preBtn);

                        if(endIndex > componentList.size() - 1){
                            endIndex = componentList.size() - 1;

                            for(int i = startIndex;i<=endIndex;i++){
                                PagerGridView.super.add(componentList.get(i));
                            }

                        }else if((endIndex + 1) == (componentList.size() - 1)){
                            endIndex += 1;
                            for(int i = startIndex;i<=endIndex;i++){
                                PagerGridView.super.add(componentList.get(i));
                            }

                        } else{

                            for(int i = startIndex;i<=endIndex;i++){
                                PagerGridView.super.add(componentList.get(i));
                            }
                            PagerGridView.super.add(nextBtn);
                        }

                        updateUI();
                    }
                }
            });
            nextBtn.setText("下一页");

            super.add(nextBtn,maxNum-1);
            endIndex--;
        }
        return super.add(comp);
    }

}
