package com.scu927.common;
import com.scu927.service.RoomBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Chuhan
 * @date 2024/9/18
 */

@Component
public class SimpleTaskScheduler {
    @Autowired
    private RoomBookingService roomBookingService;
    private static final Logger logger = LoggerFactory.getLogger(SimpleTaskScheduler.class);


    //use cron to execute service once 30min
    @Scheduled(cron = "* */30 * * * *")
    public void executeTaskWithCron() {

        Response<?> response = roomBookingService.cancelAllExpiredBooking();
        logger.info("cancel task execute successful："+response.getMessage());


    }

}