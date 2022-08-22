package seoham.seohamspring.mypage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seoham.seohamspring.config.BaseException;
import seoham.seohamspring.mypage.domain.*;
import seoham.seohamspring.config.BaseException;

import static seoham.seohamspring.config.BaseResponseStatus.*;

@Service
public class MypageServiceImpl implements MypageService{

    private final MypageRepository mypageRepository;

    @Autowired
    public MypageServiceImpl(MypageRepository mypageRepository) {
        this.mypageRepository = mypageRepository;
    }


    @Override
    public PostCheckValidRes chekcNickname(PostCheckNicknameReq postCheckNicknameReq) throws BaseException{
        int result;
        try {
            PostCheckValidRes postCheckValidRes = null;


            if (mypageRepository.checkNickname(postCheckNicknameReq) == 0) {
                postCheckValidRes.setValid(true);
            } else {
                postCheckValidRes.setValid(false);
            }

            return postCheckValidRes;
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Override
    public PostCheckValidRes checkPassword(PostCheckPasswordReq postCheckPasswordReq, int userIdx) throws BaseException{
        try {
            PostCheckValidRes postCheckValidRes = null;

            if (mypageRepository.checkPassword(postCheckPasswordReq, userIdx) == 0) {
                postCheckValidRes.setValid(true);
            } else {
                postCheckValidRes.setValid(false);
            }
            return postCheckValidRes;
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
        try {
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
}

