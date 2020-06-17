package iSBot;

import java.util.ArrayList;

public class Root
{
    private ArrayList<mech> mechs;

    public void setMechs(ArrayList<mech> mechs){
        this.mechs = mechs;
    }
    public ArrayList<mech> getMechs(){
        return this.mechs;
    }
}