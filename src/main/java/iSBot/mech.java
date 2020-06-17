package iSBot;

import java.util.List;

public class mech
{

    private String rank;

    private String name;

    private String chassisFeature;

    private String hp;

    private String weight;

    private String size;

    private String velocity;

    private String imgUrl;

    private List<weapon> weapons;

    private List<uniqueAbility> uniqueAbility;

    public void setRank(String rank){
        this.rank = rank;
    }
    public String getRank(){
        return this.rank;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setChassisFeature(String chassisFeature){
        this.chassisFeature = chassisFeature;
    }
    public String getChassisFeature(){
        return this.chassisFeature;
    }
    public void setHp(String hp){
        this.hp = hp;
    }
    public String getHp(){
        return this.hp;
    }
    public void setWeight(String weight){
        this.weight = weight;
    }
    public String getWeight(){
        return this.weight;
    }
    public void setSize(String size){
        this.size = size;
    }
    public String getSize(){
        return this.size;
    }
    public void setVelocity(String velocity){
        this.velocity = velocity;
    }
    public String getVelocity(){
        return this.velocity;
    }
    public void setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }
    public String getImgUrl(){
        return this.imgUrl;
    }
    public void setWeapons(List<weapon> weapons){
        this.weapons = weapons;
    }
    public List<weapon> getWeapons(){
        return this.weapons;
    }
    public void setUniqueAbility(List<uniqueAbility> uniqueAbility){
        this.uniqueAbility = uniqueAbility;
    }
    public List<uniqueAbility> getUniqueAbility(){
        return this.uniqueAbility;
    }
}