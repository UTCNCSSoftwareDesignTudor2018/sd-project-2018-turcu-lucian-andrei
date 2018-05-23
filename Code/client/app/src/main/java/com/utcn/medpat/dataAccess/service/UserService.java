package com.utcn.medpat.dataAccess.service;

import com.utcn.medpat.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Lucian on 5/23/2018.
 */

public interface UserService {
    @GET("/getUsers")
    Call<List<User>> getUsers();
}
