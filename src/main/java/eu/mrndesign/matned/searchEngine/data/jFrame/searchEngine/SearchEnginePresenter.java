package eu.mrndesign.matned.searchEngine.data.jFrame.searchEngine;

import eu.mrndesign.matned.searchEngine.data.interpreter.DataInterpreter;

import java.util.LinkedList;
import java.util.List;

public class SearchEnginePresenter implements SearchEngineContract.Presenter{

    private final SearchEngineContract.View view;
    private DataInterpreter mediator;
    private SearchEngineScreen screen;
    private int listSize;



    public SearchEnginePresenter(SearchEngineScreen screen, SearchEngineContract.View view) {
        this.screen = screen;
        this.view = view;
        listSize = 0;
    }

    @Override
    public void search() {
        mediator = new DataInterpreter(screen.getChoice());
        view.onSearch(htmlTextListBuild(), listSize);
    }

    @Override
    public void changeTermsOfSearch(String value) {
        //TODO
    }

    @Override
    public int listSize() {
        return listSize;
    }

    private String htmlTextListBuild() {
        String choice = screen.getChoice();
        String value = screen.getInputTextField().getText();
        List<Object> list = new LinkedList<Object>(mediator.getResultList(value));
        listSize = list.size();
        if(list.size() > 0) {
            StringBuilder builder = new StringBuilder();
            builder.append("<html>");
            for (Object el : list) {
                builder.append("<p>").append(el.toString()).append("</p>");
            }
            builder.append("</html>");
            return String.valueOf(builder);
        } else return "No results";

    }

}
