package com.szaruga.InternetBankingApplicationDemo.service;

import com.szaruga.InternetBankingApplicationDemo.dto.userdetails.GetUserDetailsByIdDto;
import com.szaruga.InternetBankingApplicationDemo.dto.userdetails.UserDetailsDto;
import com.szaruga.InternetBankingApplicationDemo.dto.userdetails.UsersDetailsPageDto;
import com.szaruga.InternetBankingApplicationDemo.entity.UserDetailsEntity;
import com.szaruga.InternetBankingApplicationDemo.exception.userdetails.UserDetailsNotFoundException;
import com.szaruga.InternetBankingApplicationDemo.jpa.UserDetailsRepository;
import com.szaruga.InternetBankingApplicationDemo.mapper.UserDetailsMapper;
import com.szaruga.InternetBankingApplicationDemo.model.CreateUserDetails;
import com.szaruga.InternetBankingApplicationDemo.util.PageableUtils;
import com.szaruga.InternetBankingApplicationDemo.verification.userdetailsdto.ValidationUserDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.USER_DETAILS_NOT_FOUND_WITH_ID;

@Service
public class UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;
    private final ValidationUserDetailsDto validationUserDetailsDto;

    @Autowired
    public UserDetailsService(UserDetailsRepository userDetailsRepository, ValidationUserDetailsDto validationUserDetailsDto) {
        this.userDetailsRepository = userDetailsRepository;
        this.validationUserDetailsDto = validationUserDetailsDto;
    }

    public Page<UsersDetailsPageDto> getUsersDetailsPagination(int pageNumber, int pageSize, String sort) {
        //todo zrobic zawezenie sortowania
        Pageable pageable = PageableUtils.buildPageable(pageNumber, pageSize, sort);
        Page<UserDetailsEntity> userDetailsPage = userDetailsRepository.findAll(pageable);
        List<UsersDetailsPageDto> usersDetailsPageDtoList =
                UserDetailsMapper.mapUsersDetailsEntitiesToPageDtoList(userDetailsPage.getContent());
        return new PageImpl<>(usersDetailsPageDtoList, pageable, userDetailsPage.getTotalElements());
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
        Optional<UserDetailsEntity> optionalUser = userDetailsRepository.findById(id);
        if (optionalUser.isPresent()) {
            userDetailsRepository.deleteById(id);
        } else throw new UserDetailsNotFoundException(USER_DETAILS_NOT_FOUND_WITH_ID.getMessage() + id);
    }
}
