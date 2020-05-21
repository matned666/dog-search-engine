package eu.mrndesign.matned.searchEngine.data.statics;

public class Check {

    public static boolean isNumeric(String content) {
        if (content == null) {
            return false;
        }
        try {
            Integer.parseInt(content);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
