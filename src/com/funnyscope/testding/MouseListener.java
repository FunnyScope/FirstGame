package com.funnyscope.testding;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter {

    private Handler handler;

    public MouseListener(Handler handler) {
        this.handler = handler;
    }

    //For shooting, I guess
    public void mouseClicked(MouseEvent arg0) {


    }


}
