package com.springBootLearn.jobapp.review.impl;

import com.springBootLearn.jobapp.company.Company;
import com.springBootLearn.jobapp.company.CompanyService;
import com.springBootLearn.jobapp.review.Review;
import com.springBootLearn.jobapp.review.ReviewRepository;
import com.springBootLearn.jobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {


    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        //Here we used a custom method in repository
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {

        Company company = companyService.getCompanyById(companyId);
        if (company != null){
            review.setCompany(company);
            reviewRepository.save(review);
           return true;
        }
        else{
            return false;
        }
    }
    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        for (Review review : reviews) {
            if (review.getId() ==reviewId) {
                return review;
            }
        }
        return null;
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if (companyService.getCompanyById(companyId) != null){
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if (companyService.getCompanyById(companyId) != null
                && reviewRepository.existsById(reviewId)){
//            Review review = reviewRepository.findById(reviewId).orElse(null);
//            Company company = review.getCompany();
//            company.getReviews().remove(review);
//
//            companyService.updateCompany(company, companyId);
            reviewRepository.deleteById(reviewId);
            return true;
        } else {
            return false;
        }
    }

}
