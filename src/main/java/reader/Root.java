package reader;

import java.util.ArrayList;
import java.util.List;
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