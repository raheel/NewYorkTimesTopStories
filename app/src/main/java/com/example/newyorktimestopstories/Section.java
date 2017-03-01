package com.example.newyorktimestopstories;

/**
 * Created by raheelkhan on 2/26/17.
 */

public enum Section {
    home,
    opinion,
    world,
    national,
    politics,
    upshot,
    nyregion("NY Region"),
    business,
    technology,
    science,
    health,
    sports,
    arts,
    books,
    movies,
    theater,
    sundayreview("Sunday Review"),
    fashion,
    tmagazine("T Magazine"),
    food,
    travel,
    magazine,
    realestate("Real Estate"),
    automobiles,
    obituaries,
    insider;

    private String displayName;

    Section(String displayName) {
        this.displayName = displayName;
    }

    Section() {
        this.displayName = name().substring(0, 1).toUpperCase() + name().substring(1);
    }

    public String getDisplayName() {
        return displayName;
    }

    public static String toCSV(Enum ... sections){
        String result = "";

        for (int i=0; i<sections.length; i++){
            result += sections[i].name();
            if (i!=sections.length-1){
                result += ",";
            }
        }

        return result;
    }

    public static Section[] toSections(String values){
        String [] s = values.split(",");
        Section [] sections = new Section[s.length];

        for (int i=0; i<s.length; i++){
            sections[i] = Section.valueOf(s[i]);
        }

        return sections;
    }

    }
