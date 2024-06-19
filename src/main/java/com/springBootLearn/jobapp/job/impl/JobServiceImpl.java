package com.springBootLearn.jobapp.job.impl;


import com.springBootLearn.jobapp.job.Job;
import com.springBootLearn.jobapp.job.JobRepository;
import com.springBootLearn.jobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    //private final List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    //private static int next_id = 0;
    @Override
    public List<Job> getAllJobs() {
        //return jobs;
        return jobRepository.findAll();
    }

    @Override
    public Job getJobByID(int id) {
//
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public void createJob(Job job) {

        jobRepository.save(job);
    }

    @Override
    public boolean DeleteJobById(int id) {
        if (jobRepository.existsById(id)){
            jobRepository.deleteById(id);
            return true;
        }else{
            return false;
        }


    }

    @Override
    public boolean UpdateJobDetailsById(int id, Job updated_job) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updated_job.getTitle());
            job.setDescription(updated_job.getDescription());
            job.setMinSalary(updated_job.getMinSalary());
            job.setMaxSalary(updated_job.getMaxSalary());
            job.setLocation(updated_job.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;

    }


}
