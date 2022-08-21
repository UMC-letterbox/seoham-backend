package seoham.seohamspring.config;

import lombok.Getter;
/**
 * 에러 코드 관리
 */
@Getter



public enum BaseResponseStatus {

    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),


    /**
     * 2000 : Request 오류
     */
    // Common

    CREATE_USER_EMPTY_EMAIL(false, 2000, "이메일을 입력해주세요."),
    CREATE_USER_EMPTY_NICKNAME(false, 2001, "닉네임을 입력해주세요."),
    CREATE_USER_EMPTY_PASSWORD(false, 2002, "비밀번호를 입력해주세요."),
    CREATE_USER_INVALID_EMAIL(false, 2003, "이메일 형식을 확인해주세요."),


    CHECK_USER_EMPTY_EMAIL(false, 2010, "이메일을 입력해주세요."),
    CHECK_USER_INVALID_EMAIL(false, 2011, "이메일 형식을 확인해주세요."),

    CHECK_USER_EMPTY_NICKNAME(false, 2020, "닉네임을 입력해주세요."),
    CHECK_USER_INVALID_NICKNAME(false, 2021, "닉네임 형식을 확인해주세요."),



    FIND_USER_EMPTY_NICKNAME(false, 2030, "닉네임을 입력해주세요."),
    FIND_USER_INVALID_NICKNAME(false, 2031, "닉네임 형식을 확인해주세요."),
    FIND_USER_NO_NICKNAME(false, 2032, "해당하는 닉네임이 없습니다."),







    FIND_USER_EMPTY_EMAIL(false, 2040, "이메일을 입력해주세요."),
    FIND_USER_INVALID_EMAIL(false, 2041, "이메일 형식을 확인해주세요."),
    FIND_USER_EMPTY_PASSWORD(false, 2042, "비밀번호를 입력해주세요."),
    FIND_USER_NO_EMAIL(false, 2042, "해당하는 이메일이 없습니다."),










    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),

    // users

    USERS_EMPTY_USER_ID(false, 2010, "유저 아이디 값을 확인해주세요."),

    // [POST] /users
    POST_USERS_EMPTY_EMAIL(false, 2015, "이메일을 입력해주세요."),

    POST_USERS_EXISTS_EMAIL(false,2017,"중복된 이메일입니다."),

    //posts
    POST_POSTS_INVALID_CONTENT(false, 2018, "내용의 글자수를 확인해주세요"),
    POST_TAGS_INVALID_CONTENT(false,2019,"태그의 글자수를 확인해주세요"),
    POST_TAGS_EXIST(false,2020,"이미 존재하는 태그 입니다."),
    POST_EMPTY_POST_IDX(false, 2021,"게시물 아이디 값을 확인해주세요."),
    POST_EMPTY_TAG_IDX(false,2022,"태그 아이디 값을 확인해주세요."),
    POST_SENDER_INVALID_CONTENT(false, 2023,"보낸이 글자수를 확인해주세요"),
    POST_EMPTY_SENDER(false,2024,"보낸이 아이디 값을 확인해주세요."),

    //mypage
    POST_MYPAGE_EMPTY_NICKNAME(false, 2030, "닉네임을 입력해주세요"),
    POST_MYPAGE_EMPTY_PASSWORD(false, 2031, "패스워드를 입력해주세요"),

    PATCH_MYPAGE_EMPTY_NICKNAME(false, 2032, "수정할 닉네임을 입력해주세요"),
    PATCH_MYPAGE_EMPTY_PASSWORD(false, 2033, "수정할 패스워드를 입력해주세요"),



    /**
     * 3000 : Response 오류
     */
    // Common
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),

    // [POST] /users
    DUPLICATED_EMAIL(false, 3013, "중복된 이메일입니다."),
    FAILED_TO_LOGIN(false,3014,"없는 아이디거나 비밀번호가 틀렸습니다."),


    // posts
    MODIFY_FAIL_POST(false, 3020, "편지 수정을 실패하였습니다."),
    DELETE_FAIL_POST( false, 3021, "편지 삭제를 실패하였습니다. "),
    MODIFY_FAIL_TAG(false,3022,"태그 정보 수정을 실패하였습니다."),
    DELETE_FAIL_TAG(false,3023,"태그 정보 삭제를 실패하였습니다."),
    MODIFY_FAIL_SENDER(false, 3024,"보낸이 정보 수정을 실패하였습니다."),
    DELETE_FAIL_SENDER(false,3025,"보낸이 정보 삭제를 실패하였습니다."),
    SELECT_FAIL_TAG_LIST(false,3026,"태그 목록이 존재하지 않습니다."),

    //mypage
    FAIL_MODIFY_NICKNAME(false, 3030, "닉네임 수정에 실패했습니다"),
    FAIL_MODIFY_PASSWORD(false, 3031, "비밀번호 수정에 실패했습니다"),
    FAIL_DELETE_USER(false, 3032, "유저 삭제에 실패했습니다"),



    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),

    //[PATCH] /users/{userIdx}
    MODIFY_FAIL_USERNAME(false,4014,"유저네임 수정 실패"),



    PASSWORD_ENCRYPTION_ERROR(false, 4011, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 4012, "비밀번호 복호화에 실패하였습니다."),

    INVALID_JWT(false,5000,"jwt 실패"),
    EMPTY_JWT(false,5001 ,"jwt 비어있습니다." );




    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
