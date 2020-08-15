package iSBot;

public class part {
    String name;
    String partType;
    String partEffect;
    String quality;
    String imgUrl;
    String obtained;

    public part (String name, String partType, String quality, String partEffect, String imgUrl, String obtained)
    {
        this.name = name;
        this.partType = partType;
        this.quality = quality;
        this.partEffect = partEffect;
        this.imgUrl = imgUrl;
        this.obtained = obtained;
    }

    public part (String name, String partType, String quality, String partEffect, String imgUrl)
    {
        this.name = name;
        this.partType = partType;
        this.quality = quality;
        this.partEffect = partEffect;
        this.imgUrl = imgUrl;
    }

    public part() {

    }

    public void print()
    {
        System.out.format("%n %s ", this.name);
        System.out.format("%n %s ", this.partType);
        System.out.format("%n %s ", this.quality);
        System.out.format("%n %s ", this.partEffect);
        System.out.format("%n %s ", this.imgUrl);
        System.out.format("%n %s ", this.obtained);
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public String getPartEffect() {
        return partEffect;
    }

    public void setPartEffect(String partEffect) {
        this.partEffect = partEffect;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getObtained() {
        return obtained;
    }

    public void setObtained(String obtained) {
        this.obtained = obtained;
    }
}


