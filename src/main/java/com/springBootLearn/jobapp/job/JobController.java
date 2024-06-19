package com.springBootLearn.jobapp.job;


import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// Try giving path here too
@RestController

public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getAllJobs(){
        //List<Job> l=  jobService.getAllJobs();
        //return new ResponseEntity<>(l,HttpStatus.OK);
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<Job> getJobByID(@PathVariable("id") int id){
        Job job =jobService.getJobByID(id);
        if (job != null){
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/jobs")
    public ResponseEntity<String> CreateJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job with ID-"+job.getId() + " added Successfully",HttpStatus.CREATED);
        //return "Job with ID- "+ job.getId() + " is added successfully" ;
    }

    // DELETE /jobs/{id}
    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String>DeleteJobById(@PathVariable int id){
        if (jobService.DeleteJobById(id)){
            //Not 0-> deleted succesfully
            return ResponseEntity.ok("Job with ID- "+ id + " Deleted Successfully");
        }
        return new ResponseEntity<>("Job with Id-"+ id + " Not found",HttpStatus.NOT_FOUND);

    }
    // PUT /jobs/{id}  [The request body should contain the updated job details]
    @PutMapping("/jobs/{id}")
    public ResponseEntity<String> UpdateJobDetailsById (@PathVariable int id, @RequestBody Job updated_job){
       if (jobService.UpdateJobDetailsById(id, updated_job)){
           return ResponseEntity.ok("Job with ID- "+ id + " Updated Successfully");
       }
        return new ResponseEntity<>("Job with Id-"+ id + " Not found",HttpStatus.NOT_FOUND);
    }



}

/*

GET /jobs: Get all jobs
GET /jobs/{id}: Get a specific job by ID
POST /jobs: Create a new job (request body should contain the job details)
DELETE /jobs/{id}: Delete a specific job by ID
PUT /jobs/{id}: Update a specific job by ID (request body should contain the updated job details)

Example API URLs:
GET {base_url}/jobs
GET {base_url}/jobs/1
POST {base_url}/jobs
DELETE {base_url}/jobs/1
PUT {base_url}/jobs/1

*/