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

    USER_EMPTY_EMAIL(false, 2000, "유저 이메일을 입력해주세요."),
    USER_EMPTY_NICKNAME(false, 2001, "유저 닉네임을 입력해주세요."),
    USER_EMPTY_PASSWORD(false, 2002, "유저 비밀번호를 입력해주세요."),

    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),

    // users

    USERS_EMPTY_USER_ID(false, 2010, "유저 아이디 값을 확인해주세요."),

    // [POST] /users
    POST_USERS_EMPTY_EMAIL(false, 2015, "이메일을 입력해주세요."),
    POST_USERS_INVALID_EMAIL(false, 2016, "이메일 형식을 확인해주세요."),
    POST_USERS_EXISTS_EMAIL(false,2017,"중복된 이메일입니다."),

    POST_POSTS_INVALID_CONTENT(false, 2018, "내용의 글자수를 확인해주세요"),



    /**
     * 3000 : Response 오류
     */
    // Common
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),

    // [POST] /users
    DUPLICATED_EMAIL(false, 3013, "중복된 이메일입니다."),
    FAILED_TO_LOGIN(false,3014,"없는 아이디거나 비밀번호가 틀렸습니다."),


    MODIFY_FAIL_POST(false, 3020, "편지 수정을 실패하였습니다."),
    DELETE_FAIL_POST( false, 3021, "편지 삭제를 실패하였습니다. "),




    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),

    //[PATCH] /users/{userIdx}
    MODIFY_FAIL_USERNAME(false,4014,"유저네임 수정 실패"),

    DELETE_FAIL_USERIDX(false,4015,"유저 삭제 실패"),   //잠깐 쓰고 삭제할 code
    INVAILD_USERIDX(false,4016,"유저 인덱스를 확인 해 주세요"),   //잠깐 쓰고 삭제할 code

    PASSWORD_ENCRYPTION_ERROR(false, 4011, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 4012, "비밀번호 복호화에 실패하였습니다.");



    // 5000 : 필요시 만들어서 쓰세요
    // 6000 : 필요시 만들어서 쓰세요


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}