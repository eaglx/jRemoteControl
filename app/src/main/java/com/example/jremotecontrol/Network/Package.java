package com.example.jremotecontrol.Network;

import java.io.Serializable;

public class Package implements Serializable {
    public Mod getMod() {
        return m;
    }

    public enum Mod {
        MOVECURSOR,
        CLICKMOUSE,
        INPUTCHAR
    }

    public enum MouseBtn {
        NONE,
        LEFT,
        RIGHT
    }

    private Mod m;
    private int mouseXPos;
    private int mouseYPos;
    private MouseBtn mb;
    //private char keyCh;

    public Package(int mouseXPos, int mouseYPos){
        m = Mod.MOVECURSOR;
        this.mouseXPos = mouseXPos;
        this.mouseYPos = mouseYPos;
        mb = MouseBtn.NONE;
    }

    public Package(int mb){
        m = Mod.CLICKMOUSE;
        if(mb == 1) {
            this.mb = MouseBtn.LEFT;
        }
        else {
            this.mb = MouseBtn.RIGHT;
        }
        mouseXPos = 0;
        mouseYPos = 0;
    }

//    public Package(char c){
//        m = Mod.INPUTCHAR;
//    }

    public int getMouseYPos() {
        return mouseYPos;
    }

    public int getMouseXPos() {
        return mouseXPos;
    }

    public MouseBtn getMouseBtnClick() { return mb; }
}