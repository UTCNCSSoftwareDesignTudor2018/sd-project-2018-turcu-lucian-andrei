package com.utcn.medpat.dataAccess.service;

import com.utcn.medpat.dataAccess.dto.LoginCredentials;
import com.utcn.medpat.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Lucian on 5/20/2018.
 */

public interface LoginService {

    @POST("/login")
    Call<User> login(@Body LoginCredentials loginCredentials);
}

