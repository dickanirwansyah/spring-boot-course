package com.jwt.examplejwt.payload;



import javax.validation.constraints.NotBlank;

public class JsonWebTokensResponse {

    @NotBlank(message = "access token must not be null")
    private String accessToken;
    private String tokenType = "Bearer";

    public JsonWebTokensResponse(String accessToken){
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
