package core.basesyntax.dao;

import java.util.HashMap;

public interface StorageDao {
    HashMap<String, Integer> add(String fruitName, Integer quantity);

    HashMap<String, Integer> get(String fruitName);
}
