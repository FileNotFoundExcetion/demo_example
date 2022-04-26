package com.example.demo.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestFormRequest {

    //required system_field
    private Long formId;
    private String formName;
    private String requestUserName;
    private String requestType;
    private String email;
    private String requestDetail;
    //system_field
    private String requestRole;
    private String country;
    private String phone;

    private List<CustomerFieldData> customFieldData;

    @Getter
    @Setter
    public static class CustomerFieldData{
        private String name;
        private Object value;
    }
}
