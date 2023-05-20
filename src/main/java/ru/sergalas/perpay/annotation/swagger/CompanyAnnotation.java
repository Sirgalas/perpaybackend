package ru.sergalas.perpay.annotation.swagger;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.sergalas.perpay.annotation.SwaggerAnnotation;
import ru.sergalas.perpay.entities.companies.dto.CompanyReadDTO;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@SwaggerAnnotation
@Tag(name = "Company")
@ApiResponse(
        responseCode = "200",
        description = "Ok",
        content = {
                @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = CompanyReadDTO.class)
                )
        }
)
@SecurityRequirement(name = "Bearer Authentication")
public @interface CompanyAnnotation {
}
