package seoham.seohamspring.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import seoham.seohamspring.mypage.domain.DeleteUserReq;
import seoham.seohamspring.mypage.domain.PatchNicknameReq;
import seoham.seohamspring.mypage.domain.PatchPasswordReq;

public class MypageServiceImpl implements MypageService{

    private final MypageRepository mypageRepository;

    @Autowired
    public MypageServiceImpl(MypageRepository mypageRepository) {
        this.mypageRepository = mypageRepository;
    }


    @Override
    public int chekcEmail(String email) throws BaseException{
        int result;
        try {
            return mypageRepository.checkEmail(email);
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Override
    public int checkPassword(String password, int userIdx) throws BaseException{
        try {
            return mypageRepository.checkPassword(password, userIdx);
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
        int result;
        try {
            mypageRepository.deleteUser(userIdx);
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }

        if (result == 1) {
            throw new BaseException(FAIL_DELETE_USER);
        }

        return "유저 정보가 삭제되었습니다";
    }
}
