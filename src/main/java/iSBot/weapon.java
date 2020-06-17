package iSBot;
public class weapon
{
    private String name;

    private String type;

    private String damageString;

    private String element;

    private int rank;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setDamageString(String damageString){
        this.damageString = damageString;
    }
    public String getDamageString(){
        return this.damageString;
    }
    public void setElement(String element){
        this.element = element;
    }
    public String getElement(){
        return this.element;
    }
    public void setRank(int rank){
        this.rank = rank;
    }
    public int getRank(){
        return this.rank;
    }
}