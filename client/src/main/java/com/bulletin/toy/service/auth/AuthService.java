package com.bulletin.toy.service.auth;

public interface AuthService {

    String getResourceWithToken(String authCode, String userId);

}
