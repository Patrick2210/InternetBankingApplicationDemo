package com.szaruga.InternetBankingApplicationDemo.service;

import com.szaruga.InternetBankingApplicationDemo.dto.userdetails.GetUserDetailsByIdDto;
import com.szaruga.InternetBankingApplicationDemo.dto.userdetails.UserDetailsDto;
import com.szaruga.InternetBankingApplicationDemo.dto.userdetails.UsersDetailsPageDto;
import com.szaruga.InternetBankingApplicationDemo.entity.UserDetailsEntity;
import com.szaruga.InternetBankingApplicationDemo.exception.userdetails.UserDetailsNotFoundException;
import com.szaruga.InternetBankingApplicationDemo.exception.validation.IllegalSortingRequest;
import com.szaruga.InternetBankingApplicationDemo.jpa.UserDetailsRepository;
import com.szaruga.InternetBankingApplicationDemo.mapper.UserDetailsMapper;
import com.szaruga.InternetBankingApplicationDemo.model.userdetails.CreateUserDetails;
import com.szaruga.InternetBankingApplicationDemo.verification.userdetailsdto.ValidationUserDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

@Service
public class UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;
    private final ValidationUserDetailsDto validationUserDetailsDto;

    @Autowired
    public UserDetailsService(UserDetailsRepository userDetailsRepository, ValidationUserDetailsDto validationUserDetailsDto) {
        this.userDetailsRepository = userDetailsRepository;
        this.validationUserDetailsDto = validationUserDetailsDto;
    }

    public Page<UsersDetailsPageDto> getAllUsersDetails(int pageNumber, int pageSize, String sortByInput) {
        Pageable pageable;
        if (sortByInput == null) {
            pageable = PageRequest.of(pageNumber, pageSize);
            return userDetailsRepository.findAll(pageable).map(UserDetailsMapper::mapUsersDetailsEntityToPageDto);
        } else {
            Sort sort = Sort.by(Sort.Direction.ASC, sortByInput);
            pageable = PageRequest.of(pageNumber, pageSize, sort);
            if (SORTING_ID.getMessage().equals(sortByInput) || SORTING_POSTCODE.getMessage().equals(sortByInput) ||
                    SORTING_CITY.getMessage().equals(sortByInput)) {
                return userDetailsRepository.findAll(pageable).map(UserDetailsMapper::mapUsersDetailsEntityToPageDto);
            } else {
                throw new IllegalSortingRequest(INVALID_SORT_FIELD.getMessage() + sortByInput);
            }
        }
    }

    public GetUserDetailsByIdDto getUserDetailsById(int id) {
        final UserDetailsEntity userDetailsEntity = userDetailsRepository.findById(id)
                .orElseThrow(() -> new UserDetailsNotFoundException(USER_DETAILS_NOT_FOUND_WITH_ID.getMessage() + id));
        return UserDetailsMapper.mapUserEntityToGetUserById(userDetailsEntity);
    }


    public CreateUserDetails saveUserDetails(UserDetailsDto userDetailsDto) {
        validationUserDetailsDto.validateDto(userDetailsDto);
        UserDetailsEntity save = userDetailsRepository.save(UserDetailsMapper.toEntity(userDetailsDto));
        return new CreateUserDetails(save.getId());
    }

    public void deleteUserDetails(int id) {
        UserDetailsEntity userDetails = userDetailsRepository.findById(id)
                .orElseThrow(() -> new UserDetailsNotFoundException(USER_DETAILS_NOT_FOUND_WITH_ID.getMessage() + id));
        userDetailsRepository.delete(userDetails);
    }
}