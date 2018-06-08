package cn.logcode.library.http;


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
    @POST("action/api")
    Observable<BaseEntity<String>> test(@Field("token") String token);
}
