package compiler.structures;

import java.util.HashMap;
import java.util.ArrayList;

public class SymbolTable {
    private HashMap<String, Symbol> table;

    public SymbolTable() {
        table = new HashMap<String, Symbol>();
    }

    public void add_symbol(Symbol symbol) {
        table.put(symbol.getName(), symbol);
    }

    public boolean exists(String name) {
        return table.get(name) != null;
    }

    public Symbol get_symbol(String name) {
        return table.get(name);
    }

    public ArrayList<Symbol> get_all() {
        ArrayList<Symbol> symbols = new ArrayList<>();
        for (Symbol symbol : table.values()) {
            symbols.add(symbol);
        }
        return symbols;
    }
}
