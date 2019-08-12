package com.thoughtworks.apidemo.service;

import com.thoughtworks.apidemo.dto.AddResponse;
import com.thoughtworks.apidemo.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ApiTestService {

    @Autowired
    private TimeUtils timeUtils;

    public static final String NOT_VALID_INPUT_HINT = "please input [0,1000]";
    public static final String EQUAL_MINUTE_HINT = "internal error";

    public AddResponse getCheckNumberResult(Integer number) {
        AddResponse response = new AddResponse();
        checkIsValidInput(number, response);
        incrementNumber(number, response);
        return response;
    }

    private void incrementNumber(Integer number, AddResponse response) {
        if (Objects.isNull(response.getErrorCode())) {
            response.setData(String.valueOf(++number));
            response.setErrorCode(0);
        }
    }

    private void checkIsValidInput(Integer number, AddResponse response) {
        if (number < 0 || number > 1000) {
            response.setErrorCode(1);
            response.setData(NOT_VALID_INPUT_HINT);
        }
        if (number.equals(timeUtils.getCurrentMinutes())) {
            response.setErrorCode(1);
            response.setData(EQUAL_MINUTE_HINT);
        }
    }
}
