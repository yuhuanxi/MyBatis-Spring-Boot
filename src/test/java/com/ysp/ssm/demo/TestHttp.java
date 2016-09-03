package com.ysp.ssm.demo;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class TestHttp {

    private static final Logger LOG = LogManager.getLogger(TestHttp.class);

    static final String TEST_SERVER_URL = "http://115.29.226.157:9090";

    static final String ADMIN_SERVER_URL = "http://123.59.64.180";

    public static void main(String[] args) throws IOException, URISyntaxException {
        asyncTest();
        System.out.println("helloworld");
    }

    /**
     * 查看举报详情
     *
     * @throws IOException
     */
    private static void listTipOffs() throws IOException {

        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder
                .baseUrl(ADMIN_SERVER_URL)
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
                    LOG.info("result:{}", result);
                }
            }
        }
    }

    /**
     * 添加举报
     *
     * @throws IOException
     */
    private static void addTipOff() throws IOException {
        final String TEST_SERVER_URL = "http://115.29.226.157:9090";
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder
                .baseUrl(TEST_SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ITipOffService service = retrofit.create(ITipOffService.class);
        Call<ReturnResult> resultCall = service.addTipOff(12345L, 14456L, 39685L, null, 2, "色情", "");
        LOG.info("body:{}", resultCall.execute().body());
    }

    /**
     * 测试异步请求
     */
    private static void asyncTest() {

        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder
                .baseUrl(TEST_SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ITipOffService service = retrofit.create(ITipOffService.class);
        Call<ReturnResult> resultCall = service.addTipOff(null, 14456L, 39685L, null, 2, "色情", "");
        resultCall.enqueue(new Callback<ReturnResult>() {
            @Override
            public void onResponse(Call<ReturnResult> call, Response<ReturnResult> response) {
                if (response.code() == 200) {
                    ReturnResult body = response.body();
                    if (body != null) {
                        LOG.info("body:{}", body);
                        sayHello();
                    }
                }
            }

            @Override
            public void onFailure(Call<ReturnResult> call, Throwable throwable) {
                LOG.info("on failure");
            }
        });
    }

    private static void sayHello() {
        System.out.println("hello world");
    }
}
