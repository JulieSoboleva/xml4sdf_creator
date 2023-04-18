package com.example.mainform;

public class Doc
{
    private int id;
    private String name = "";
    private String extraRegionInfo = "";
    private String referenceSystem = "";
    private String storageFormat = "";
    private String secretClass = "";
    private String creator = "";
    private String rightHolder = "";
    private String areaStateDate = "";
    private String accessCondition = "";
    private String objectCreateDate = "";
    private String subtype = "";

    private String comments = "";
    private String objectQuantity = "";
    private String location = "";

    public Doc(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public String getExtraRegionInfo() {
        return extraRegionInfo;
    }

    public void setExtraRegionInfo(String extraRegionInfo) {
        this.extraRegionInfo = extraRegionInfo;
    }

    public String getReferenceSystem() {
        return referenceSystem;
    }

    public void setReferenceSystem(String referenceSystem) {
        this.referenceSystem = referenceSystem;
    }

    public String getStorageFormat() {
        return storageFormat;
    }

    public void setStorageFormat(String storageFormat) {
        this.storageFormat = storageFormat;
    }

    public String getSecretClass() {
        return secretClass;
    }

    public void setSecretClass(String secretClass) {
        this.secretClass = secretClass;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getRightHolder() {
        return rightHolder;
    }

    public void setRightHolder(String rightHolder) {
        this.rightHolder = rightHolder;
    }

    public String getAreaStateDate() {
        return areaStateDate;
    }

    public void setAreaStateDate(String areaStateDate) {
        areaStateDate = areaStateDate.trim();
        if (areaStateDate.length() == 4){
            areaStateDate += "-01-01";
        }
        this.areaStateDate = areaStateDate;
    }

    public String getAccessCondition() {
        return accessCondition;
    }

    public void setAccessCondition(String accessCondition) {
        this.accessCondition = accessCondition;
    }

    public String getObjectCreateDate() {
        return objectCreateDate;
    }

    public void setObjectCreateDate(String objectCreateDate) {
        this.objectCreateDate = objectCreateDate;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {

        this.subtype = subtype;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getObjectQuantity() {
        return objectQuantity;
    }

    public void setObjectQuantity(String objectQuantity) {
        this.objectQuantity = objectQuantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getScale()
    {
        int pos1 = name.indexOf(":");
        if (pos1 < 0)
            return "";
        String s = "1" + name.substring(pos1);
        s = s.replace('.', ' ');
        s = s.replace(',', ' ');
        pos1 = s.indexOf(' ');
        if (pos1 > 0)
            s = s.substring(0, pos1);
        return s;
    }

    public boolean isEmpty()
    {
        if (name == "" && extraRegionInfo == "" && referenceSystem == "" &&
            storageFormat == "" && secretClass == "" && creator == "" &&
            rightHolder == "" && areaStateDate == "" && accessCondition == "" &&
            objectCreateDate == "" && subtype == "" && objectQuantity == "" && location == "")
            return true;
        return false;
    }

    public void cloneFieldsFrom(Doc src)
    {
        if (name == "")
            name = src.name;
        if (extraRegionInfo == "")
            extraRegionInfo = src.extraRegionInfo;
        if (referenceSystem == "")
            referenceSystem = src.referenceSystem;
        if (storageFormat == "")
            storageFormat = src.storageFormat;
        if (secretClass == "")
            secretClass = src.secretClass;
        if (creator == "")
            creator = src.creator;
        if (rightHolder == "")
            rightHolder = src.rightHolder;
        if (areaStateDate == "")
            areaStateDate = src.areaStateDate;
        if (accessCondition == "")
            accessCondition = src.accessCondition;
        if (objectCreateDate == "")
            objectCreateDate = src.objectCreateDate;
        if (subtype == "")
            subtype = src.subtype;
        if (objectQuantity == "")
            objectQuantity = src.objectQuantity;
        if (location == "")
            location = src.location;
    }

    @Override
    public String toString() {
        return "Doc " + id +
                "\nname='" + name + '\'' +
                "\nextraRegionInfo='" + extraRegionInfo + '\'' +
                "\nreferenceSystem='" + referenceSystem + '\'' +
                "\nstorageFormat='" + storageFormat + '\'' +
                "\nsecretClass='" + secretClass + '\'' +
                "\ncreator='" + creator + '\'' +
                "\nrightHolder='" + rightHolder + '\'' +
                "\nareaStateDate='" + areaStateDate + '\'' +
                "\naccessCondition='" + accessCondition + '\'' +
                "\nobjectCreateDate='" + objectCreateDate + '\'' +
                "\nsubtype='" + subtype + '\'' +
                "\nobjectQuantity='" + objectQuantity + '\'' +
                "\nlocation='" + location + "\'\n";
    }
}
