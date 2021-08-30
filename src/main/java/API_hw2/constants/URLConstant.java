package API_hw2.constants;

public enum URLConstant {

    BASE("https://api.trello.com/1"),
    BOARDS("/boards/"),
    LISTS("/lists/"),
    CARDS("/cards/");

    String URL;

    URLConstant(String constantName) {
        this.URL = constantName;
    }

    public String get() {
        return URL;
    }
}
