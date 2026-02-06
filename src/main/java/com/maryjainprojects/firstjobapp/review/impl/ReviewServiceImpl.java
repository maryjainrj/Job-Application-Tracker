package com.maryjainprojects.firstjobapp.review.impl;

import com.maryjainprojects.firstjobapp.company.Company;
import com.maryjainprojects.firstjobapp.company.CompanyRepository;
import com.maryjainprojects.firstjobapp.company.CompanyService;
import com.maryjainprojects.firstjobapp.review.Review;
import com.maryjainprojects.firstjobapp.review.ReviewRepository;
import com.maryjainprojects.firstjobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyRepository companyRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyRepository companyRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyRepository = companyRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReview(Long companyId) {
        List<Review> reviews=reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company=companyService.getCompanyById(companyId);
        review.setId(null);
        if(company!=null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }



    @Override
    public Review getReview(Long reviewId, Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);

        return reviews.stream()
                .filter(review -> reviewId.equals(review.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review UpdatedReview) {

        if(companyService.getCompanyById(companyId) != null) {
            UpdatedReview.setCompany(companyService.getCompanyById(companyId));
            UpdatedReview.setId(reviewId);
            reviewRepository.save(UpdatedReview);
            return true;
        }
        else
        {
            return false;
        }
    }
    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if (reviewRepository.existsById(reviewId) &&
                reviewRepository.existsByIdAndCompanyId(reviewId, companyId)) {
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }

}
