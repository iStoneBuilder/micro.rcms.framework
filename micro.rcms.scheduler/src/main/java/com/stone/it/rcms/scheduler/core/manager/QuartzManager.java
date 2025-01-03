package com.stone.it.rcms.scheduler.core.manager;

import com.alibaba.fastjson.JSON;
import com.stone.it.rcms.scheduler.vo.QuartzGroupVO;
import com.stone.it.rcms.scheduler.vo.SchedulerVO;

import javax.annotation.Resource;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DateBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author cj.stone
 * @Desc
 */
@Component
public class QuartzManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzManager.class);
    /**
     * 注入QuartzConfig中定义的任务调度器scheduler
     */
    @Resource
    private Scheduler scheduler;

    /**
     * 开启某个任务
     *
     * @param scheduledJob scheduledJob
     * @throws Exception scheduledJob
     */
    public void startQuartz(SchedulerVO scheduledJob) throws Exception {
        JobKey jobKey = JobKey.jobKey(scheduledJob.getQuartzId(), scheduledJob.getQuartzGroupCode());
        // 不存在则添加任务
        if (!scheduler.checkExists(jobKey)) {
            LOGGER.info("开启任务：{}", scheduledJob.getQuartzName());
            // 利用反射机制获取任务执行类
            @SuppressWarnings("unchecked")
            Class<? extends Job> jobClass = (Class<? extends Job>)(Class
                .forName("com.stone.it.rcms.scheduler.core.actuator.SchedulerJob").newInstance().getClass());
            // 设置任务明细，调用定义的任务逻辑
            JobDetail jobDetail = JobBuilder.newJob(jobClass)
                // 添加认证信息(也可通过usingJobData传参数)
                .withIdentity(scheduledJob.getQuartzId(), scheduledJob.getQuartzGroupCode())
                // 执行
                .build();
            // 设置任务触发器，cronTrigger规则定义执行规则
            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                // 通过键值对方式向job实现业务逻辑传参数
                .usingJobData("jobName", scheduledJob.getQuartzName())
                .usingJobData("jobCron", scheduledJob.getQuartzCron())
                .usingJobData("jobData", JSON.toJSONString(scheduledJob))
                // 添加认证信息
                .withIdentity(scheduledJob.getQuartzId(), scheduledJob.getQuartzGroupCode())
                // 程序启动后间隔多久开始执行任务
                .startAt(DateBuilder.futureDate(5, DateBuilder.IntervalUnit.SECOND))
                // 执行Cron表达时
                .withSchedule(CronScheduleBuilder.cronSchedule(scheduledJob.getQuartzCron()))
                // 执行
                .build();
            // 把任务明细和触发器注册到任务调度中
            scheduler.scheduleJob(jobDetail, cronTrigger);
        }
        // 启动
        scheduler.start();
    }

    /**
     * 修改任务的Cron表达式
     *
     * @param scheduledJob scheduledJob
     * @return scheduledJob
     * @throws SchedulerException SchedulerException
     */
    public boolean modifyQuartz(SchedulerVO scheduledJob) throws SchedulerException {
        TriggerKey triggerKey = new TriggerKey(scheduledJob.getQuartzId(), scheduledJob.getQuartzGroupCode());
        CronTrigger oldCronTrigger = (CronTrigger)scheduler.getTrigger(triggerKey);
        String oldTime = oldCronTrigger.getCronExpression();
        if (!oldTime.equalsIgnoreCase(scheduledJob.getQuartzCron())) {
            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                // 通过键值对方式向job实现业务逻辑传参数
                .usingJobData("jobName", scheduledJob.getQuartzName())
                .usingJobData("jobCron", scheduledJob.getQuartzCron())
                .usingJobData("jobData", JSON.toJSONString(scheduledJob))
                .withIdentity(scheduledJob.getQuartzId(), scheduledJob.getQuartzGroupCode())
                .withSchedule(CronScheduleBuilder.cronSchedule(scheduledJob.getQuartzCron())).build();
            scheduler.rescheduleJob(triggerKey, cronTrigger);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 暂停所有任务
     *
     * @throws SchedulerException scheduledJob
     */
    public void pauseAllQuartz() throws SchedulerException {
        scheduler.pauseAll();
    }

    /**
     * 暂停某个任务
     *
     * @param scheduledJob scheduledJob
     * @throws SchedulerException scheduledJob
     */
    public void pauseQuartz(SchedulerVO scheduledJob) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(scheduledJob.getQuartzId(), scheduledJob.getQuartzGroupCode());
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return;
        }
        scheduler.pauseJob(jobKey);
    }

    /**
     * 恢复所有任务
     *
     * @throws SchedulerException SchedulerException
     */
    public void resumeAllQuartz() throws SchedulerException {
        scheduler.resumeAll();
    }

    /**
     * 恢复某个任务
     * 
     * @param scheduledJob scheduledJob
     * @throws SchedulerException schedulerException
     */
    public void resumeQuartz(SchedulerVO scheduledJob) throws Exception {
        JobKey jobKey = JobKey.jobKey(scheduledJob.getQuartzId(), scheduledJob.getQuartzGroupCode());
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            LOGGER.info("恢复任务不存在：{}", scheduledJob.getQuartzName());
            startQuartz(scheduledJob);
            return;
        }
        scheduler.resumeJob(jobKey);
    }

    /**
     * 删除任务
     *
     * @param scheduledJob scheduledJob
     * @throws SchedulerException scheduledJob
     */
    public void deleteQuartz(SchedulerVO scheduledJob) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(scheduledJob.getQuartzId(), scheduledJob.getQuartzGroupCode());
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return;
        }
        scheduler.deleteJob(jobKey);
    }

}
