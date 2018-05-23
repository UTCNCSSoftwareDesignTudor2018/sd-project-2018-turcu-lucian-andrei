package com.utcn.medpat.dataAccess.service;

import com.utcn.medpat.dataAccess.dto.MessageDTO;
import com.utcn.medpat.model.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Lucian on 5/22/2018.
 */

public interface MessagingService {

    @GET("/getInbox")
    Call<List<Message>> getInbox(@Query("toId") String user);

    @POST("/sendMessage")
    Call<Void> sendMessage(@Body MessageDTO messageDTO);
}
