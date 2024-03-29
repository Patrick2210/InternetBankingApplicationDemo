package com.szaruga.InternetBankingApplicationDemo.service;

import com.szaruga.InternetBankingApplicationDemo.dto.user.*;
import com.szaruga.InternetBankingApplicationDemo.entity.UserEntity;
import com.szaruga.InternetBankingApplicationDemo.exception.user.PeselValidationException;
import com.szaruga.InternetBankingApplicationDemo.util.PeselValidationRequestSender;
import com.szaruga.InternetBankingApplicationDemo.util.ValidationPageableInput;
import org.springframework.http.ResponseEntity;
import com.szaruga.InternetBankingApplicationDemo.exception.user.UserHasAccountsException;
import com.szaruga.InternetBankingApplicationDemo.exception.user.UserNotFoundException;
import com.szaruga.InternetBankingApplicationDemo.exception.validation.IllegalSortingRequest;
import com.szaruga.InternetBankingApplicationDemo.jpa.UserRepository;
import com.szaruga.InternetBankingApplicationDemo.mapper.UserMapper;
import com.szaruga.InternetBankingApplicationDemo.model.user.CreateUser;
import com.szaruga.InternetBankingApplicationDemo.util.StringSortCriteria;
import com.szaruga.InternetBankingApplicationDemo.verification.userdto.VerificationUserDto;
import com.szaruga.InternetBankingApplicationDemo.verification.useremailupdatedto.VerificationEmailUpdateDto;
import com.szaruga.InternetBankingApplicationDemo.verification.userpasswordupdatedto.VerificationUserPasswordUpdateDto;
import com.szaruga.InternetBankingApplicationDemo.verification.userupgradedto.VerificationUserUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

/**
 * Service class for managing user-related operations.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final VerificationUserDto verificationUserDto;
    private final VerificationUserUpdateDto verificationUserUpdateDto;
    private final VerificationUserPasswordUpdateDto verificationUserPasswordUpdateDto;
    private final VerificationEmailUpdateDto verificationEmailUpdateDto;
    private final PeselValidationRequestSender peselValidationRequestSender;
    private final ValidationPageableInput validationPageableInput;

    /**
     * Constructs an instance of the UserService.
     *
     * @param userRepository                    The repository for managing user entities.
     * @param verificationUserDto               Validator for user DTOs.
     * @param verificationUserUpdateDto         Validator for updating user DTOs.
     * @param verificationUserPasswordUpdateDto Validator for updating user password DTOs.
     * @param verificationEmailUpdateDto        Validator for updating user email DTOs.
     * @param peselValidationRequestSender      Validator for sending request with pesel number.
     * @param validationPageableInput           Validator for validating pageable input.
     */
    @Autowired
    public UserService(UserRepository userRepository,
                       VerificationUserDto verificationUserDto,
                       VerificationUserUpdateDto verificationUserUpdateDto,
                       VerificationUserPasswordUpdateDto verificationUserPasswordUpdateDto,
                       VerificationEmailUpdateDto verificationEmailUpdateDto,
                       PeselValidationRequestSender peselValidationRequestSender,
                       ValidationPageableInput validationPageableInput) {
        this.userRepository = userRepository;
        this.verificationUserDto = verificationUserDto;
        this.verificationUserUpdateDto = verificationUserUpdateDto;
        this.verificationUserPasswordUpdateDto = verificationUserPasswordUpdateDto;
        this.verificationEmailUpdateDto = verificationEmailUpdateDto;
        this.peselValidationRequestSender = peselValidationRequestSender;
        this.validationPageableInput = validationPageableInput;
    }

    /**
     * Retrieves all users with pagination and optional sorting.
     *
     * @param pageNumber  The page number to retrieve.
     * @param pageSize    The size of each page.
     * @param sortByInput The field to sort by.
     * @return A page of user DTOs.

     */
    public Page<UsersPageDto> getAllUsers(int pageNumber, int pageSize, String sortByInput) {
        Pageable pageable;
        if (sortByInput == null) {
            validationPageableInput.validate(pageNumber, pageSize);
            pageable = PageRequest.of(pageNumber, pageSize);
        } else {
            Sort sort = Sort.by(Sort.Direction.ASC, StringSortCriteria.preprocessSortingCriteria(sortByInput));
            pageable = PageRequest.of(pageNumber, pageSize, sort);
        }
        return userRepository.findAll(pageable).map(UserMapper::mapUsersEntityToUsersPageDto);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The DTO representing the user.
     * @throws UserNotFoundException If the user with the specified ID is not found.
     */
    public GetUserByIdDto getUserById(long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_WITH_ID.getMessage() + id));
        return UserMapper.mapUserEntityToGetUserByIdDto(userEntity);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id The ID of the user to delete.
     * @throws UserNotFoundException    If the user with the specified ID is not found.
     * @throws UserHasAccountsException If the user has associated accounts and cannot be deleted.
     */
    public void deleteUser(long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_WITH_ID.getMessage() + id));
        if (userEntity.getAccounts().isEmpty()) {
            userRepository.delete(userEntity);
        } else {
            throw new UserHasAccountsException(USER_HAS_ACCOUNT.getMessage());
        }
    }

    /**
     * Saves a new user.
     *
     * @param dto The DTO representing the user to be saved.
     * @return The DTO representing the newly created user.
     * @throws PeselValidationException If the PESEL validation fails.
     */
    public CreateUser saveUser(UserDto dto) {
        verificationUserDto.userDto(dto);
        ResponseEntity<String> response = peselValidationRequestSender.sendPeselValidationRequest(dto.getNumberPesel());
        if (response.getStatusCode().value() == 200) {
            UserEntity save = userRepository.save(UserMapper.toEntity(dto));
            return new CreateUser(save.getId());
        } else {
            throw new PeselValidationException(PESEL_INVALID_VALIDATION.getMessage());
        }
    }

    /**
     * Updates a user's information.
     *
     * @param id        The ID of the user to update.
     * @param updateDto The DTO containing the updated user information.
     * @throws UserNotFoundException If the user with the specified ID is not found.
     */
    public void updateUser(long id, UserUpdateDto updateDto) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_WITH_ID.getMessage() + id));
        verificationUserUpdateDto.userUpdateDto(updateDto);
        userEntity.setFirstName(updateDto.getFirstName());
        userEntity.setLastName(updateDto.getLastName());
        userEntity.setPhoneNumber(updateDto.getPhoneNumber());
        userEntity.setEmail(updateDto.getEmail());
        userRepository.save(userEntity);
    }

    /**
     * Updates a user's password.
     *
     * @param id                The ID of the user to update.
     * @param updatePasswordDto The DTO containing the updated password information.
     * @throws UserNotFoundException If the user with the specified ID is not found.
     */
    public void updateUserPassword(long id, UserPasswordUpdateDto updatePasswordDto) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_WITH_ID.getMessage() + id));

        verificationUserPasswordUpdateDto.userPasswordUpdateDto(updatePasswordDto);
        userEntity.setPassword(updatePasswordDto.getPassword());
        userRepository.save(userEntity);
    }

    /**
     * Updates a user's email.
     *
     * @param id             The ID of the user to update.
     * @param emailUpdateDto The DTO containing the updated email information.
     * @throws UserNotFoundException If the user with the specified ID is not found.
     */
    public void updateUserEmail(long id, UserEmailUpdateDto emailUpdateDto) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_WITH_ID.getMessage() + id));

        verificationEmailUpdateDto.verificationEmailUpdateDto(emailUpdateDto);
        userEntity.setEmail(emailUpdateDto.getEmail());
        userRepository.save(userEntity);
    }

    private String generateResetToken() {
        return UUID.randomUUID().toString();
    }
}