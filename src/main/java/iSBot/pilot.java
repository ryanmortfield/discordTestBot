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
}
