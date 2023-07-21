package compiler.structures;

public class Symbol {
    private String name;
    private DataType type;
    private String value;
    private Boolean used;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public DataType getType() {
        return type;
    }
    public void setType(DataType type) {
        this.type = type;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public Boolean getUsed() {
        return used;
    }
    public void setUsed(Boolean used) {
        this.used = used;
    }
    public Symbol(String name, DataType type) {
        super();
        this.name = name;
        this.type = type;
        this.used = false;
    }
    public Symbol() {
        super();
    }
    @Override
    public String toString() {
        return "Symbol [name=" + name + ", type=" + type + ", value=" + value + ", used=" + used + "]";
    }
}
