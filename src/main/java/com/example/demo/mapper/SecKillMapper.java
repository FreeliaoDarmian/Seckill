package com.example.demo.mapper;

public interface SecKillMapper {
    /**
     * 下单逻辑1  利用user和good的唯一索引 如果主键失败就会滚
     * @param userId
     * @param goodsId
     * @return
     */
    int insertOrder(Integer userId, String goodsId);

    /**
     * 下单逻辑2: 更新库存
     * @param goodsId
     * @return
     */
    int updateStock(String goodsId);
}
