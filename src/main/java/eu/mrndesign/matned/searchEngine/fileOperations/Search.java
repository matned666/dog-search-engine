package eu.mrndesign.matned.searchEngine.fileOperations;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public class Search {

    private String value;
    private int numberOfFoundItems;
    private boolean operationSuccess;
    private LocalTime timeOfOperation;
    private LocalDate dateOfOperation;

    public Search(String value, boolean operationSuccess, int numberOfFoundItems) {
        this.value = value;
        this.operationSuccess = operationSuccess;
        this.numberOfFoundItems = numberOfFoundItems;
        timeOfOperation = new LocalTime();
        dateOfOperation = new LocalDate();
    }

    public String toString() {
        return "Searched entry:'" + value + "', operation success: " + operationSuccess + ", time of search: " + timeOfOperation + " " + dateOfOperation + ", found items:" + numberOfFoundItems;
    }
}


