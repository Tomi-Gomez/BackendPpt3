package com.proyecto_final_ppt3.handler;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {
    private String mensaje;
    private int status;
    private LocalDateTime timestamp;
    private String path;
}
