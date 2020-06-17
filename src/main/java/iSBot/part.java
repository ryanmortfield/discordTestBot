package iSBot;

public class part {
    String name;
    String partType;
    String partEffect;
    String quality;
    String imgUrl;

    public part (String name, String partType, String quality, String partEffect, String imgUrl)
    {
        this.name = name;
        this.partType = partType;
        this.quality = quality;
        this.partEffect = partEffect;
        this.imgUrl = imgUrl;
    }

    public void print()
    {
        System.out.format("%n %s ", this.name);
        System.out.format("%n %s ", this.partType);
        System.out.format("%n %s ", this.quality);
        System.out.format("%n %s ", this.partEffect);
        System.out.format("%n %s ", this.imgUrl);
    }

    public String getName()
    {
        return this.name;
    }
}


