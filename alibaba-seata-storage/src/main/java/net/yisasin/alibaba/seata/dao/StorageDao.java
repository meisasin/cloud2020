package net.yisasin.alibaba.seata.dao;

import net.yisasin.springcloud.common.model.BranchTable;
import net.yisasin.springcloud.common.model.GlobalTable;
import net.yisasin.springcloud.common.model.LockTable;
import net.yisasin.springcloud.common.model.UndoLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StorageDao {

    /**
     * 扣除库存数量
     * @param commodityCode
     * @param count
     */
    void deduct(@Param("commodityCode") String commodityCode,
                @Param("count") int count);

    List<GlobalTable> listGlobal();

    List<BranchTable> listBranch();

    List<LockTable> listLock();

    List<UndoLog> listUndolog();
}
