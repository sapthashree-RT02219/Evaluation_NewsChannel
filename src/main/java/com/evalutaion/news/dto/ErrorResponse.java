package com.evalutaion.news.dto;

import com.evalutaion.news.service.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse implements OutgoingDto {

    @Schema(required = true, description = "Error Code")
    private ErrorCode code;

    @Schema(description = "Error message")
    private String message;
}
