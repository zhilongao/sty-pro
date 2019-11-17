package com.common.dto.address;

import java.util.List;

/**
 * @Author long
 * @Date 2019/11/2 9:39
 */
public class AddressSearchResultDTO {

    private int status;

    private String message;

    private List<AddressResultDTO> results;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AddressResultDTO> getResults() {
        return results;
    }

    public void setResults(List<AddressResultDTO> results) {
        this.results = results;
    }
}
