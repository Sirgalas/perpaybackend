package ru.sergalas.perpay.annotation.swagger;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.sergalas.perpay.annotation.SwaggerAnnotation;
import ru.sergalas.perpay.entities.payment.dto.PaymentWriteDTO;
import ru.sergalas.perpay.entities.users.dto.UserReadDTO;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@SwaggerAnnotation
@Tag(name = "User")
@ApiResponse(
        responseCode = "200",
        description = "Ok",
        content = {
                @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = PaymentWriteDTO.class)
                )
        }
)
@SecurityRequirement(name = "Bearer Authentication")
public @interface PaymentAnnotation {
}
