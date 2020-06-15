package parser.common.symbolTable;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

    Map<Integer, SymbolTableEntry> store;

    Integer index;

    public SymbolTable() {
        store = new HashMap<>(100);
        index = 0;
    }

    public Object getVariable(Integer id) {
        return store.get(id);
    }

    public Integer storeVariable(SymbolTableEntry symbolTableEntry) {
        store.put(++index, symbolTableEntry);
        return index;
    }
}
