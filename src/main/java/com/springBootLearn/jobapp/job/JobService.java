package com.springBootLearn.jobapp.job;



import java.util.List;


public interface JobService {
    List<Job> getAllJobs();
    Job getJobByID(int id);
    void createJob(Job job);

    boolean DeleteJobById(int id);

    boolean UpdateJobDetailsById(int id, Job updated_job);
}
