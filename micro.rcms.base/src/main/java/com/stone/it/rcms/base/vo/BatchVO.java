package com.stone.it.rcms.base.vo;

import lombok.Data;

import java.util.List;

/**
 * @author cj.stone
 * @Date 2022/12/12
 * @Desc
 */
@Data
public class BatchVO extends BaseVO {

    static final long serialVersionUID = 1L;
    /**
     * 插入
     */
    private List<Object> insertItems;
    /**
     * 更新
     */
    private List<Object> updateItems;
    /**
     * 删除
     */
    private List<Object> deleteItems;

}
