package API_hw2.data;

public enum URLConstant {

    BASE("https://api.trello.com/1"),
    BOARDS("/boards/");

    private final String URL;

    URLConstant(String constantName) {
        this.URL = constantName;
    }

    public String getURLConstant() {
        return URL;
    }
}
