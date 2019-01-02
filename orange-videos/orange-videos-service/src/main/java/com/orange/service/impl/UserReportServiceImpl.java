package com.orange.service.impl;

import com.orange.mapper.UsersReportMapper;
import com.orange.pojo.UsersReport;
import com.orange.service.UserReportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class UserReportServiceImpl implements UserReportService {
    @Resource
    UsersReportMapper usersReportMapper;

    @Transactional(propagation=Propagation.REQUIRED)
    @Override
    public void reportUser(UsersReport usersReport) {
        usersReport.setId(UUID.randomUUID().toString());
        usersReport.setCreateDate(new Date());
        usersReportMapper.insert(usersReport);
    }
}
