package com.manoj.UrlShortner.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manoj.UrlShortner.model.UserModel;
import com.manoj.UrlShortner.model.dto.ClickEventDto;
import com.manoj.UrlShortner.model.dto.UrlMappingDTO;
import com.manoj.UrlShortner.service.UrlMappingService;
import com.manoj.UrlShortner.service.UserService;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/urls")
public class UrlMappingController {
    @Autowired
    private UrlMappingService urlMappingService;
    @Autowired
    private UserService userService;

    @PostMapping("/shorten")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UrlMappingDTO> createShortner(@RequestBody Map<String, String> request, Principal principal) {
        String originalUrl = request.get("originalUrl");
        UserModel user = userService.findByUsername(principal.getName());
        UrlMappingDTO urlMappingDTO = urlMappingService.createShortUrl(originalUrl, user);
        return ResponseEntity.ok(urlMappingDTO);
    }

    @GetMapping("/myurls")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<UrlMappingDTO>> getUserUrls(Principal principal) {
        UserModel user = userService.findByUsername(principal.getName());
        List<UrlMappingDTO> urls = urlMappingService.getUrlsByUser(user);
        return ResponseEntity.ok(urls);

    }

    @GetMapping("/analytics/{shortUrl}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ClickEventDto>> getUrlAnalytics(
            @PathVariable String shortUrl,
            @RequestParam("startDate") String startDate, 
            @RequestParam("endDate") String endDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME; 
        LocalDateTime start = LocalDateTime.parse(startDate, formatter); 
        LocalDateTime end=LocalDateTime.parse(endDate,formatter);

        List<ClickEventDto> clickEvents = urlMappingService.getClickEventsByDate(shortUrl, start, end); 

        return ResponseEntity.ok(clickEvents); 
    }
      @GetMapping("/totalClicks")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Map<LocalDate, Long>> getTotalClicksByDate(Principal principal,
                                                                     @RequestParam("startDate") String startDate,
                                                                     @RequestParam("endDate") String endDate){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        UserModel user = userService.findByUsername(principal.getName());
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        Map<LocalDate, Long> totalClicks = urlMappingService.getTotalClicksByUserAndDate(user, start, end);
        return ResponseEntity.ok(totalClicks);
    }


}
