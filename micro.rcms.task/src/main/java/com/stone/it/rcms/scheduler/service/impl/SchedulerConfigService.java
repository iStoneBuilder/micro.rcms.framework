package com.stone.it.rcms.scheduler.service.impl;


import com.stone.it.rcms.core.util.UUIDUtil;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.scheduler.dao.ISchedulerConfigDao;
import com.stone.it.rcms.scheduler.core.manager.QuartzManager;
import com.stone.it.rcms.scheduler.service.ISchedulerConfigService;
import com.stone.it.rcms.scheduler.vo.*;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import org.quartz.SchedulerException;

/**
 *
 * @author cj.stone
 * @Desc
 */
@Named
public class SchedulerConfigService implements ISchedulerConfigService {

  @Resource
  private QuartzManager quartzManager;

  @Inject
  private ISchedulerConfigDao schedulerDao;

  @Override
  public PageResult<SchedulerVO> findQuartzPageResult(SchedulerVO schedulerVO, PageVO pageVO) {
    return schedulerDao.findQuartzPageResult(schedulerVO,pageVO);
  }

  @Override
  public SchedulerVO createQuartz(SchedulerVO schedulerVO) throws Exception {
    // 设置任务ID
    schedulerVO.setQuartzId(UUIDUtil.getUuid());
    // 创建数据
    schedulerDao.createQuartz(schedulerVO);
    // 定时任务启用
    if("enable".equals(schedulerVO.getEnabledFlag())){
      quartzManager.startQuartz(schedulerVO);
    }
    return schedulerVO;
  }

  @Override
  public int deleteQuartz(String quartzId) throws SchedulerException {
    SchedulerVO schedulerVO = schedulerDao.findQuartzInfoById(quartzId);
    // 删除定时任务
    quartzManager.deleteQuartz(schedulerVO);
    // 删除数据表
    return schedulerDao.deleteQuartz(quartzId);
  }

  @Override
  public SchedulerVO updateQuartz(String quartzId,SchedulerVO schedulerVO) throws Exception {
    SchedulerVO oldSchedulerVO =  schedulerDao.findQuartzInfoById(quartzId);
    if("enable".equals(oldSchedulerVO.getEnabledFlag())){
      throw new SchedulerException("启动中的任务禁止修改");
    }
    schedulerVO.setQuartzId(quartzId);
    // 更新数据
    schedulerDao.updateQuartz(schedulerVO);
    // 删除定时任务
    quartzManager.deleteQuartz(schedulerVO);
    if("enable".equals(schedulerVO.getEnabledFlag())){
      quartzManager.startQuartz(schedulerVO);
    }
    return schedulerVO;
  }

  @Override
  public SchedulerVO operateQuartz(String quartzId, String operate) throws Exception {
    // 查询旧数据 (enable:启用; suspend:暂停; restore:恢复; stopped: 停用)
    SchedulerVO schedulerVO = schedulerDao.findQuartzInfoById(quartzId);
    schedulerVO.setEnabledFlag(operate);
    // 定时任务启用
    if("enable".equals(operate)){
      quartzManager.startQuartz(schedulerVO);
    } else if("suspend".equals(operate)){
      // 定时任务暂停
      quartzManager.pauseQuartz(schedulerVO);
    } else if("restore".equals(operate)){
      schedulerVO.setEnabledFlag("enable");
      // 定时任务恢复
      quartzManager.resumeQuartz(schedulerVO);
    } else if("stopped".equals(operate)){
      schedulerVO.setEnabledFlag("disable");
      // 定时任务停用
      quartzManager.deleteQuartz(schedulerVO);
    }
    // 更新数据
    schedulerDao.updateQuartz(schedulerVO);
    return schedulerVO;
  }

}
