package com.pichincha.cell.template.domain.base;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ResponseDto implements Serializable {
    private String message;
    private Boolean error;
    private Object content;

    public ResponseDto(Object data) {
        this.content = data;
        this.message = "Successful request";
        this.error = false;
    }

    public ResponseDto(Object data, String msg) {
        this.content = data;
        this.message = msg;
        this.error = false;
    }
}
