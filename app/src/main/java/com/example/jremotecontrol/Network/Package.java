package com.example.jremotecontrol.Network;

public class Package {
    private int mouseXPos;
    private int mouseYPos;
    //private char keyCh;

    public Package(int mouseXPos, int mouseYPos){
        this.mouseXPos = mouseXPos;
        this.mouseYPos = mouseYPos;
    }

    public int getMouseYPos() {
        return mouseYPos;
    }

    public int getMouseXPos() {
        return mouseXPos;
    }
}
