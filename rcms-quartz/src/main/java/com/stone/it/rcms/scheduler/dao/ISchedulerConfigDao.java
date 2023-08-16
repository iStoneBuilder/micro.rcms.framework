package com.stone.it.rcms.scheduler.dao;


import com.stone.it.rcms.com.vo.PageResult;
import com.stone.it.rcms.com.vo.PageVO;
import com.stone.it.rcms.scheduler.vo.QuartzJobVO;
import com.stone.it.rcms.scheduler.vo.SchedulerVO;
import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2023/7/19
 * @Desc
 */
public interface ISchedulerConfigDao {

  PageResult<SchedulerVO> findQuartzPageResult(SchedulerVO schedulerVO, PageVO pageVO);

  List<SchedulerVO> findQuartzList(SchedulerVO schedulerVO);

  int createQuartz(SchedulerVO schedulerVO);

  int deleteQuartz(String jobId);

  int updateQuartz(SchedulerVO schedulerVO);

  SchedulerVO findQuartzInfo(String quartzId);

}
