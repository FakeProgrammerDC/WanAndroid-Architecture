package com.dongchao.core.network.exception;

/**
 * @author chao.dong
 * 用于穷举http异常的类，包含code
 * 200：正确的请求返回正确的结果，如果不想细分正确的请求结果都可以直接返回200。
 * 201：表示资源被正确的创建。比如说，我们 POST 用户名、密码正确创建了一个用户就可以返回 201。
 * 202：请求是正确的，但是结果正在处理中，这时候客户端可以通过轮询等机制继续请求。
 * 203：请求的代理服务器修改了源服务器返回的 200 中的内容，我们通过代理服务器向服务器 A 请求用户信息，服务器 A 正常响应，
 * 但代理服务器命中了缓存并返回了自己的缓存内容，这时候它返回 203 告诉我们这部分信息不一定是最新的，我们可以自行判断并处理。
 * <p>
 * 300：请求成功，但结果有多种选择。
 * 301：请求成功，但是资源被永久转移。比如说，我们下载的东西不在这个地址需要去到新的地址。
 * 303：使用 GET 来访问新的地址来获取资源。
 * 304：请求的资源并没有被修改过。
 * 308：使用原有的地址请求方式来通过新地址获取资源。
 * <p>
 * 400：请求出现错误，比如请求头不对等。
 * 401：没有提供认证信息。请求的时候没有带上 Token 等。
 * 402：为以后需要所保留的状态码。
 * 403：请求的资源不允许访问。就是说没有权限。
 * 404：请求的内容不存在。
 * 406：请求的资源并不符合要求。
 * 408：客户端请求超时。
 * 413：请求体过大。
 * 415：类型不正确。
 * 416：请求的区间无效。
 * <p>
 * 500：服务器错误。
 * 501：请求还没有被实现。
 * 502：网关错误。
 * 503：服务暂时不可用。服务器正好在更新代码重启。
 * 505：请求的 HTTP 版本不支持。
 */
public class CommonCode {

    public static final int SUCCESS_200 = 200;
    public static final int SUCCESS_201 = 201;
    public static final int SUCCESS_202 = 202;
    public static final int SUCCESS_203 = 203;

//    public static boolean isSuccess(int code) {
//        return code == SUCCESS_200
//                || code == SUCCESS_201
//                || code == SUCCESS_202
//                || code == SUCCESS_203;
//    }

    public static final int REQ_ERR_400 = 400;
    public static final int REQ_ERR_401 = 401;
    public static final int REQ_ERR_402 = 402;
    public static final int REQ_ERR_403 = 403;
    public static final int REQ_ERR_404 = 404;
    public static final int REQ_ERR_406 = 406;
    public static final int REQ_ERR_408 = 408;
    public static final int REQ_ERR_413 = 413;
    public static final int REQ_ERR_415 = 415;
    public static final int REQ_ERR_416 = 416;


    public static final int SERVER_500 = 500;
    public static final int SERVER_501 = 501;
    public static final int SERVER_502 = 502;
    public static final int SERVER_503 = 503;
    public static final int SERVER_505 = 505;


    /**
     * Http异常，url异常
     */
    public static final int HTTP_URL_ERROR = 987;
    public static final String HTTP_URL_ERROR_MSG = "连接异常";

    /**
     * Http异常，打开连接异常
     */
    public static final int HTTP_CONNECTION_ERROR = 986;
    public static final String HTTP_CONNECTION_ERROR_MSG = "连接异常";
    /**
     * Http异常，未知链接
     */
    public static final int HTTP_UNKNOWN_HOST_ERROR = 985;
    public static final String HTTP_UNKNOWN_HOST_ERROR_MSG = "UNKNOWN HOST";
    /**
     * Http异常，超时
     */
    public static final int HTTP_TIMEOUT_ERROR = 984;
    public static final String HTTP_TIMEOUT_ERROR_MSG = "连接超时";

    /***
     * 网络问题
     * @param code
     * @return
     */
    public static boolean isNetErr(int code) {
        return code == HTTP_URL_ERROR
                || code == HTTP_CONNECTION_ERROR
                || code == HTTP_UNKNOWN_HOST_ERROR
                || code == HTTP_TIMEOUT_ERROR
                || code == REQ_ERR_400
                || code == REQ_ERR_401
                || code == REQ_ERR_402
                || code == REQ_ERR_403
                || code == REQ_ERR_404
                || code == REQ_ERR_406
                || code == REQ_ERR_408
                || code == REQ_ERR_413
                || code == REQ_ERR_415
                || code == REQ_ERR_416;
    }

    /**
     * 服务器错误，返回了空内容
     */
    public static final int SERVER_ERROR_EMPTY = 974;
    public static final String SERVER_ERROR_EMPTY_MSG = "Server empty";
    public static final String NET_ERROR_MSG = "net error";

    /***
     * 服务器问题
     * @param code
     * @return
     */
    public static boolean isServerErr(int code) {
        return code == SERVER_ERROR_EMPTY
                || code == SERVER_500
                || code == SERVER_501
                || code == SERVER_502
                || code == SERVER_503
                || code == SERVER_505;
    }

    public static boolean isJsonParseErr(int code) {
        return INNER_JSON_PARSE_ERROR == code;
    }

    /**
     * json转换异常
     */
    public static final int INNER_JSON_PARSE_ERROR = 979;
    public static final String INNER_JSON_PARSE_ERROR_MSG = "Json 转换错误";

    /**
     * 文件文找到异常，在OkHttpManager中上传文件会抛出
     */
    public static final int INNER_FILE_NOT_FOUND_ERROR = 978;
    /**
     * 非法参数异常，在OkHttpManager中会抛出
     */
    public static final int INNER_ILLEGAL_ARG_ERROR = 977;

    /**
     * 未知错误
     */
    public static final int UNKNOWN_ERROR = 976;
    public static final String UNKNOWN_ERROR_MSG = "未知错误";


    public static final int GLOBAL_HTTP_ERROR = 1000;
    public static final int GLOBAL_SERVER_ERR = 1001;


    public static final String HTTP_ERROR_MSG = "请检查您的网络连接";
    public static final String HTTP_SERVER_ERROR_MSG = "服务器异常,稍后再说";
    public static final String INNER_ERROR_MSG = "内部错误";

}
