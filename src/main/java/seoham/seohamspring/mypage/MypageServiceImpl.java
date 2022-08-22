package seoham.seohamspring.mypage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seoham.seohamspring.config.BaseException;
import seoham.seohamspring.mypage.domain.*;
import seoham.seohamspring.config.BaseException;
import seoham.seohamspring.util.SHA256;

import static seoham.seohamspring.config.BaseResponseStatus.*;

@Service
public class MypageServiceImpl implements MypageService{

    private final MypageRepository mypageRepository;

    @Autowired
    public MypageServiceImpl(MypageRepository mypageRepository) {
        this.mypageRepository = mypageRepository;
    }


    // 닉네임 중복검사
    // 완료
    @Override
    public PostCheckValidRes chekcNickname(PostCheckNicknameReq postCheckNicknameReq) throws BaseException{
        try {
            int check = mypageRepository.checkNickname(postCheckNicknameReq);

            if (check == 0) {
                return new PostCheckValidRes(true);
            } else {
                return new PostCheckValidRes(false);
            }

        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


    // 패스워드 확인
    @Override
    public PostCheckValidRes checkPassword(PostCheckPasswordReq postCheckPasswordReq, int userIdx) throws BaseException{
        String pwd;
        try {

            pwd = SHA256.encrypt(postCheckPasswordReq.getPassword());
            postCheckPasswordReq.setPassword(pwd);

            int check = mypageRepository.checkPassword(postCheckPasswordReq, userIdx);

            if (check == 1) {
                return new PostCheckValidRes(true);
            } else {
                return new PostCheckValidRes(false);
            }
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


    @Override
    public String modifyNickname(PatchNicknameReq patchNicknameReq, int userIdx) throws BaseException{
        int result;
        try {
            result = mypageRepository.modifyNickname(patchNicknameReq, userIdx);
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }

        if (result == 0) {
            throw new BaseException(FAIL_MODIFY_NICKNAME);
        }

        return "닉네임을 성공적으로 수정하였습니다.";

    }

    @Override
    public String modifyPassword(PatchPasswordReq patchPasswordReq, int userIdx) throws BaseException{
        int result;
        String pwd;
        try {
            pwd = SHA256.encrypt(patchPasswordReq.getNewPassword());
            patchPasswordReq.setNewPassword(pwd);
            result = mypageRepository.modifyPassword(patchPasswordReq, userIdx);
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }

        if (result == 0) {
            throw new BaseException(FAIL_MODIFY_PASSWORD);
        }

        return "비밀번호를 성공적으로 수정하였습니다";
    }

    @Override
    public String deleteUser(int userIdx) throws BaseException{
        int result = 0;
        try {
            result = mypageRepository.deleteUser(userIdx);
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }

        if (result == 1) {
            throw new BaseException(FAIL_DELETE_USER);
        }

        return "유저 정보가 삭제되었습니다";
    }

    @Override
    public GetCountInfoRes userInfo(int userIdx) throws BaseException {
        try {
            return mypageRepository.userInfo(userIdx);
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }

    }


}

