package ru.sergalas.perpay.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sergalas.perpay.annotation.swagger.CompanyAnnotation;
import ru.sergalas.perpay.entities.companies.dto.CompanyReadDTO;
import ru.sergalas.perpay.entities.companies.dto.CompanyWriteDTO;
import ru.sergalas.perpay.entities.companies.exceptions.CompanyNotFoundException;
import ru.sergalas.perpay.entities.companies.repository.CompanyDsl;
import ru.sergalas.perpay.entities.companies.service.CompaniesService;
import ru.sergalas.perpay.entities.users.exception.NotAllowException;
import ru.sergalas.perpay.entities.users.exception.UserNotFoundException;
import ru.sergalas.perpay.web.services.ResponseErrorValidation;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/company")
@CrossOrigin
@RequiredArgsConstructor
public class CompanyController {

    private final CompaniesService companiesService;
    private final ResponseErrorValidation responseErrorValidation;
    private final CompanyDsl companyDsl;


    @Operation(
        summary = "get all company",
        operationId = "companyAll"
    )
    @CompanyAnnotation
    @GetMapping("")
    public ResponseEntity<List<CompanyReadDTO>> index()
    {
        return new ResponseEntity<>(companiesService.findAll(),HttpStatus.OK);
    }

    @Operation(
        summary = "get all company from user",
        operationId = "companyAllFomUserId"
    )
    @CompanyAnnotation
    @GetMapping("/all-principal")
    public ResponseEntity<List<CompanyReadDTO>> getAllFromUser(Principal principal)
    {
        try {
            return new ResponseEntity<>(companyDsl.getUserCompaniesByPrincipal(principal),HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(
        summary = "one view company",
        operationId = "companyOneView"
    )
    @CompanyAnnotation
    @GetMapping("/{id}")
    public ResponseEntity<CompanyReadDTO> getOne(@PathVariable("id") String id)
    {
        try {
            return new ResponseEntity<>(companiesService.findOne(id), HttpStatus.OK);
        } catch (CompanyNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(
            summary = "Company create",
            operationId = "companyCreate"
    )
    @CompanyAnnotation
    @PostMapping("")
    public ResponseEntity<Object> create(
            @Valid @RequestBody CompanyWriteDTO companyReadDTO,
            BindingResult bindingResult,
            Principal principal
    ) throws CompanyNotFoundException, UserNotFoundException, NotAllowException {
        ResponseEntity<Object> error = responseErrorValidation.mapValidationService(bindingResult);
        if(!ObjectUtils.isEmpty(error)) {
            return error;
        }
        return new ResponseEntity<>(
                companiesService.create(new CompanyWriteDTO(),principal),
                HttpStatus.CREATED
        );
    }

    @Operation(
            summary = "Company create",
            operationId = "companyCreate"
    )
    @CompanyAnnotation
    @PutMapping("/{id}")
    public ResponseEntity<Object> edit(
            @PathVariable("id") String id,
            @Valid @RequestBody CompanyWriteDTO companyReadDTO,
            BindingResult bindingResult,
            Principal principal
    ) throws CompanyNotFoundException, UserNotFoundException, NotAllowException {
        ResponseEntity<Object> error = responseErrorValidation.mapValidationService(bindingResult);
        if(!ObjectUtils.isEmpty(error)) {
            return error;
        }
        return new ResponseEntity<>(
                companiesService.edit(id,companyReadDTO,principal),
                HttpStatus.CREATED
        );
    }

    @Operation(
            summary = "Company create",
            operationId = "companyCreate"
    )
    @CompanyAnnotation
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(
            @PathVariable("id") String id,
            Principal principal
    ) throws UserNotFoundException, NotAllowException {
        return new ResponseEntity<>(companiesService.delete(id,principal),HttpStatus.OK);
    }
}
