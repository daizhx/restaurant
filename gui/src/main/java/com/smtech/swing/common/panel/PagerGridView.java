package com.smtech.swing.common.panel;

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
    private int startIndex = 0;
    private int endIndex = 0;


    public PagerGridView() {
        setLayout(new GridLayoutEx(1,8,10,0));
        startIndex = 0;
        endIndex = 6;
        maxNum = 8;
        componentList = new ArrayList<Component>();
    }


    @Override
    public Component add(Component comp) {
        componentList.add(comp);
        if(componentList.size() == maxNum){
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

                        }else{

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

            super.add(nextBtn,7);

        }
        return super.add(comp);
    }

    @Override
    protected void addImpl(Component comp, Object constraints, int index) {
        int c = getComponentCount();
        super.addImpl(comp, constraints, index);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Dimension size = getSize();
        System.out.println("size : " + size.width + "x" + size.height);

        Component[] childs = getComponents();


        for(Component c : childs){
            Dimension cSize = c.getSize();
            System.out.println("child size : " + cSize.width + "x" + cSize.height);
        }
        super.paintComponent(g);
    }
}
