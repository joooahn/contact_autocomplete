# mini-project

> mini-project 포트번호 8080
> H2 데이터베이스를 사용하여 바로 테스트해볼 수 있습니다.

## Build Setup

- front 의존성 적용 및 빌드
``` bash
# install dependencies
npm install

# build for production with minification
npm run build
```
- spring boot 실행

- localhost:8080 접속

## api

| url | 파라미터 | 설명 | 기타 |
|--|--|--|--|
| item/title/search?st={search_type}&k={keyword}&s={size} | - search_type: 검색 종류 <br/> - keyword: 검색어 <br/> - size: 조회 크기 | title로 item을 검색하는 url | |
| item/title/startsWith/{searchText}/size/{size} | - searchText: 검색어 <br/> - size: 조회 크기 | 앞부분 일치 조회 url | deprecated |
| item/title/contains/{searchText}/size/{size} | - searchText: 검색어 <br/> - size: 조회 크기 | 포함 일치 조회 url | deprecated |
| item/title/endsWith/{searchText}/size/{size} | - searchText: 검색어 <br/> - size: 조회 크기 | 뒷부분 일치 조회 url | deprecated |
