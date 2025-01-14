package com.stone.it.rcms.base.service.impl;

import com.stone.it.rcms.base.dao.IPayConfigDao;
import com.stone.it.rcms.base.service.IPayConfigService;
import com.stone.it.rcms.base.vo.PayConfigVO;
import com.stone.it.rcms.core.util.RandomUtil;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * 支付配置
 * 
 * @author cj.stone
 * @Date 2024/12/9
 * @Desc
 */
@Named
public class PayConfigService implements IPayConfigService {

    @Inject
    private IPayConfigDao payConfigDao;

    @Override
    public PageResult<PayConfigVO> findPayConfigPageList(PayConfigVO payConfigVO, PageVO pageVO) {
        return payConfigDao.findPayConfigPageList(payConfigVO, pageVO);
    }

    @Override
    public PayConfigVO findPayConfigDetail(String payConfigId, PayConfigVO payConfigVO) {
        payConfigVO.setPayConfigId(payConfigId);
        return payConfigDao.findPayConfigDetail(payConfigVO);
    }

    @Override
    public PayConfigVO createPayConfig(PayConfigVO payConfigVO) {
        payConfigVO.setPayConfigId(RandomUtil.stringGenerator(10));
        payConfigDao.createPayConfig(payConfigVO);
        return payConfigVO;
    }

    @Override
    public PayConfigVO updatePayConfig(String payConfigId, PayConfigVO payConfigVO) {
        payConfigVO.setPayConfigId(payConfigId);
        payConfigDao.updatePayConfig(payConfigVO);
        return payConfigVO;
    }

    @Override
    public void deletePayConfig(String payConfigId) {
        payConfigDao.deletePayConfig(payConfigId);
    }
}
