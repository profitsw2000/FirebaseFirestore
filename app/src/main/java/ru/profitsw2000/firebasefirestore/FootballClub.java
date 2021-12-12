package ru.profitsw2000.firebasefirestore;

public class FootballClub {

    private String id ;
    private String club ;
    private String city ;
    private String country ;
    private String fc_logo  ;

    public FootballClub() {
    }

    public FootballClub(String id, String club, String city, String country, String fc_logo) {
        this.id = id    ;
        this.club = club    ;
        this.city = city    ;
        this.country = country  ;
        this.fc_logo = fc_logo  ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFc_logo() {
        return fc_logo;
    }

    public void setFc_logo(String fc_logo) {
        this.fc_logo = fc_logo;
    }
}
