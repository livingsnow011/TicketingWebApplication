# TicketingWebApplication

SeSAC-CloudMSA-BackendProject-Team-4

~~https://ticketingweb.ddns.net~~ 인스턴스 종료


# 목차
  - [요약](#요약)
  - [애플리케이션 아키텍처](#애플리케이션-아키텍처)
  - [ERD](#erd)
  - [패키지 구조](#패키지-구조)
  - [개발 환경](#개발-환경)
  - [사용 기술](#사용-기술)
    - [백엔드](#백엔드)
    - [프론트엔드](#프론트엔드)
    - [인프라](#인프라)
  - [프로젝트 목적](#프로젝트-목적)
  - [프로젝트 진행 및 맡은 역할](#프로젝트-진행-및-맡은-역할)
    - [Trello](#trello)
    - [맡은 역할](#맡은-역할)
  - [기능 시연](#기능-시연)
    - [공연 CRUD](#공연-crud)
    - [스케줄러를 활용한 예매,추첨 과정](#스케줄러를-활용한-예매추첨-과정)
    - [인증,인가 및 관리자 페이지](#인증인가-및-관리자-페이지)
    - [S3 이미지 저장소](#s3-이미지-저장소)
    - [페이징](#페이징)
    - [N+1 문제](#n1-문제)
    - [CI/CD 무중단 배포 서비스 구축](#cicd-무중단-배포-서비스-구축)
    - [도메인과 SSL 설정](#도메인과-ssl-설정)
  - [후기](#후기)

## 요약
- 선착순이 아닌 추첨으로 이루어지는 공연 예매 웹 애플리케이션 개발
- 스프링 부트를 활용하여 기획 -> 개발 -> 배포 -> 운영까지 전과정 개발 경험 확보
- JPA를 사용한 도메인 설계 및 스프링 MVC 프레임워크 기반 백엔드 서버 구축 
- 3인 팀구성으로 협업 개발

<details>
  <summary> <h3>api 명세서</h3></summary>
<div markdown="1">

| index | 상세 | HTTP method | uri | 관리자 권한 |
| --- | --- | --- | --- | --- |
| 1 | 메인 페이지 | GET | / |  |
| 2 | 공연 생성 페이지 | GET | /admin/show/new | o |
| 3 | 공연 생성 | POST | /admin/show/new | o |
| 4 | 공연 수정 페이지 | GET | /admin/show/{showId} | o |
| 5 | 공연 수정 | POST | /admin/show/{showId} | o |
| 6 | 공연 삭제 | DELETE | /admin/show/{showId} | o |
| 7 | 분류 별  조회+ 페이징 | GET | /show/{ShowClassification}+ /{page} |  |
| 8 | 공연 조회 | GET | /show/detail/{showId} |  |
| 9 | 회원 가입 페이지 | GET | /new |  |
| 10 | 회원 가입 | POST | /new |  |
| 11 | 로그인 페이지 | GET | /login |  |
| 12 | 로그인 에러 | GET | /login/error |  |
| 13 | 공연 예매 | POST | /book |  |
| 14 | 예매 공연 조회+ 페이징 | GET | /books+{page} |  |
</div>
</details>

## 애플리케이션 아키텍처
![img](https://raw.githubusercontent.com/livingsnow011/TicketingWeb-app-spring/main/Architecture.png)

## ERD
![img](https://raw.githubusercontent.com/livingsnow011/TicketingWeb-app-spring/main/ERD.png)

## 패키지 구조
![img](https://raw.githubusercontent.com/livingsnow011/TicketingWeb-app-spring/main/Package.png)

## 개발 환경

- Trello 
- intelij
- Github
- Github Desktop
- mySQL workbench

## 사용 기술

### 백엔드

#### 주요 라이브러리 / 프레임워크
- Java openjdk version 11.0.2
- Spring Boot 2.7.1
- Spring Data JPA
- Spring Security
- Spring Cloud ( S3 공연 이미지 저장소 )

#### Build
- Gradle

#### database
- MySQL

### 프론트엔드
- HTML5/CSS/javascript
- Thymeleaf
- BootStrap 4.5.2
- JQuery ajax

### 인프라
- Amazon Linux 2 OS
- AWS EC2
- AWS RDS
- AWS S3
- AWS codedeploy
- Nginx
- Github Action

### 기타
- Lombok
- ModelMapper
- thymeleaf-extras-springsecurity
- thymeleaf-layout-dialect
- h2database

## 프로젝트 목적
### 왜 선착순이 아닌 추첨으로 예매를 해야하나
**SESAC 교육과정**에서 스프링까지 진도를 나간 후, 팀원들을 정해 웹 애플리케이션을 만드는 것부터 시작했습니다.  

당시 주제를 정하는 데만 2일 정도를 소비하였는데, 각자 하고 싶은 주제를 제시한 뒤 **투표**를 통해 무엇을 만들지 정하였습니다.  

저는 당시 **레딧**과 같은 주제가 다양한 커뮤니티 사이트를 만들자는 의견을 냈었고, 다른 팀원들도 각자 다양한 의견을 내었습니다.  

투표 결과 저의 커뮤니티 안과 최oo 팀원의 **티켓예매** 애플리케이션이 남았고, 2차 투표 결과 티켓예매 웹사이트를 개발하기로 결정되었습니다.  

공연 관련 티켓 예매 웹 사이트로 방향이 정해졌지만, 기존의 예매 사이트들처럼 선착순으로 하는 예매는 뭔가 밋밋한 느낌이 들었고, 팀원들끼리 주제에 대해 상의해 본 결과 우리는 선착순이 아닌 **추첨을 통한 예매사이트**를 만들자로 가닥을 정했습니다.  

물론 새로운 것을 만들어보자 라는 이유도 있었지만, 더 생각해보면 추첨을 통한 예매에 차별점과 좋은 점들 발견할 수 있었습니다.  

기존의 사람들이 사용하는 **yes24 공연**이나 **인터파크** 등의 사이트들은 모두 선착순으로 예매 여부가 정해집니다.  

그렇기 때문에 사람들은 예매 당일 당시에 **누구보다 빨리 예매 버튼을 누르기 위해 대기해야합니다.**  

이것은 고객들로 하여금 **피로감**을 느끼게 하고, 운영진 입장에서도 **특정 시간대에 몰리는 트래픽**을 처리하기 위해 분주히 준비해야 합니다. 

반면 추첨식으로 예매여부를 정하게 만든다면, 정해진 기간 안에 예매 버튼만 누르고 **결과를 확인하기만** 하면 됩니다.  

저희는 이러한 애플리케이션이 시장에서 **차별점**이 있다고 생각하여, 개발하게 되었습니다.  

## 프로젝트 진행 및 맡은 역할
### Trello
팀원들끼리 개발을 시작하기 전에 해야할 것들을 정해보았습니다.  

**IDE**부터 이클립스가 익숙한 사람들과 인텔리제이가 익숙한 사람들이 있었고, 각자 설치한 **자바 버전**이 달랐으며(11과 8로 나뉨), **DB**는 무엇을 사용할지, **빌드 도구**들은 무엇을 사용할지 등등 생각보다 생각할 것이 많았습니다.  

물론 이것들은 **실제 기능개발의 어려움이 아니므로**, 서로 친해지는 **아이스브레이킹** 타임과 같이 서로 이런 것들이 이런 점이 좋다, 나는 이런것이 익숙하다 등의 표현으로 하나씩 정해갈 수 있었습니다.  

저희는 소통을 **디스코드**(게임하는 사람들은 다 갖고있는..)로 하였고, 기술 스택들이 정해지고 나서 gihub organization 기능으로 **공용 repository**를 만들었습니다. (그 전 프론트엔드 토이 프로젝트에서는 invite collaborator 기능을 사용)  

슬슬 설계 단계에 들어선 느낌이 들었을 때, 팀 안에서 **설계 협업 도구를 이용해 보는것이 어떤가?** 라는 의견이 나왔습니다. 전 토이 프로젝트 발표 때 다른 조에서 **Notion**을 통해 작업 과정을 정리하였고, 다른 조들도 저와 같이 그 조의 활동을 보고 감명을 받은 것 같았습니다.  

저희도 Notion을 통해 협업 문서를 작성해 볼까 했는데, 검색 결과 **Trello**라는 웹서비스도 협업에 많이 쓰인다는 것을 알게 되었습니다.  

팀원 모두 써본 경험이 없었기 때문에, 각자 계정을 생성하고 템플릿을 만들어 협업 공간으로 사용했습니다.  

![img](https://lh3.googleusercontent.com/u/0/drive-viewer/AFDK6gPMaFSqYFx1WF2AB20RMlnibb2XPiGx4bWsBOEK5lNGnxVhheTb9wHFDw53a4W_wfThdjPJMJE0DqdNLBrZAHLXVKcmlQ=w1920-h942)

Trello는 생각보다 간단하고 유용한 서비스였습니다. Notion보단 자율성이 낮은 것 같지만, 어떻게 보면 **협업과 스케줄에만 집중**할 수 있도록 해주었습니다.  

프로젝트 시작부터 끝날 때까지 팀원 모두 많이 들락날락했고, 뭘해야할지 모르겠을 때, 뭔가 공유해야할 것이 있을 때 Trello를 참고할 수 있었습니다. 

### 맡은 역할
결과적으로 제가 맡은 역할은 **공연을 위한 CRUD**와 **화면 단인 타임리프 템플릿** 제작이였습니다.
저 이외 팀원들은 
1. 최oo 팀원 : 공연 이미지, 예매관련 로직 작성
2. 김00 팀원 : 로그인 및 시큐리티 담당  

으로 분배하였습니다. (**배포와 CI/CD 구축은 교육과정 말에 deploy 파트가 끝난 후 개인으로 진행하였습니다.**)    
  

**물론 각자 개발만 할 수는 없었습니다.**  

최00 팀원과 저는 공연과 예매 관계였기 때문에, 좌석과 날짜 티켓 엔티티 설계부터 계속 소통해야했습니다.  

공연 이미지 처리 또한 제가 서비스를 만들고 어떻게 서버단에서 처리해야할지 순차적인 흐름이 있었기 때문에 **완성까지 각자 개발**과 같은 일은 없었습니다.   

제 역할에 대한 소감은 CRUD와 화면 구현이 주였기 때문에, **기본 구현에 대한 배운 것을 활용할 기회였다고 생각합니다.**    

**물론 예매가 주제인 애플리케이션이였기 때문에, 탐나는 역할을 맡지못한 것에 아쉬움이 있긴 하였습니다.**  

이런 아쉬움때문에 교육과정이 끝난 후, reposiotry를 **clone --mirror** 하여 처음부터 다시 만들어 보았습니다.  

공연 이미지는 AWS S3 저장소에 연결하는 것으로 변경하였고, 예매 로직 또한 제가 더 익숙한 방식으로 직접 짜보는 과정을 거쳤습니다.  


## 기능 시연

### 공연 CRUD
기본적인 공연 조회,등록,수정,삭제를 할 수 있습니다.  

조회는 모든 사용자들이 분류별로 볼 수 있게끔 하였습니다.  

등록,수정,삭제는 관리자 계정으로만 처리할 수 있습니다.  

#### Read
<img src='http://drive.google.com/uc?export=view&id=1OifYL68jmB52XhNNPXwwwiUgAyvYxwrO' />  

**모든 웹사이트 방문자들은 등록된 공연을 분류별로 볼 수 있습니다.**    

분류는 영화, 뮤지컬, 콘서트 등으로 나눴고 관리자 수정 페이지에서는 분류와 상관없이 모든 공연 목록을 볼 수 있습니다.  

분류 별로 페이징을 통해 9개 공연을 1페이지로 묶었으며, 예매 불가 상태가 된 경우 예매 페이지로 들어 갈 수 없게 만들었습니다.

### Create
<img src='http://drive.google.com/uc?export=view&id=1S1F0zRfpGMSlkJ-6yrMi75OZZVf3yJNi' />  

관리자 계정을 통해 공연을 등록할 수 있습니다.  

공연 하나 당 다수의 날짜, 좌석 등급, 이미지 등을 등록할 수 있으며, 사용자는 이를 통해 자신이 예매하고 싶은 공연의 상세 정보를 선택할 수 있습니다.    

등록된 날짜, 좌석 등급, 이미지 등은 공연과 1:N 관계를 맺으며, 날짜와 좌석등급 아이디를 통해 예매되는 순간 티켓 엔티티를 생성합니다. 

### Update & Delete
<img src='http://drive.google.com/uc?export=view&id=1_toilZCuX0Emg4I01-5SZeYClxXeFQ-N' />  

관리자 계정으로 관리페이지에서 공연들을 수정, 삭제할 수 있습니다.  

### 스케줄러를 활용한 예매,추첨 과정



### 인증,인가 및 관리자 페이지

### S3 이미지 저장소

### 페이징

### N+1 문제

### CI/CD 무중단 배포 서비스 구축

### 도메인과 SSL 설정

## 후기 
