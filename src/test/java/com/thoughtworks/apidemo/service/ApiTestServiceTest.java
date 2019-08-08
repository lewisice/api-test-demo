package com.thoughtworks.apidemo.service;

import com.thoughtworks.apidemo.dto.AddResponse;
import com.thoughtworks.apidemo.util.TimeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static com.thoughtworks.apidemo.service.ApiTestService.EQUAL_MINUTE_HINT;
import static com.thoughtworks.apidemo.service.ApiTestService.NOT_VALID_INPUT_HINT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTestServiceTest {

    public static final int RIGHT_NUMBER = 4;
    public static final int MINUTE_NUMBER = 20;
    public static final int OUT_OF_SCOPE_NUMBER = 2000;

    @MockBean
    private TimeUtils timeUtils;

    @Autowired
    private ApiTestService apiTestService;

    @Test
    public void should_succeed_increment_number_when_input_number_within_scope() {
        AddResponse addResponse = apiTestService.getCheckNumberResult(RIGHT_NUMBER);
        assertThat(addResponse.getData()).isEqualTo(String.valueOf(RIGHT_NUMBER + 1));
        assertThat(addResponse.getErrorCode()).isEqualTo(0);
    }

    @Test
    public void should_get_error_when_input_number_equal_current_minute() {
        when(timeUtils.getCurrentMinutes()).thenReturn(MINUTE_NUMBER);
        AddResponse addResponse = apiTestService.getCheckNumberResult(MINUTE_NUMBER);
        long num = timeUtils.getCurrentMinutes();
        assertThat(addResponse.getData()).isEqualTo(EQUAL_MINUTE_HINT);
        assertThat(addResponse.getErrorCode()).isEqualTo(1);
    }

    @Test
    public void should_get_error_when_input_number_out_of_scope() {
        AddResponse addResponse = apiTestService.getCheckNumberResult(OUT_OF_SCOPE_NUMBER);
        assertThat(addResponse.getData()).isEqualTo(NOT_VALID_INPUT_HINT);
        assertThat(addResponse.getErrorCode()).isEqualTo(1);
    }
}