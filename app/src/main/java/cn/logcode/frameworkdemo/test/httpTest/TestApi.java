package cn.logcode.frameworkdemo.test.httpTest;


import cn.logcode.library.http.BaseEntity;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by CaostGrace on 2018/5/29 13:22
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.library.http
 * @class_name: TestApi
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public interface TestApi {
    @FormUrlEncoded
    @POST("openapi/api")
    Observable<Bean> defaultTest(@Field("key") String key,@Field("info") String info);

    @FormUrlEncoded
    @POST("openapi/api")
    Observable<BaseEntity<Bean>> baseEntityTest(@Field("key") String key, @Field("info") String info);

}
