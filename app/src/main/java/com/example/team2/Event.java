package com.example.team2;

public class Event {
    private String Name;
    private String Date;
    private String Begin;
    private String End;
    private String Info;
    private boolean Public;

    public Event() {
        // req empty constructor
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getBegin() {
        return Begin;
    }

    public void setBegin(String begin) {
        Begin = begin;
    }

    public String getEnd() {
        return End;
    }

    public void setEnd(String end) {
        End = end;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public boolean isPublic() {
        return Public;
    }

    public void setPublic(boolean aPublic) {
        Public = aPublic;
    }
}
