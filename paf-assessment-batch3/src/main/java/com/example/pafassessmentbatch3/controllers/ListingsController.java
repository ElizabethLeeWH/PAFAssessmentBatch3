package com.example.pafassessmentbatch3.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.pafassessmentbatch3.models.Listing;
import com.example.pafassessmentbatch3.models.Reservation;
import com.example.pafassessmentbatch3.models.Search;
import com.example.pafassessmentbatch3.models.VacancyCheckResult;
import com.example.pafassessmentbatch3.services.AccVacancyService;
import com.example.pafassessmentbatch3.services.ListingService;
import com.example.pafassessmentbatch3.services.ReservationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class ListingsController {

    @Autowired
    private ListingService lService;

    @Autowired
    private ReservationService rService;

    @Autowired
    private AccVacancyService aVService;

    @GetMapping
    public ModelAndView getView1() {
        ModelAndView mav = new ModelAndView("view1.html");
        mav.addObject("search", new Search());
        mav.addObject("country", lService.stringOfDistinctCountry());
        return mav;
    }

    @GetMapping("/search")
    public ModelAndView getView2(@RequestParam(name = "country") String country,
                                 @RequestParam(name = "accommodates") Integer accommodates,
                                 @RequestParam(name = "minPrice") double minPrice,
                                 @RequestParam(name = "maxPrice") double maxPrice,
                                 HttpServletRequest request,
                                 HttpSession sess) {
        
        Search search = createSearchObject(country, accommodates, minPrice, maxPrice);
        StringBuffer url = request.getRequestURL();
        String queryString = request.getQueryString();
        String fullUrl = url.append("?").append(queryString).toString();
        sess.setAttribute("url", fullUrl);
        sess.setAttribute("listing", fullUrl);

        ModelAndView mav = new ModelAndView("view2.html");
        mav.addObject("search", search);
        mav.addObject("listings", lService.listofSearchResult(search));
        return mav;
    }

    @GetMapping("/details/{id}")
    public ModelAndView getView3(HttpSession sess, @PathVariable String id){
        List<Listing> l = lService.searchListingById(id);
        Listing selectedListing;
        if(l.isEmpty()){
           selectedListing = null;
        } else {
            selectedListing = l.getFirst();
        }
        ModelAndView mav = new ModelAndView("view3");
        mav.addObject("previousPageUrl", (String) sess.getAttribute("url"));
        mav.addObject("listing", selectedListing);
        mav.addObject("reservation", new Reservation());


        return mav;
    }

    @PostMapping("/reservation/{id}")
    public ModelAndView submitReservation(@PathVariable("id") String accId, @ModelAttribute Reservation reservation, HttpSession sess){
        ModelAndView mav = new ModelAndView();
        reservation.setAccId(accId);
        rService.createResvId(reservation);
        mav.addObject("reservation", reservation);
        mav.addObject("previousPageUrl", (String) sess.getAttribute("url"));

        VacancyCheckResult result = aVService.isThereVacancy(reservation);

        if(!result.isSuccess()){
            mav.addObject("errorMessage", result.getErrorMessage());
            mav.setViewName("view3");
        }else{
            rService.insertNewReservationSQL(reservation);
            mav.setViewName("view4");
        }
        return mav;
    }

    private Search createSearchObject(String country, Integer accommodates, double minPrice, double maxPrice) {
        Search search = new Search();
        search.setCountry(country);
        search.setAccommodates(accommodates);
        search.setMinPrice(minPrice);
        search.setMaxPrice(maxPrice);
        return search;
    }
}
