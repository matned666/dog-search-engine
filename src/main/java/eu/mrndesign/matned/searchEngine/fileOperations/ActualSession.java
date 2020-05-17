package eu.mrndesign.matned.searchEngine.fileOperations;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.LinkedList;
import java.util.List;

public class ActualSession {

    private List<Search> sessionSearchesList;
    private LocalTime sessionStartTime;
    private LocalDate sessionStartDate;
    private LocalTime sessionEndTime;
    private LocalDate sessionEndDate;

    public ActualSession() {
        sessionStartTime = new LocalTime();
        sessionStartDate = new LocalDate();
        sessionSearchesList = new LinkedList<>();
    }

    public List<Search> getSessionSearchesList() {
        return sessionSearchesList;
    }

    public void setSessionEndTime(LocalTime sessionEndTime) {
        this.sessionEndTime = sessionEndTime;
    }

    public void setSessionEndDate(LocalDate sessionEndDate) {
        this.sessionEndDate = sessionEndDate;
    }

    public String toString() {
        String temp = "<p>Started a new session:</p>";
        temp += "<p>Session start: " + sessionStartTime + " " + sessionStartDate + "</p>";
        for (Search el : sessionSearchesList) {
            temp += "<p>" + el + "</p>";
        }
        if (sessionEndTime == null) {
            temp += "<p>^^^^ ACTUAL SESSION  ^^^^</p></p><br></p>";
        } else {
            temp += "<p>Session end: " + sessionEndTime + " " + sessionEndDate + "</p>";
        }

        return temp;
    }
}



