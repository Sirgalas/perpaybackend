package ru.sergalas.perpay.security.constants;

public class SecurityConstants {

    public static final String SIGN_UP_URLS = "/api/auth/**";

    public static final String SECRET = "SecretKeyJWT";

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING ="Authorization";

    public static final String CONTENT_TYPE ="application/json";

    public static final Long  EXPIRATION_TIME = 3600_000L;

}
