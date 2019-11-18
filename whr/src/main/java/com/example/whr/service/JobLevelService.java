package com.example.whr.service;

import com.example.whr.bean.JobLevel;
import com.example.whr.dao.JobLevelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JobLevelService {
    @Autowired
    private JobLevelMapper jobLevelMapper;

    public List<JobLevel> getAllJobLevels() {
        return jobLevelMapper.getAllJobLevels();
    }

    public JobLevel getJobLevelByName(String name) {
        return jobLevelMapper.getJobLevelByName(name);
    }

    public int addJobLevel(JobLevel jobLevel) {
        if (getJobLevelByName(jobLevel.getName()) != null) {
            return -1;
        }
        return jobLevelMapper.addJobLevel(jobLevel);
    }

    public boolean deleteJobLevelById(String ids) {
        String[] split  = ids.split(",");
        return jobLevelMapper.deleteJobLevelById(split) == split.length;
    }

    public int updateJobLevel(JobLevel jobLevel) {
        return jobLevelMapper.updateJobLevel(jobLevel);
    }
}
