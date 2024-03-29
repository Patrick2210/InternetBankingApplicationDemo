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
import com.szaruga.InternetBankingApplicationDemo.util.StringSortCriteria;
import com.szaruga.InternetBankingApplicationDemo.util.ValidationPageableInput;
import com.szaruga.InternetBankingApplicationDemo.verification.userdetailsdto.ValidationUserDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

/**
 * Service class for managing user details.
 */
@Service
public class UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;
    private final ValidationUserDetailsDto validationUserDetailsDto;
    private final ValidationPageableInput validationPageableInput;

    /**
     * Constructs an instance of the UserDetailsService.
     *
     * @param userDetailsRepository    The repository for managing user details entities.
     * @param validationUserDetailsDto Validator for user details DTOs.
     * @param validationPageableInput  Validator for validating pageable input.
     */
    @Autowired
    public UserDetailsService(UserDetailsRepository userDetailsRepository, ValidationUserDetailsDto validationUserDetailsDto, ValidationPageableInput validationPageableInput) {
        this.userDetailsRepository = userDetailsRepository;
        this.validationUserDetailsDto = validationUserDetailsDto;
        this.validationPageableInput = validationPageableInput;
    }

    /**
     * Retrieves all user details with pagination and optional sorting.
     *
     * @param pageNumber  The page number to retrieve.
     * @param pageSize    The size of each page.
     * @param sortByInput The field to sort by.
     * @return A page of user details DTOs.
     */
    public Page<UsersDetailsPageDto> getAllUsersDetails(int pageNumber, int pageSize, String sortByInput) {
        Pageable pageable;
        if (sortByInput == null) {
            validationPageableInput.validate(pageNumber, pageSize);
            pageable = PageRequest.of(pageNumber, pageSize);
        } else {
            Sort sort = Sort.by(Sort.Direction.ASC, StringSortCriteria.preprocessSortingCriteria(sortByInput));
            pageable = PageRequest.of(pageNumber, pageSize, sort);
        }
        return userDetailsRepository.findAll(pageable).map(UserDetailsMapper::mapUsersDetailsEntityToPageDto);
    }

    /**
     * Retrieves user details by ID.
     *
     * @param id The ID of the user details to retrieve.
     * @return The user details DTO.
     * @throws UserDetailsNotFoundException If user details with the specified ID are not found.
     */
    public GetUserDetailsByIdDto getUserDetailsById(int id) {
        final UserDetailsEntity userDetailsEntity = userDetailsRepository.findById(id)
                .orElseThrow(() -> new UserDetailsNotFoundException(USER_DETAILS_NOT_FOUND_WITH_ID.getMessage() + id));
        return UserDetailsMapper.mapUserEntityToGetUserById(userDetailsEntity);
    }

    /**
     * Saves user details.
     *
     * @param userDetailsDto The user details DTO to save.
     * @return Information about the created user details.
     */
    public CreateUserDetails saveUserDetails(UserDetailsDto userDetailsDto) {
        validationUserDetailsDto.validateDto(userDetailsDto);
        UserDetailsEntity save = userDetailsRepository.save(UserDetailsMapper.toEntity(userDetailsDto));
        return new CreateUserDetails(save.getId());
    }

    /**
     * Deletes user details by ID.
     *
     * @param id The ID of the user details to delete.
     * @throws UserDetailsNotFoundException If user details with the specified ID are not found.
     */
    public void deleteUserDetails(int id) {
        UserDetailsEntity userDetails = userDetailsRepository.findById(id)
                .orElseThrow(() -> new UserDetailsNotFoundException(USER_DETAILS_NOT_FOUND_WITH_ID.getMessage() + id));
        userDetailsRepository.delete(userDetails);
    }
}