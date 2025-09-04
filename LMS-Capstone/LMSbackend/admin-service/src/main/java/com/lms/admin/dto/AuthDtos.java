package com.lms.admin.dto;
public class AuthDtos {
    public static record RegisterReq(String name, String email, String phone, String password) {}
    public static record LoginReq(String email, String password) {}
    public static record TokenResp(String token) {}
}
