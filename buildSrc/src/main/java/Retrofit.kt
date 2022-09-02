object Retrofit {
    private const val retrofitVersion = "2.9.0"
    private const val okHttpVersion = "4.9.1"
    private const val moshiVersion = "1.13.0"

    const val retrofit: String = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
    const val moshi = "com.squareup.moshi:moshi-kotlin:$moshiVersion"

    const val okHttp = "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"
}