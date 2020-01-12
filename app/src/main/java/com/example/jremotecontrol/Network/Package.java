package com.example.jremotecontrol.Network;

public class Package {
    enum Mod {
        MOVECURSOR,
        CLICKMOUSE,
        INPUTCHAR
    }

    enum MousBtn {
        LEFT,
        RIGHT
    }

    private Mod m;
    private int mouseXPos;
    private int mouseYPos;
    private MousBtn mb;
    //private char keyCh;

    public Package(int mouseXPos, int mouseYPos){
        m = Mod.MOVECURSOR;
        this.mouseXPos = mouseXPos;
        this.mouseYPos = mouseYPos;
    }

    public Package(int mb){
        m = Mod.CLICKMOUSE;
        if(mb == 1) {
            this.mb = MousBtn.LEFT;
        }
        else if(mb == 2) {
            this.mb = MousBtn.RIGHT;
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
}
