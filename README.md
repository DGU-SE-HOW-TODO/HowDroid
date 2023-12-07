# *✅ HOW TO DO - AOS ✅*


##  *****✅  HOW TO DO*****
할 일을 기록하고 수행 여부를 실패 태그와 함께 관리하며 동기부여와 피드백을 제공해 주는 TODO 서비스

<br>

## 👋 *****Contributors*****

| [@l2zh](https://github.com/l2zh) | [@Dani43](https://github.com/Dan2dani) |
| :---: | :---: |
| ![image](https://github.com/DGU-SE-HOW-TODO/HowDroid/assets/113578158/0949efe9-94ff-4b32-975f-690c7bfe6642) | ![image](https://github.com/DGU-SE-HOW-TODO/HowDroid/assets/113578158/ce69010b-2c1e-4e19-929c-f154c0e742b5)|
|                                                    `AuthView`, `HomeView`, </br> `ToDoMakeView`                                             |`FailTagView`, `StatisticsView`, <br>`FeedBackView`|
                                                

## 📱*****ScreenShot*****

| <img width="200" src="https://github.com/DGU-SE-HOW-TODO/HowDroid/assets/113578158/f9c14055-586b-4727-997e-e3f5cfefdc9b"/> | <img width="200" src="https://github.com/DGU-SE-HOW-TODO/HowDroid/assets/113578158/b0a6aed7-af3f-4a40-8d55-c65232e56a75"/> | <img width="200" src="https://github.com/DGU-SE-HOW-TODO/HowDroid/assets/113578158/bcd63b80-d818-48dd-9cca-d0ddbcf83871"/> | <img width="200" src="https://github.com/DGU-SE-HOW-TODO/HowDroid/assets/113578158/ad3049cc-9d22-40f1-8d93-f99f5952e335"/> | <img width="200" src="https://github.com/DGU-SE-HOW-TODO/HowDroid/assets/113578158/93e8e3bb-bf79-4d71-a18a-e0341ccaa54d"/> |
| :---: | :---: | :---: |:------------------------------------------------------------------------------------------------------------------------------:| :---:|
|`로그인`|`회원가입` | `대분류 생성`|`투두 생성`|                                                        `투두 체크`                                                        |
| <img width="200" src="https://github.com/DGU-SE-HOW-TODO/HowDroid/assets/113578158/39f689ee-d399-4c44-9253-ff377c877e0d"/> | <img width="200" src="https://github.com/DGU-SE-HOW-TODO/HowDroid/assets/113578158/b9d30cf9-5c2b-444a-8cea-61cc8e0fb39e"/> | <img width="200" src="https://github.com/DGU-SE-HOW-TODO/HowDroid/assets/113578158/7da6d736-2470-41eb-892a-2363f7ca86fe"/> | <img width="200" src="https://github.com/DGU-SE-HOW-TODO/HowDroid/assets/113578158/f46d7ca4-25a0-425f-a700-d57c1b6a4c27"> | <img width="200" src="https://github.com/DGU-SE-HOW-TODO/HowDroid/assets/113578158/7ce71eed-57a5-447b-ac1b-934b0ae49161">
|`나만의 실패태그 만들기`|`실태패그 달기`|`미루기`|`투두 고정`|`통계/피드백`| 


<br>

<br>

## 📘 *****Convention*****

[깃 컨벤션이 궁금하다면? click ✔️](https://large-leo-019.notion.site/Git-Convention-6c3122332b2f4923abd9d8a3c97cb1a8?pvs=4)
<br>

[코드 컨벤션이 궁금하다면? click ✔️](https://large-leo-019.notion.site/Android-Coding-Convention-cf0a6a75b6ab49e181af63c57a6e551e?pvs=4)

<br>

## 👩🏻‍💻 ***Specification***

| Architecture | Clean Architecture, MVVM |
| --- | --- |
| Design Pattern | Repository Pattern, Adapter Pattern,  Delegation Pattern, Observer Pattern |
| Jetpack Components | encryptedsharedpreferences, LiveData, Lifecycle, ViewModel |
| Dependency Injection | Hilt |
| Network | Retrofit, OkHttp |
| Asynchronous Processing | Coroutine(+ Flow) |
| Third Party Library | Coil, Timber, kotlinSerialization |
| Strategy | Git Flow |
| CI | GitHub Action(KtLint, Complie Check) |
| Other Tool | Notion, Figma, Postman |
<br>

## 📁 *****Foldering*****

```
📂 com.HowDroid
┣ 📂 data
┃ ┣ 📂 datasource
┃ ┣ 📂 inetceptor
┃ ┣ 📂 model
┃ ┣ 📂 repository
┃ ┣ 📂 service
┣ 📂 di
┣ 📂 domain
┃ ┣ 📂 model
┃ ┣ 📂 repository
┣ 📂 presentation
┃ ┣ 📂 addtodo
┃ ┣ 📂 chart
┃ ┣ 📂 home
┃ ┣ 📂 login
┃ ┣ 📂 myfailtag
┃ ┣ 📂 signup
┃ ┣ 📂 type
┣ 📂 util
┃ ┣ 📂 binding
┃ ┣ 📂 extension
```
