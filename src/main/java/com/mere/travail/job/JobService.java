package com.mere.travail.job;

import java.util.List;

public interface JobService {
    List<Job> getAllJobs();
    void createJob(Job job);
    Job getJobById(Long jobId);
    boolean deleteJob(Long jobId);
    boolean updateJob(Long jobId, Job job);
}
