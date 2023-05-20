package ru.sergalas.perpay.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sergalas.perpay.annotation.swagger.CompanyAnnotation;
import ru.sergalas.perpay.annotation.swagger.UserAnnotation;
import ru.sergalas.perpay.entities.companies.exceptions.CompanyNotFoundException;
import ru.sergalas.perpay.entities.users.dto.UserReadDTO;
import ru.sergalas.perpay.entities.users.dto.UserWriteDTO;
import ru.sergalas.perpay.entities.users.exception.UserNotFoundException;
import ru.sergalas.perpay.entities.users.repository.UserRepository;
import ru.sergalas.perpay.entities.users.service.UserService;
import ru.sergalas.perpay.web.services.ResponseErrorValidation;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("api/user")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ResponseErrorValidation responseErrorValidation;

    @Operation(
            summary = "User redact",
            operationId = "userRedact"
    )
    @UserAnnotation
    @PutMapping("/{id}")
    public ResponseEntity<Object> edit(
            @Valid @RequestBody UserWriteDTO userWriteDTO,
            BindingResult bindingResult,
            Principal principal
    ) throws UserNotFoundException {
        ResponseEntity<Object> error = responseErrorValidation.mapValidationService(bindingResult);
        if(!ObjectUtils.isEmpty(error)) {
            return error;
        }
        return new ResponseEntity<>(
                userService.edit(userWriteDTO,principal),
                HttpStatus.CREATED
        );
    }

    @Operation(
            summary = " add Company from User",
            operationId = "addCompanyFromUser"
    )
    @CompanyAnnotation
    @PostMapping("/{company_id}")
    public ResponseEntity<Object> addCompany(
            @PathVariable("company_id") String company_id,
            Principal principal
    ) throws CompanyNotFoundException {
        return new ResponseEntity<>(
                userService.addCompany(company_id,principal),
                HttpStatus.CREATED
        );
    }

    @Operation(
            summary = "Company create",
            operationId = "companyCreate"
    )
    @CompanyAnnotation
    @PostMapping("/{company_id}")
    public ResponseEntity<Object> editSubscriberCode(
            @PathVariable("id") String id,
            @PathVariable("subscriberCode") String subscriberCode,
            Principal principal
    ) throws CompanyNotFoundException {
        return new ResponseEntity<>(
                userService.editSubscriberCode(id,subscriberCode,principal),
                HttpStatus.CREATED
        );
    }
}

