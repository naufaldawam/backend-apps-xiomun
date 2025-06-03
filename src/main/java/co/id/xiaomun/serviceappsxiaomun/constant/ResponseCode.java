package co.id.xiaomun.serviceappsxiaomun.constant;


public class ResponseCode {
    // mapping data message

    // message public english
    public static final String SUCCESS_2xx = "success";             // 200: Request berhasil
    public static final String CREATED_2xx = "created";             // 201: Resource berhasil dibuat
    public static final String NO_CONTENT_2xx = "no content";       // 204: Request berhasil tanpa isi (biasanya untuk DELETE)

    public static final String BAD_REQUEST_4xx = "bad request";                     // 400: Format request salah atau tidak valid
    public static final String UNAUTHORIZED_4xx = "unauthorized";                  // 401: Tidak terautentikasi (belum login atau token salah)
    public static final String FORBIDDEN_4xx = "forbidden";                        // 403: Dilarang mengakses (tidak punya izin)
    public static final String NOT_FOUND_4xx = "not found";                        // 404: Resource tidak ditemukan
    public static final String CONFLICT_4xx = "conflict";                          // 409: Konflik data (misalnya saat insert/update)
    public static final String UNPROCESSABLE_ENTITY_4xx = "unprocessable entity";  // 422: Data valid secara format tapi gagal diproses (biasanya validasi bisnis)

    public static final String INTERNAL_SERVER_ERROR_5xx = "internal server error";  // 500: Kesalahan umum di server
    public static final String BAD_GATEWAY_5xx = "bad gateway";                      // 502: Server menerima respons tidak valid dari server lain
    public static final String SERVICE_UNAVAILABLE_5xx = "service unavailable";      // 503: Server sedang tidak tersedia (misalnya maintenance)
    public static final String GATEWAY_TIMEOUT_5xx = "gateway timeout";              // 504: Server tidak mendapat respons tepat waktu dari server lain

    // response code public
    public static final String RC_SUCCESS_2xx = "200";
    public static final String RC_CREATED_2xx = "201";
    public static final String RC_NO_CONTENT_2xx = "204";

    public static final String RC_BAD_REQUEST_4xx = "400";
    public static final String RC_UNAUTHORIZED_4xx = "401";
    public static final String RC_FORBIDDEN_4xx = "403";
    public static final String RC_NOT_FOUND_4xx = "404";
    public static final String RC_CONFLICT_4xx = "409";
    public static final String RC_UNPROCESSABLE_ENTITY_4xx = "422";

    public static final String RC_INTERNAL_SERVER_ERROR_5xx = "500";
    public static final String RC_BAD_GATEWAY_5xx = "502";
    public static final String RC_SERVICE_UNAVAILABLE_5xx = "503";
    public static final String RC_GATEWAY_TIMEOUT_5xx = "504";
    
}

// return new ResponseEntity<>(
//     new ApiResponse<>(ResponseCode.SUCCESS_2xx, ResponseMessage.SUCCESS_2xx, data),
//     HttpStatus.OK
// );
