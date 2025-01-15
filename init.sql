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

-- 유저 정보 seq = 1은 게시글,답글 관리 가능 2는 게시글 관리 및 답글 목록 조회 가능
INSERT INTO exam.USER (seq, email, name, nickname, password, registerDate, updateDate) VALUES (1, 'hong@abcd.com', '홍길동', '길동이', '$2a$10$DZp4WDrPrd2DtSLE7QhddOOf0HnFpw7GmolV6WAXMRIXLFGmcm0Zu', '2025-01-15 12:59:06.593248', '2025-01-15 12:59:06.593248');
INSERT INTO exam.USER (seq, email, name, nickname, password, registerDate, updateDate) VALUES (2, 'hong1@abcd.com', '홍길동이', '길동이둘', '$2a$10$pIZEV8YdtRZKqlL/4TD26uZlyq7UC3GQXJUvc.QdNfP4TeHKrbERq', '2025-01-15 12:59:35.426655', '2025-01-15 12:59:35.426655');

INSERT INTO exam.USER_ROLE (seq, userSeq, roleCode) VALUES (1, 1, 'POST_USER');
INSERT INTO exam.USER_ROLE (seq, userSeq, roleCode) VALUES (2, 1, 'COMMENT_USER');
INSERT INTO exam.USER_ROLE (seq, userSeq, roleCode) VALUES (3, 2, 'POST_USER');

INSERT INTO exam.POST (seq, userSeq, title, content, registerDate, updateDate) VALUES (1, 1, '변경제목1', '변경내용1', '2025-01-15 13:14:22.225299', '2025-01-15 13:16:17.088821');
INSERT INTO exam.POST (seq, userSeq, title, content, registerDate, updateDate) VALUES (2, 1, '게시글2', '내용2', '2025-01-15 13:14:52.237982', '2025-01-15 13:14:52.237982');

INSERT INTO exam.POST_ATTACH_FILE (seq, postSeq, fileName, filePath, registerDate) VALUES (2, 2, '파일명1.pdf', 'cdn경로/파일명1.pdf', '2025-01-15 13:14:52.262721');
INSERT INTO exam.POST_ATTACH_FILE (seq, postSeq, fileName, filePath, registerDate) VALUES (3, 2, '파일명2.pdf', 'cdn경로/파일명2.pdf', '2025-01-15 13:14:52.262721');
INSERT INTO exam.POST_ATTACH_FILE (seq, postSeq, fileName, filePath, registerDate) VALUES (4, 2, '파일명3.pdf', 'cdn경로/파일명3.pdf', '2025-01-15 13:14:52.263231');
INSERT INTO exam.POST_ATTACH_FILE (seq, postSeq, fileName, filePath, registerDate) VALUES (5, 1, '변경파일1.jpg', 'cdn경로/변경파일1.jpg', '2025-01-15 13:16:17.106026');

INSERT INTO exam.COMMENT (seq, postSeq, userSeq, content, registerDate, updateDate) VALUES (1, 1, 1, '1번 수정답글2', '2025-01-15 13:20:23.994384', '2025-01-15 13:23:12.130284');
INSERT INTO exam.COMMENT (seq, postSeq, userSeq, content, registerDate, updateDate) VALUES (2, 1, 1, '1번게시글 답글2', '2025-01-15 13:21:20.065464', '2025-01-15 13:21:20.065464');
INSERT INTO exam.COMMENT (seq, postSeq, userSeq, content, registerDate, updateDate) VALUES (4, 1, 1, '1번게시글 답글3', '2025-01-15 13:24:48.453635', '2025-01-15 13:24:48.453635');
