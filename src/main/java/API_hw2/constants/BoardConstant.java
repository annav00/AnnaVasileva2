package API_hw2.constants;

public enum BoardConstant {

    ID("id"),
    NAME("name"),
    CLOSED("closed"),
    DESC("desc");

    String constantName;

    BoardConstant(String constantName) {
        this.constantName = constantName;
    }

    public String getConstantName() {
        return constantName;
    }
}
