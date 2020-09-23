package com.makeus.urirang.android.src.login.social;

import android.content.Context;
import android.util.Log;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.auth.authorization.accesstoken.AccessToken;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.Profile;
import com.kakao.usermgmt.response.model.UserAccount;
import com.kakao.util.OptionalBoolean;
import com.kakao.util.exception.KakaoException;
import com.makeus.urirang.android.src.login.social.interfaces.SocialActivityView;

import static com.makeus.urirang.android.src.ApplicationClass.TAG;

public class SessionCallback implements ISessionCallback {

    private Context mContext;
    private SocialActivityView mSocialView;

    public SessionCallback(Context context, SocialActivityView view) {
        this.mContext = context;
        this.mSocialView = view;
    }

    //로그인 성공 상태
    @Override
    public void onSessionOpened() {
        requestMe();
    }

    @Override
    public void onSessionOpenFailed(KakaoException exception) {
        Log.d(TAG, "OnSessionOpenFailed: " + exception.getMessage());
    }

    public void requestMe() {
        UserManagement.getInstance().me(new MeV2ResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.d(TAG, "세션 닫혀 있음: " + errorResult);
            }

            @Override
            public void onFailure(ErrorResult errorResult) {
                Log.d(TAG, "사용자 정보 용청 실패: " + errorResult);
            }

            @Override
            public void onSuccess(MeV2Response result) {

                UserAccount kakaoAccount = result.getKakaoAccount();
                if (kakaoAccount != null) {

//                    Profile profile = kakaoAccount.getProfile();
//                    String nick = "";
//                    if (profile != null) {
////                        Log.d(TAG, "nick: " + profile.getNickname());
//                        nick = profile.getNickname();
//                    } else if (kakaoAccount.profileNeedsAgreement() == OptionalBoolean.TRUE) {
//                        //동의 요청 후 프로필 정보 획득 가능
//                    } else {
//                        //프로필 획득 불가
//                    }

                    AccessToken accessToken = Session.getCurrentSession().getTokenInfo();
                    Log.d(TAG, "access token: " + accessToken.getAccessToken());

                    final SocialService socialService = new SocialService(mSocialView, mContext, accessToken.getAccessToken());
                    socialService.tryGetIsMember();

                }
            }
        });
    }
}
