package com.travel.travel.config.security;

public class ConstansSecurity {

    //Spring Secutiry
    public static final String LOGIN_URL = "/auth/log_in";
    public static final String SIGNIN_URL = "/auth/sign_in";
    public static final String DESTINATIONS_URL = "/auth/destinations/location";
    public static final String DESTINATIONS_ADD_URL = "/api/destinations/add";
    public static final String DESTINATIONS_UPDATE_URL = "/api/destinations/update/{id}";
    public static final String DESTINATIONS_LOCATION_URL = "/api/destinations/location";
    public static final String DESTINATIONS_DELETE_URL = "/api/destinations/delete/{id}";
    public static final String DESTINATIONS_DETAILS_URL = "/api/destinations/details/{id}";
    public static final String LOCALHOST_FRONT_URL = "http://localhost:5173";
    public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
    public static final String TOKEN_BEARER_PREFIX = "Bearer ";

    // JWT 
    public static final String SECRET_KEY = "ZnJhc2VzbGFyZ2FzcGFyYWNvbG9jYXJjb21vY2xhdmVlbnVucHJvamVjdG9kZWVtZXBsb3BhcmFqd3Rjb25zcHJpbmdzZWN1cml0eQ==bWlwcnVlYmFkZWVqbXBsb3BhcmFiYXNlNjQ=";
    public static final long TOKEN_EXPIRATION_TIME = 864_000_000;

}
