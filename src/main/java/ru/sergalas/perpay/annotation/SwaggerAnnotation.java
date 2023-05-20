package ru.sergalas.perpay.annotation;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import ru.sergalas.perpay.security.payload.response.InvalidLoginResponse;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ApiResponse(
        responseCode = "401",
        description = "Unauthorized: the request requires user authentication",
        content = {
                @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = InvalidLoginResponse.class)
                )
        }
)
@SecurityRequirement(name = "Bearer Authentication")
public @interface SwaggerAnnotation {
}
