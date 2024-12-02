package com.stone.it.rcms.mifi.device.service.impl;

import com.stone.it.rcms.core.exception.RcmsApplicationException;
import com.stone.it.rcms.core.util.UserUtil;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.common.service.ICommonService;
import com.stone.it.rcms.mifi.common.vo.CommonVO;
import com.stone.it.rcms.mifi.device.dao.IDeviceManageDao;
import com.stone.it.rcms.mifi.device.service.IDeviceManageService;
import com.stone.it.rcms.mifi.device.vo.DeviceVO;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/11/19
 * @Desc
 */
@Named
public class DeviceManageService implements IDeviceManageService {

    @Inject
    private IDeviceManageDao deviceManageDao;

    @Inject
    private ICommonService commonService;

    @Override
    public PageResult<DeviceVO> findPageDeviceResult(DeviceVO vo, PageVO pageVO) {
        // 处理增加了查询条件
        List<CommonVO> list = new ArrayList<>();
        if (vo.getEnterpriseId() == null) {
            list = commonService.findEnterpriseListByParentId(vo.getCurrentEnterpriseId());
        }
        return deviceManageDao.findPageDeviceResult(vo, pageVO, list);
    }

    @Override
    public DeviceVO findDeviceDetail(String deviceSn) {
        DeviceVO deviceVO = deviceManageDao.findDeviceDetail(deviceSn);
        if (deviceVO == null || !deviceVO.getTenantId().equals(UserUtil.getTenantId())) {
            throw new RcmsApplicationException(500, "终端设备不存在");
        }
        return deviceVO;
    }

    @Override
    public int createDevice(List<DeviceVO> list) {
        List<DeviceVO> exists = deviceManageDao.findExistDevice(list);
        List<DeviceVO> noExists = new ArrayList<>();
        List<String> existSn = new ArrayList<>();
        for (DeviceVO vo : list) {
            boolean exist = false;
            for (DeviceVO iExist : exists) {
                if (vo.getDeviceSn().equals(iExist.getDeviceSn())) {
                    exist = true;
                    break;
                }
            }
            if (existSn.contains(vo.getDeviceSn())) {
                continue;
            }
            existSn.add(vo.getDeviceSn());
            noExists.add(vo);
        }
        if (noExists.isEmpty()) {
            return 0;
        }
        return deviceManageDao.createDevice(noExists);
    }

    @Override
    public int updateDevice(String deviceSn, DeviceVO vo) {
        findDeviceDetail(deviceSn);
        vo.setDeviceSn(deviceSn);
        return deviceManageDao.updateDevice(vo);
    }

    @Override
    public int deleteDevice(String deviceSn) {
        findDeviceDetail(deviceSn);
        return deviceManageDao.deleteDevice(deviceSn);
    }
}
