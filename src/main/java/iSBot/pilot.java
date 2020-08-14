package iSBot;

public class pilot {
    String pilotName;
    String pilotDescription;
    String skill1;
    String skill1Description;
    String skill2;
    String skill2Description;
    String skill3;
    String skill3Description;
    String skill4;
    String skill4Description;
    String imgUrl;

    public pilot (String pilotName, String pilotDescription, String skill1, String skill1Description, String skill2, String skill2Description, String skill3, String skill3Description, String skill4, String skill4Description, String imgUrl)
    {
        this.pilotName = pilotName;
        this.pilotDescription = pilotDescription;
        this.skill1 = skill1;
        this.skill1Description = skill1Description;
        this.skill2 = skill2;
        this.skill2Description = skill2Description;
        this.skill3 = skill3;
        this.skill3Description = skill3Description;
        this.skill4 = skill4;
        this.skill4Description = skill4Description;
        this.imgUrl = imgUrl;
    }
    public pilot (String pilotName, String pilotDescription, String skill1, String skill1Description, String skill2, String skill2Description, String skill3, String skill3Description, String skill4, String skill4Description)
    {
        this.pilotName = pilotName;
        this.pilotDescription = pilotDescription;
        this.skill1 = skill1;
        this.skill1Description = skill1Description;
        this.skill2 = skill2;
        this.skill2Description = skill2Description;
        this.skill3 = skill3;
        this.skill3Description = skill3Description;
        this.skill4 = skill4;
        this.skill4Description = skill4Description;
    }

    public pilot() {

    }

    public void print()
    {
        System.out.format("%n %s ", this.pilotName);
        System.out.format("%n %s ", this.pilotDescription);
        System.out.format("%n %s ", this.skill1);
        System.out.format("%n %s ", this.skill1Description);
        System.out.format("%n %s ", this.skill2);
        System.out.format("%n %s ", this.skill2Description);
        System.out.format("%n %s ", this.skill3);
        System.out.format("%n %s ", this.skill3Description);
        System.out.format("%n %s ", this.skill4);
        System.out.format("%n %s ", this.skill4Description);
        System.out.format("%n %s ", this.imgUrl);


    }

    public String getName() {
        return this.pilotName.toLowerCase();
    }

    public String getPilotName() {
        return pilotName;
    }

    public void setPilotName(String pilotName) {
        this.pilotName = pilotName;
    }

    public String getPilotDescription() {
        return pilotDescription;
    }

    public void setPilotDescription(String pilotDescription) {
        this.pilotDescription = pilotDescription;
    }

    public String getSkill1() {
        return skill1;
    }

    public void setSkill1(String skill1) {
        this.skill1 = skill1;
    }

    public String getSkill1Description() {
        return skill1Description;
    }

    public void setSkill1Description(String skill1Description) {
        this.skill1Description = skill1Description;
    }

    public String getSkill2() {
        return skill2;
    }

    public void setSkill2(String skill2) {
        this.skill2 = skill2;
    }

    public String getSkill2Description() {
        return skill2Description;
    }

    public void setSkill2Description(String skill2Description) {
        this.skill2Description = skill2Description;
    }

    public String getSkill3() {
        return skill3;
    }

    public void setSkill3(String skill3) {
        this.skill3 = skill3;
    }

    public String getSkill3Description() {
        return skill3Description;
    }

    public void setSkill3Description(String skill3Description) {
        this.skill3Description = skill3Description;
    }

    public String getSkill4() {
        return skill4;
    }

    public void setSkill4(String skill4) {
        this.skill4 = skill4;
    }

    public String getSkill4Description() {
        return skill4Description;
    }

    public void setSkill4Description(String skill4Description) {
        this.skill4Description = skill4Description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
