package API_hw2.data;

public enum BoardConstant {

    ID("id"),
    NAME("name"),
    CLOSED("closed"),
    DESC("desc");

    private final String constantName;

    BoardConstant(String constantName) {
        this.constantName = constantName;
    }

    public String getConstantName() {
        return constantName;
    }
}
