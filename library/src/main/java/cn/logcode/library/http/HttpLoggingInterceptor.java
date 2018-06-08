package cn.logcode.library.http;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;

import cn.logcode.library.Log.LogUtils;
import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Created by CaostGrace on 2018/5/29 14:57
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.library.http
 * @class_name: HttpLoggingInterceptor
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public class HttpLoggingInterceptor implements Interceptor {
    private static final String LOG_TAG = "OkHttp";
    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        /*
            打印开始开始请求
         */
        RequestBody requestBody = request.body();
        boolean hasRequestBody = requestBody != null;

        Connection connection = chain.connection();
        Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
        String requestMessage = request.method() + ' ' + request.url() + ' ' + protocol;
        String requestStartMessage = "--> " + requestMessage;
        if (hasRequestBody) {
            requestStartMessage += " (" + requestBody.contentLength() + "-byte body)";
        }
        LogUtils.d(LOG_TAG, "start sending Request: " + requestStartMessage);


        /*
            请求中,计算请求时间并打印
         */
        long startNs = System.nanoTime();
        Response response;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            LogUtils.d(LOG_TAG, "<-- HTTP FAILED: " + e);
            throw e;
        }
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        /*
            请求完后打印RequestBody
         */
        LogUtils.printDivider(LOG_TAG);
        LogUtils.d(LOG_TAG, requestStartMessage);

        if (hasRequestBody) {
            // Request body headers are only present when installed as a network interceptor. Force
            // them to be included (when available) so there values are known.
            if (requestBody.contentType() != null) {
                LogUtils.d(LOG_TAG, "Content-Type: " + requestBody.contentType());
            }
            if (requestBody.contentLength() != -1) {
                LogUtils.d(LOG_TAG, "Content-Length: " + requestBody.contentLength());
            }
        }

        Headers headers = request.headers();
        for (int i = 0, count = headers.size(); i < count; i++) {
            String name = headers.name(i);
            // Skip headers from the request body as they are explicitly logged above.
            if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name))
            {
                LogUtils.d(LOG_TAG, name + ": " + headers.value(i));
            }
        }

        if (bodyEncoded(request.headers())) {
            LogUtils.d(LOG_TAG, "--> END " + request.method() +
                    " (encoded body omitted)");
        } else {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);

            Charset charset = UTF8;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }

            if (isPlaintext(buffer)) {
                LogUtils.d(LOG_TAG, buffer.readString(charset));
                LogUtils.d(LOG_TAG, "--> END " + request.method() +
                        " (" + requestBody.contentLength() + "-byte body)");
            } else {
                LogUtils.d(LOG_TAG, "--> END "
                        + request.method()
                        + " (binary "
                        + requestBody.contentLength()
                        + "-byte body omitted)");
            }
        }


        /*
            请求完后打印ResponseBody
         */
        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
        LogUtils.d(LOG_TAG, "<-- " + response.code() + ' ' + response.message() +
                ' ' + response.request()
                .url() + " (" + tookMs + "ms" + ", " + bodySize +" body" + ')');


        headers = response.headers();
        for (int i = 0, count = headers.size(); i < count; i++) {
            LogUtils.d(LOG_TAG,headers.name(i) + ": " + headers.value(i));
        }

        if (!HttpHeaders.hasBody(response)) {
            LogUtils.d(LOG_TAG,"<-- END HTTP");
        } else if (bodyEncoded(response.headers())) {
            LogUtils.d(LOG_TAG,"<-- END HTTP (encoded body omitted)");
        } else {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(UTF8);
                } catch (UnsupportedCharsetException e) {

                    LogUtils.d(LOG_TAG,"Couldn't decode the response body; " +
                            "charset is likely malformed.");
                    LogUtils.d(LOG_TAG,"<-- END HTTP");

                    return response;
                }
            }

            if (!isPlaintext(buffer)) {

                LogUtils.d(LOG_TAG,"<-- END HTTP (binary " + buffer.size() +
                        "-byte body omitted)");
                return response;
            }

            if (contentLength != 0) {

                LogUtils.d(LOG_TAG,buffer.clone().readString(charset));
            }

            LogUtils.d(LOG_TAG,"<-- END HTTP: " + requestMessage +
                    " (" + buffer.size() + "-byte body)");
        }

        LogUtils.printDivider(LOG_TAG);


        return response;

    }

    /**
     * Returns true if the body in question probably contains human readable text. Uses a small sample
     * of code points to detect unicode control characters commonly used in binary file signatures.
     */
    static boolean isPlaintext(Buffer buffer) throws EOFException {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }
}
