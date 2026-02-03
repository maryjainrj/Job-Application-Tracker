package com.maryjainprojects.firstjobapp.job.impl;

import com.maryjainprojects.firstjobapp.job.Job;
import com.maryjainprojects.firstjobapp.job.JobIRepository;
import com.maryjainprojects.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    //private List<Job> jobs = new ArrayList<Job>();
    JobIRepository jobIRepository;


    private Long nextId = 1L;


    public JobServiceImpl(JobIRepository jobIRepository) {
        this.jobIRepository = jobIRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobIRepository.findAll();
    }

    @Override
    public String createJob(Job job) {
       // job.setId(nextId++);
        jobIRepository.save(job);
        return "Job added successfully";

    }

    @Override
    public Job getJobById(Long id) {
    return jobIRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
    try{jobIRepository.deleteById(id);
        return true;
    }catch(Exception e){
        return false;
    }


    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobIRepository.findById(id);

           if (jobOptional.isPresent()) {
               Job job = jobOptional.get();
               job.setTitle( updatedJob.getTitle());
               job.setDescription( updatedJob.getDescription());
               job.setMinSalary( updatedJob.getMinSalary());
               job.setMaxSalary(updatedJob.getMaxSalary());
               job.setLocation(updatedJob.getLocation());
               jobIRepository.save(job);
                return true;
           }
        return false;
       }

    }



