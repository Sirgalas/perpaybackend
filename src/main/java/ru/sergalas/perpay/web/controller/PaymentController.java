package ru.sergalas.perpay.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sergalas.perpay.annotation.swagger.PaymentAnnotation;
import ru.sergalas.perpay.entities.payment.dto.PaymentWriteDTO;
import ru.sergalas.perpay.entities.payment.service.PaymentService;
import ru.sergalas.perpay.entities.users.exception.UserNotFoundException;
import ru.sergalas.perpay.web.services.ResponseErrorValidation;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("api/payment")
@CrossOrigin
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final ResponseErrorValidation responseErrorValidation;

    @Operation(
        summary = "full pay company",
        operationId = "fullPayCompany"
    )
    @PaymentAnnotation
    @GetMapping("/{company_id}")
    public ResponseEntity<Object> index(
        @PathVariable("company_id") String company_id,
        Principal principal
    ) throws UserNotFoundException {
        return new ResponseEntity<>(
            paymentService.index(company_id,principal),
            HttpStatus.CREATED
        );
    }

    @Operation(
            summary = "add pay company",
            operationId = "addPayCompany"
    )
    @PaymentAnnotation
    @PostMapping("")
    public ResponseEntity<Object> create (
        PaymentWriteDTO paymentWriteDTO
    ) {
        return new ResponseEntity<>(
                paymentService.create(paymentWriteDTO),
                HttpStatus.CREATED
        );
    }

    @Operation(
            summary = "edit pay company",
            operationId = "editPayCompany"
    )
    @PaymentAnnotation
    @PostMapping("/{id}")
    public ResponseEntity<Object> edit (
        @PathVariable("id") String id,
        PaymentWriteDTO paymentWriteDTO
    ) {
        return new ResponseEntity<>(
                paymentService.edit(id,paymentWriteDTO),
                HttpStatus.CREATED
        );
    }

    @Operation(
            summary = "delete pay company",
            operationId = "deletePayCompany"
    )
    @PaymentAnnotation
    @PostMapping("/{id}")
    public ResponseEntity<Object> delete (
        @PathVariable("id") String id
    ) {
        return new ResponseEntity<>(
                paymentService.delete(id),
                HttpStatus.OK
        );
    }

}
