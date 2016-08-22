package com.ysp.ssm.demo;

import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by yuhuanxi on 16/8/22.
 */
public interface ITipOffService {

    /**
     * 返回的是一个对象
     *
     * @param curPage
     * @param pageSize
     * @return
     */
    @Headers("Accept: Application/JSON")
    @GET("/api/v2/tipoff/list")
    Call<TipOff> listTipOffs(@Query("curPage") Integer curPage, @Query("pageSize") Integer pageSize);

    /**
     * 获取连载详情
     *
     * 这里的 id 可以使用 @Path 动态添加
     */
//    @GET("group/{id}/users")
//    Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort);

    /**
     * 举报接口
     * <p>
     * 用于Form表单中
     */
    @Multipart
    @POST("/lianzai/TipOffCtrl/tipOff")
    Call<ReturnResult> addTipOff(
            @Part("uid") Long uid,
            @Part("planId") Long planId,
            @Part("stageId") Long stageId,
            @Part("commentId") Long commentId,
            @Part("type") Integer type,
            @Part("reason") String reason,
            @Part("uidSid") String uidSid);

//    @POST("/lianzai/TipOffCtrl/tipOff")
//    Call<ReturnResult> addTipOff(
//            @Body Long uid,
//            @Body Long planId,
//            @Body Long stageId,
//            @Body Long commentId,
//            @Body Integer type,
//            @Body String reason,
//            @Body String uidSid);
}
