package com.example.vivaha_v100.service;

import com.example.vivaha_v100.dto.FilterCriteria;
import com.example.vivaha_v100.dto.ProfileDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilterService {

    public List<ProfileDTO> filterProfiles(List<ProfileDTO> profiles, FilterCriteria criteria) {
        return profiles.stream()
                .filter(p -> criteria.getMaritalStatus() == null || criteria.getMaritalStatus().equalsIgnoreCase(p.getMaritalStatus()))
                .filter(p -> criteria.getMinAge() == null || p.getAge() >= criteria.getMinAge())
                .filter(p -> criteria.getMaxAge() == null || p.getAge() <= criteria.getMaxAge())
                .filter(p -> criteria.getMinSalary() == null || (p.getSalaryPackage() != null && p.getSalaryPackage() >= criteria.getMinSalary()))
                .filter(p -> criteria.getMaxSalary() == null || (p.getSalaryPackage() != null && p.getSalaryPackage() <= criteria.getMaxSalary()))
                .filter(p -> criteria.getMinHeight() == null || (p.getHeight() != null && p.getHeight() >= criteria.getMinHeight()))
                .filter(p -> criteria.getMaxHeight() == null || (p.getHeight() != null && p.getHeight() <= criteria.getMaxHeight()))
                .filter(p -> criteria.getGotraId() == null || criteria.getGotraId().equals(p.getGotraId()))
                .filter(p -> criteria.getCasteId() == null || criteria.getCasteId().equals(p.getCasteId()))
                .filter(p -> criteria.getReligionId() == null || criteria.getReligionId().equals(p.getReligionId()))
                .filter(p -> criteria.getMangalik() == null || criteria.getMangalik().equals(p.getMangalik()))
                .filter(p -> criteria.getDisability() == null || criteria.getDisability().equals(p.getDisability()))
                .collect(Collectors.toList());
    }
}
