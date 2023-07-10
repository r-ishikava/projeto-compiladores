package compiler.structures;

public class Symbol {
    private String name;
    private DataType type;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public DataType getDataType() {
        return type;
    }
    public void setDataType(DataType type) {
        this.type = type;
    }
    public Symbol(String name, DataType type) {
        super();
        this.name = name;
        this.type = type;
    }
    public Symbol() {
        super();
    }
    @Override
    public String toString() {
        return "Ídentifier [name=" + name + ", type=" + type + "]";
    }
}
