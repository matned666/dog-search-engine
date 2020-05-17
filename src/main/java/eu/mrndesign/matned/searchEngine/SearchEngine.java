package eu.mrndesign.matned.searchEngine;


import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class SearchEngine {

    private List titlesList;
    private List contentList;
    private List resourceList;

    // for tests
    SearchEngine() {
        titlesList = new LinkedList();
        contentList = new LinkedList();
        this.resourceList = new LinkedList();
    }

    SearchEngine(List resourceList) {
        titlesList = new LinkedList();
        contentList = new LinkedList();
        this.resourceList = resourceList;
    }

    private String[] splitTitleAndContent(String element) {
        return element.split(" - ", 2);
    }

    private void addSearchedResourceItemToNewList(List<String> resourceList, String regex) {
        Pattern p = Pattern.compile(regex);
        for (String el : resourceList) {
            Matcher m = p.matcher(el.toLowerCase());
            if (m.find()) {
                titlesList.add(splitTitleAndContent(el)[0]);
                contentList.add(splitTitleAndContent(el)[1]);
            }
        }
    }

    List getTitlesList() {
        return titlesList;
    }

    List getContentList() {
        return contentList;
    }

    List getResourceList() {
        return resourceList;
    }

    boolean isSearchedItemInResource(String regex) {
        addSearchedResourceItemToNewList(resourceList, regex);
        return titlesList.size() > 0;
    }

    String noEntriesMessage(String regex) {
        if (!isSearchedItemInResource(regex)) return "Brak wyników.";
        else return null;
    }


}


