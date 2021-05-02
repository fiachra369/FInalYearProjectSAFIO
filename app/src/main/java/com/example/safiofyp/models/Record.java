package com.example.safiofyp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Record {
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("_full_text")
    @Expose
    private String fullText;
    @SerializedName("Number")
    @Expose
    private String number;
    @SerializedName("Longitude")
    @Expose
    private String longitude;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Latitude")
    @Expose
    private String latitude;
    @SerializedName("_id")
    @Expose
    private Integer id;

    int difference;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getDifference() {
        return difference;
    }

    public void setDifference(int difference) {
        this.difference = difference;
    }

    /*@SerializedName("_id")
    @Expose
    private Integer id;*/
    @SerializedName("Date & Time")
    @Expose
    private String dateTime;
    @SerializedName("O'Connell St Outside Pennys")
    @Expose
    private Integer oConnellStOutsidePennys;
    @SerializedName("O'Connell St Outside Clerys")
    @Expose
    private Integer oConnellStOutsideClerys;
    @SerializedName("Mary Street")
    @Expose
    private Integer maryStreet;
    @SerializedName("Capel Street - Removed from site 20/10")
    @Expose
    private Integer capelStreetRemovedFromSite2010;
    @SerializedName("Aston Quay")
    @Expose
    private Integer astonQuay;
    @SerializedName("Grafton Street @ CompuB")
    @Expose
    private Integer graftonStreetCompuB;
    @SerializedName("Talbot Street North")
    @Expose
    private Integer talbotStreetNorth;
    @SerializedName("Doilier Street, Burgh Quay")
    @Expose
    private Integer doilierStreetBurghQuay;
    @SerializedName("Dawson Street Replacement")
    @Expose
    private Integer dawsonStreetReplacement;
    @SerializedName("Dame Street (Counter Missing)")
    @Expose
    private Integer dameStreetCounterMissing;
    @SerializedName("Talbot Street South")
    @Expose
    private Integer talbotStreetSouth;
    @SerializedName("O'Connell St, Parnell St @ AIB")
    @Expose
    private Integer oConnellStParnellStAIB;
    @SerializedName("Grafton Street / Nassau Street / Suffolk Street")
    @Expose
    private Integer graftonStreetNassauStreetSuffolkStreet;
    @SerializedName("College Green, Bank Of Ireland")
    @Expose
    private Integer collegeGreenBankOfIreland;
    @SerializedName("Henry Street")
    @Expose
    private Integer henryStreet;
    @SerializedName("Westmoreland Street East")
    @Expose
    private Integer westmorelandStreetEast;
    @SerializedName("Dawson Street")
    @Expose
    private String dawsonStreet;
    @SerializedName("Liffey Street")
    @Expose
    private Integer liffeyStreet;
    @SerializedName("Westmoreland Street West")
    @Expose
    private Integer westmorelandStreetWest;
    @SerializedName("Grafton Street")
    @Expose
    private Integer graftonStreet;
    @SerializedName("Bachelors Walk")
    @Expose
    private Integer bachelorsWalk;
    @SerializedName("College Green @ Church Lane")
    @Expose
    private Integer collegeGreenChurchLane;
    @SerializedName("College Green - Dame St Side")
    @Expose
    private Integer collegeGreenDameStSide;

    /*public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }*/

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getOConnellStOutsidePennys() {
        return oConnellStOutsidePennys;
    }

    public void setOConnellStOutsidePennys(Integer oConnellStOutsidePennys) {
        this.oConnellStOutsidePennys = oConnellStOutsidePennys;
    }

    public Integer getOConnellStOutsideClerys() {
        return oConnellStOutsideClerys;
    }

    public void setOConnellStOutsideClerys(Integer oConnellStOutsideClerys) {
        this.oConnellStOutsideClerys = oConnellStOutsideClerys;
    }

    public Integer getMaryStreet() {
        return maryStreet;
    }

    public void setMaryStreet(Integer maryStreet) {
        this.maryStreet = maryStreet;
    }

    public Integer getCapelStreetRemovedFromSite2010() {
        return capelStreetRemovedFromSite2010;
    }

    public void setCapelStreetRemovedFromSite2010(Integer capelStreetRemovedFromSite2010) {
        this.capelStreetRemovedFromSite2010 = capelStreetRemovedFromSite2010;
    }

    public Integer getAstonQuay() {
        return astonQuay;
    }

    public void setAstonQuay(Integer astonQuay) {
        this.astonQuay = astonQuay;
    }

    public Integer getGraftonStreetCompuB() {
        return graftonStreetCompuB;
    }

    public void setGraftonStreetCompuB(Integer graftonStreetCompuB) {
        this.graftonStreetCompuB = graftonStreetCompuB;
    }

    public Integer getTalbotStreetNorth() {
        return talbotStreetNorth;
    }

    public void setTalbotStreetNorth(Integer talbotStreetNorth) {
        this.talbotStreetNorth = talbotStreetNorth;
    }

    public Integer getDoilierStreetBurghQuay() {
        return doilierStreetBurghQuay;
    }

    public void setDoilierStreetBurghQuay(Integer doilierStreetBurghQuay) {
        this.doilierStreetBurghQuay = doilierStreetBurghQuay;
    }

    public Integer getDawsonStreetReplacement() {
        return dawsonStreetReplacement;
    }

    public void setDawsonStreetReplacement(Integer dawsonStreetReplacement) {
        this.dawsonStreetReplacement = dawsonStreetReplacement;
    }

    public Integer getDameStreetCounterMissing() {
        return dameStreetCounterMissing;
    }

    public void setDameStreetCounterMissing(Integer dameStreetCounterMissing) {
        this.dameStreetCounterMissing = dameStreetCounterMissing;
    }

    public Integer getTalbotStreetSouth() {
        return talbotStreetSouth;
    }

    public void setTalbotStreetSouth(Integer talbotStreetSouth) {
        this.talbotStreetSouth = talbotStreetSouth;
    }

    public Integer getOConnellStParnellStAIB() {
        return oConnellStParnellStAIB;
    }

    public void setOConnellStParnellStAIB(Integer oConnellStParnellStAIB) {
        this.oConnellStParnellStAIB = oConnellStParnellStAIB;
    }

    public Integer getGraftonStreetNassauStreetSuffolkStreet() {
        return graftonStreetNassauStreetSuffolkStreet;
    }

    public void setGraftonStreetNassauStreetSuffolkStreet(Integer graftonStreetNassauStreetSuffolkStreet) {
        this.graftonStreetNassauStreetSuffolkStreet = graftonStreetNassauStreetSuffolkStreet;
    }

    public Integer getCollegeGreenBankOfIreland() {
        return collegeGreenBankOfIreland;
    }

    public void setCollegeGreenBankOfIreland(Integer collegeGreenBankOfIreland) {
        this.collegeGreenBankOfIreland = collegeGreenBankOfIreland;
    }

    public Integer getHenryStreet() {
        return henryStreet;
    }

    public void setHenryStreet(Integer henryStreet) {
        this.henryStreet = henryStreet;
    }

    public Integer getWestmorelandStreetEast() {
        return westmorelandStreetEast;
    }

    public void setWestmorelandStreetEast(Integer westmorelandStreetEast) {
        this.westmorelandStreetEast = westmorelandStreetEast;
    }

    public String getDawsonStreet() {
        return dawsonStreet;
    }

    public void setDawsonStreet(String dawsonStreet) {
        this.dawsonStreet = dawsonStreet;
    }

    public Integer getLiffeyStreet() {
        return liffeyStreet;
    }

    public void setLiffeyStreet(Integer liffeyStreet) {
        this.liffeyStreet = liffeyStreet;
    }

    public Integer getWestmorelandStreetWest() {
        return westmorelandStreetWest;
    }

    public void setWestmorelandStreetWest(Integer westmorelandStreetWest) {
        this.westmorelandStreetWest = westmorelandStreetWest;
    }

    public Integer getGraftonStreet() {
        return graftonStreet;
    }

    public void setGraftonStreet(Integer graftonStreet) {
        this.graftonStreet = graftonStreet;
    }

    public Integer getBachelorsWalk() {
        return bachelorsWalk;
    }

    public void setBachelorsWalk(Integer bachelorsWalk) {
        this.bachelorsWalk = bachelorsWalk;
    }

    public Integer getCollegeGreenChurchLane() {
        return collegeGreenChurchLane;
    }

    public void setCollegeGreenChurchLane(Integer collegeGreenChurchLane) {
        this.collegeGreenChurchLane = collegeGreenChurchLane;
    }

    public Integer getCollegeGreenDameStSide() {
        return collegeGreenDameStSide;
    }

    public void setCollegeGreenDameStSide(Integer collegeGreenDameStSide) {
        this.collegeGreenDameStSide = collegeGreenDameStSide;
    }

}
