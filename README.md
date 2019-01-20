# spring-boot-security

설명
* gralde 멀티 프로젝트 구성
    * security-domain : 공통 DTO, Repository, 초기 데이터 생성
    * security-web : 폼 로그인 방식의 기본 DB 인증
    * security-ldap : 폼 로그인 방식의 ldap 인증 후 권한은 DB를 통한 조회 