package com.example.jremotecontrol.Network;

import java.io.Serializable;

public class Package implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum Mod {
        MOVECURSOR,
        CLICKMOUSE,
        INPUTCHAR
    }

    public enum MouseBtn {
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
    }

    public Package(int mb){
        m = Mod.CLICKMOUSE;
        if(mb == 1) {
            this.mb = MouseBtn.LEFT;
        }
        else if(mb == 2) {
            this.mb = MouseBtn.RIGHT;
        }
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
