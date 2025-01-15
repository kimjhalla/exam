CREATE TABLE exam.COMMENT (
                              seq BIGINT AUTO_INCREMENT COMMENT '댓글 시퀀스'
        PRIMARY KEY,
                              postSeq BIGINT NOT NULL COMMENT '게시글 시퀀스',
                              userSeq BIGINT NOT NULL COMMENT '댓글 작성자 시퀀스',
                              content VARCHAR(500) NOT NULL COMMENT '댓글 내용',
                              registerDate DATETIME(6) NOT NULL COMMENT '댓글 등록 일시',
                              updateDate DATETIME(6) NOT NULL COMMENT '댓글 수정 일시'
) COMMENT='게시글 댓글 정보를 저장하는 테이블';

CREATE TABLE exam.POST (
                           seq BIGINT AUTO_INCREMENT COMMENT '게시글 시퀀스'
        PRIMARY KEY,
                           userSeq BIGINT NOT NULL COMMENT '게시글 작성자 시퀀스',
                           title VARCHAR(200) NOT NULL COMMENT '게시글 제목',
                           content VARCHAR(4000) NOT NULL COMMENT '게시글 내용',
                           registerDate DATETIME(6) NOT NULL COMMENT '게시글 등록 일시',
                           updateDate DATETIME(6) NOT NULL COMMENT '게시글 수정 일시'
) COMMENT='게시글 정보를 저장하는 테이블';

CREATE TABLE exam.POST_ATTACH_FILE (
                                       seq BIGINT AUTO_INCREMENT COMMENT '첨부 파일 시퀀스'
        PRIMARY KEY,
                                       postSeq BIGINT NOT NULL COMMENT '게시글 시퀀스',
                                       fileName VARCHAR(500) NOT NULL COMMENT '첨부 파일 이름',
                                       filePath VARCHAR(1000) NOT NULL COMMENT '첨부 파일 경로',
                                       registerDate DATETIME(6) NOT NULL COMMENT '첨부 파일 등록 일시'
) COMMENT='게시글의 첨부 파일 정보를 저장하는 테이블';

CREATE TABLE exam.USER (
                           seq BIGINT AUTO_INCREMENT COMMENT '사용자 시퀀스'
        PRIMARY KEY,
                           email VARCHAR(100) NOT NULL COMMENT '사용자 이메일 (고유값)',
                           name VARCHAR(40) NOT NULL COMMENT '사용자 이름',
                           nickname VARCHAR(100) NOT NULL COMMENT '사용자 닉네임',
                           password VARCHAR(100) NOT NULL COMMENT '사용자 비밀번호',
                           registerDate DATETIME(6) NOT NULL COMMENT '사용자 등록 일시',
                           updateDate DATETIME(6) NOT NULL COMMENT '사용자 수정 일시',
                           CONSTRAINT USER_pk UNIQUE (email)
) COMMENT='사용자 정보를 저장하는 테이블';

CREATE TABLE exam.USER_ROLE (
                                seq BIGINT AUTO_INCREMENT COMMENT '사용자 권한 시퀀스'
        PRIMARY KEY,
                                userSeq BIGINT NOT NULL COMMENT '사용자 시퀀스',
                                roleCode VARCHAR(20) NOT NULL COMMENT '사용자 권한 코드(POST_USER,COMMENT_USER) 동일 사용자 복수 권한 설정 가능'
) COMMENT='사용자 역할 정보를 저장하는 테이블';

