<div align="center">
  <img src="src/main/resources/static/images/stance.png" alt="stance image" width="200">
</div>

- 농구 동호회 모임통장의 회비 및 회원 관리 프로그램
- 실제 운영 중인 서비스이며 프론트엔드 부분은 HTML, CSS, JavaScript 를 사용하여 간단하게 구현하고 백엔드 개발에 중점을 두었습니다.
- 유지보수와 확장에 용이한 객체지향적 설계를 목표로 개발하였습니다.
<br>
<br>

---
## 🏀 서비스 개발 배경

> 🎉 이벤트란? <br>
매월 정기 입금되는 회비와는 달리 비정기적으로 발생하는 활동을 일컬어 이벤트라 지칭합니다. 이벤트의 종류에는 회식, 유니폼, 굿즈, 엠티 등이 있으며 각 이벤트의 신청자를 모집 후 해당 비용을 입금 받습니다.
> 
- 기존에 회비 내역을 엑셀로 관리 하면서 여러 번거로운 문제가 발생
    - 매달 회비 미입금자를 파악한 후 재알림 공지를 보내야 하지만, 엑셀로는 미입금자를 한눈에 쉽게 확인할 수 없어 비효율적
    - 비정기적으로 진행되는 이벤트가 빈번히 발생하게 되어 이를 카테고리별로 나눠서 관리할 필요성이 생김
    - 정기 입금과 비정기 입금을 따로 관리하면서 추가적인 관리 포인트가 생겨 시간 비용이 증가함
    - 각 이벤트와 이벤트 신청자의 입금 여부를 체계적으로 관리하기 어려워 작업이 복잡해짐

- 팀원 모두가 함께 접근하고 관리할 수 있는 플랫폼의 필요성이 커짐
    - 팀의 가용자금에 관한 문의가 주기적으로 들어옴
    - 각 이벤트 참여자들이 모두 입금을 완료해야 이벤트 담당자가 다음 절차를 진행할 수 있으므로, 관리자뿐만 아니라 팀원들도 입금 상태를 확인할 수 있어야 함
    - 본인 인증 문제로 특정 은행 플랫폼에 접근하지 못하는 팀원들도 모임 통장의 내역을 확인할 수 있어야 함

- 회원 출석 관리에서 회원 세부 관리로의 확장이 요구됨
    - 기존에는 회원들의 출석 내역만 관리하였으나, 회원의 활동 상태(액티브, 휴면, 탈퇴) 이력을 관리할 것이 요구됨
    - 신규회원이 등번호를 결정할 때 기존회원의 번호와 중복되지 않아야 하므로 이를 확인할 수 있는 창구를 제공해야 함
    - 대회 등 서류 작성 시 코치진과 운영자가 팀의 전체 인원, 이름, 등번호 등의 명단을 확보해야 하지만, 현재 이와 관련된 관리가 이루어지지 않고 있음

## 🏀 사용 기술 및 환경

Java, Spring Boot, Spring Data JPA, MySQL

## 🏀 주요 기능

### 사용자

1. 모임 통장 입출금 내역 조회
2. 통장 잔액 조회
3. 이달의 회비 입금 현황 조회
4. 회비 미입금자/입금자 명단 조회
5. 이벤트비 미입금자/입금자 명단 조회
6. 이벤트 신청자 명단 조회

### 관리자

1. 모임 통장 입출금 가계부 쓰기
2. 모임 통장 계좌 등록
3. 이벤트 생성
4. 이벤트 신청자 등록

## 🏀 기술적 이슈와 해결 과정

### 소프트웨어 아키텍처
- [더 이상 방황은 그만! 프로젝트 요구사항 정의하기](https://kellyihyeon.github.io/2024/07/07/%EC%9A%94%EA%B5%AC%EC%82%AC%ED%95%AD-%EC%A0%95%EC%9D%98%EC%84%9C/?t=1728882752917)
- [RESTful API - 리소스 이름은 어떻게 지어야할까?](https://kellyihyeon.github.io/2024/09/04/%EB%A6%AC%EC%86%8C%EC%8A%A4-%EC%9D%B4%EB%A6%84%EC%9D%80-%EC%96%B4%EB%96%BB%EA%B2%8C-%EC%A7%80%EC%96%B4%EC%95%BC%ED%95%A0%EA%B9%8C/?t=1727250665710)
- [OpenAPI Generator 로 페이징 처리는 어떻게 해?](https://kellyihyeon.github.io/2024/09/11/OpenAPI-Generator%EB%A1%9C-%ED%8E%98%EC%9D%B4%EC%A7%95-%EC%B2%98%EB%A6%AC%EB%8A%94-%EC%96%B4%EB%96%BB%EA%B2%8C-%ED%95%B4/?t=1728317733852)

### 인프라
- [Nginx 로 스프링 부트 8080번으로 포트포워딩 하기](https://kellyihyeon.github.io/2024/10/07/Nginx%EB%A1%9C-%EC%8A%A4%ED%94%84%EB%A7%81-%EB%B6%80%ED%8A%B8-8080%EB%B2%88%EC%9C%BC%EB%A1%9C-%ED%8F%AC%ED%8A%B8-%ED%8F%AC%EC%9B%8C%EB%94%A9-%ED%95%98%EA%B8%B0/?t=1728317848640)