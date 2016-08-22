package com.ysp.ssm.demo;

import org.apache.commons.collections4.CollectionUtils;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by yuhuanxi on 16/8/22.
 */
public class TestHttp {

    public static void main(String[] args) throws IOException, URISyntaxException {

        final String ADMIN_SERVER_URL = "http://123.59.64.180";
        final String TEST_SERVER_URL = "http://115.29.226.157:9090";
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder
                .baseUrl(TEST_SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ITipOffService service = retrofit.create(ITipOffService.class);
        Call<TipOff> listRepos = service.listTipOffs(1, 10);
        Response<TipOff> execute = listRepos.execute();
        TipOff body = execute.body();
        if (body != null) {
            List<TipOff.ResultsBean> results = body.getResults();

            if (CollectionUtils.isNotEmpty(results)) {
                for (TipOff.ResultsBean result : results) {
                    System.out.println(result);
                }
            }
        }

        Call<ReturnResult> resultCall = service.addTipOff(12345L, 14456L, 39685L, null, 2, "色情", "");
        System.out.println(resultCall.execute().body());
    }

}
