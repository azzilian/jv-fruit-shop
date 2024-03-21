package core.basesyntax.service.strategy.strategyimpl;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.dto.FruitTransactionDto;
import core.basesyntax.exception.NegativeBalanceException;
import core.basesyntax.service.strategy.OperationHandler;
import java.util.HashMap;

public class ReturnOperation implements OperationHandler {
    private final StorageDao storageDao;

    public ReturnOperation(StorageDaoImpl storageDao) {
        this.storageDao = storageDao;
    }

    @Override
    public HashMap<String, Integer> apply(FruitTransactionDto dto) {
        HashMap<String, Integer> fruitValue = storageDao.get(dto.fruitName());
        if (fruitValue == null) {
            throw new NegativeBalanceException("Trying to return fruits "
                    + dto.fruitName()
                    + " but there was no fruit in balance or supply which were purchased");
        }
        int newQuantity = fruitValue.get(dto.fruitName()) + dto.quantity();
        return storageDao.add(dto.fruitName(), newQuantity);
    }

    @Override
    public boolean isApplicable(FruitTransactionDto dto) {
        return "r".equals(dto.operationType());
    }
}
