package iSBot;
public class uniqueAbility
{
    private String name;

    private String cooldown;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setCooldown(String cooldown){
        this.cooldown = cooldown;
    }
    public String getCooldown(){
        return this.cooldown;
    }
}