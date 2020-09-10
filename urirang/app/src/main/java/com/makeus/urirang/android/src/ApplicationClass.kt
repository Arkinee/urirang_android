package com.makeus.urirang.android.src

import android.app.Application
import android.content.SharedPreferences
import com.kakao.auth.*
import com.makeus.urirang.android.config.XAccessTokenInterceptor
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        if (sSharedPreferences == null) {
            sSharedPreferences = applicationContext.getSharedPreferences(TAG, MODE_PRIVATE)
        }

//        KakaoSDK.init(new KakaoSDKAdapter());
    }

    override fun onTerminate() {
        super.onTerminate()
        instance = null
    }

    inner class KakaoSDKAdapter : KakaoAdapter() {
        override fun getApplicationConfig(): IApplicationConfig {
            return IApplicationConfig { applicationClassContext!! }
        }

        override fun getSessionConfig(): ISessionConfig {
            return object : ISessionConfig {
                override fun getAuthTypes(): Array<AuthType> {
                    return arrayOf(AuthType.KAKAO_TALK)
                }

                override fun isUsingWebviewTimer(): Boolean {
                    return false
                }

                override fun isSecureMode(): Boolean {
                    return false
                }

                override fun getApprovalType(): ApprovalType? {
                    return ApprovalType.INDIVIDUAL
                }

                override fun isSaveFormData(): Boolean {
                    return true
                }
            }
        }
    }

    companion object {
        //서버 주소
        var BASE_URL = "http://49.50.174.243"

        //공용 shared preference
        @JvmField
        var sSharedPreferences: SharedPreferences? = null

        // 가격 포멧
        var myFormatter = DecimalFormat("###,###")

        // SharedPreferences 키 값
        var TAG = "revelyview"

        // JWT Token 키 값
        const val X_ACCESS_TOKEN = "X-ACCESS-TOKEN"

        // FCM Token 키 값
        const val FCM_TOKEN = "OMO_FCM_TOKEN"

        //날짜 형식
        var DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
        var HOME_MONTH = SimpleDateFormat("MM", Locale.KOREA)
        var HOME_DAY = SimpleDateFormat("dd", Locale.KOREA)

        // Retrofit 인스턴스
        var retrofit: Retrofit? = null
        var retrofitForKakao: Retrofit? = null
        private var instance: ApplicationClass? = null
        val applicationClassContext: ApplicationClass?
            get() {
                checkNotNull(instance) { "This Application does not inherit com.kakao.GlobalApplication" }
                return instance
            }

        fun getRetrofit(): Retrofit? {
            if (retrofit == null) {
                val client = OkHttpClient.Builder()
                        .readTimeout(5000, TimeUnit.MILLISECONDS)
                        .connectTimeout(5000, TimeUnit.MILLISECONDS)
                        .addNetworkInterceptor(XAccessTokenInterceptor()) // JWT 자동 헤더 전송
                        .build()
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit
        }

        fun getRetrofitForKakao(): Retrofit? {
            if (retrofitForKakao == null) {
                val client = OkHttpClient.Builder()
                        .readTimeout(5000, TimeUnit.MILLISECONDS)
                        .connectTimeout(5000, TimeUnit.MILLISECONDS)
                        .build()
                retrofitForKakao = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofitForKakao
        }
    }
}