package com.mere.travail.job.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mere.travail.job.Job;
import com.mere.travail.job.JobRepository;
import com.mere.travail.job.JobService;

@Service
public class JobServiceImplementation implements JobService {
    // private List<Job> jobs = new ArrayList<Job>();
    JobRepository jobRepository;
    private Long nextId = 1L;

    public JobServiceImplementation(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long jobId) {
        return jobRepository.findById(jobId).orElse(null);
    }

    @Override
    public boolean deleteJob(Long jobId) {
        try {
            jobRepository.deleteById(jobId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long jobId, Job updateJob) {
        Optional<Job> jobOptional = jobRepository.findById(jobId);
        if ( jobOptional.isPresent() ) {
            Job job = jobOptional.get();
            job.setTitle(updateJob.getTitle());
            job.setDescription(updateJob.getDescription());
            job.setMinSalary(updateJob.getMinSalary());
            job.setMaxSalary(updateJob.getMaxSalary());
            job.setLocation(updateJob.getLocation());
            
            return true; 
        }
    return false;
    }
}
