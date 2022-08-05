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
    public int chekcEmail(String email) {
        return mypageRepository.chekcEmail(email);
    }

    @Override
    public int checkPassword(String password) {
        return mypageRepository.checkPassword(password);

    }

    @Override
    public String modifyNickname(PatchNicknameReq patchNicknameReq, int userIdx) {
        return mypageRepository.modifyNickname(patchNicknameReq, userIdx);

    }

    @Override
    public String modifyPassword(PatchPasswordReq patchPasswordReq, int userIdx) {
        return mypageRepository.modifyPassword(patchPasswordReq, userIdx);
    }

    @Override
    public String deleteUser(DeleteUserReq deleteUserReq, int userIdx) {
        return mypageRepository.deleteUser(deleteUserReq, userIdx);
    }
}
