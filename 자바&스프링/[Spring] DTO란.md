# 1. DTO(Data Transfer Object)란?
DTO(Data Transfer Object)란 계층간 데이터 교환을 위해 사용하는 객체(Java Beans)이다.
DTO의 구체적인 예시와 필요성을 MVC 패턴을 통해 알아보자.

## MVC 패턴
![](https://images.velog.io/images/modsiw/post/2db8990f-e6ad-433e-a756-bfe0890b1130/image.png)
MVC 패턴은 어플리케이션을 개발할 때 그 구성 요소를 Model과 View및 Controller 등 세가지 역할로 구분하는 디자인 패턴입니다. 비즈니스 처리 로직(Model)과 UI영역(View)은 서로의 존재를 인지하지 못하고, Controller가 중간에서 Model과 View의 연결을 담당합니다. 

Controller는 View로부터 들어온 사용자 요청을 해석하여 Model을 업데이트하거나 Model로부터 데이터를 받아 View로 전달하는 작업 등을 수행합니다. MVC패턴의 장점은 Model과 View를 분리함으로써 서로의 의존성을 낮추고 독립적인 개발을 가능하게 합니다.

Controller는 View와 도메인 Model의 데이터를 주고 받을 때 별도의 DTO를 주로 사용합니다. 도메인 객체를 View에 직접 전달할 수 있지만, 민감한 도메인 비즈니스 기능이 노출될 수 있으며 Model과 View사이에 의존성이 생기기 때문입니다. 물론 소규모는 DTO 사용이 불필요한 경우도 있습니다.

### 예시
> User.java

```java
public class User {
	public Long id;
    public String name;
    public String email;
    public String password;
    public DetailInformation detailInformation; 
    
    //비즈니스 로직, getter, setter 등 생략
}
```

>UserController.java

```java
@GetMapping
public RepositoryEntity<User> showArticle(@PathVariable Long id) {
	User user = userService.findByUd(id);
    return ResponseEntity.ok().body(user);
}
```
이렇게 Controller가 클라이언트의 요청에 대한 응답으로 도메인 Model인 User를 넘겨주면 어떤 문제점이 있을까?
* 도메인 Model의 모든 속송이 외부에 노출됩니다.
  * UI화면마다 사용하는 Model의 정보는 상이하지만, Model객체는 UI에서 사용하지 않을 불필요한 데이터까지 보유하고 있습니다.
  * 비즈니스 로직 등 User의 민감한 정보가 외부에 노출되는 보안 문제와도 직결됩니다.
  
* UI계층에서 Model의 메서드를 호출하거나 상태를 변경시킬 위험이 존재합니다.
* Model과 View가 강하게 결합되어, View의 요구사항 변화가 Model에 영향을 끼치기 쉽습니다.
  * 또한 UserEntity의 속성이 변경되면, View가 전달받을 JSON 및 프론트엔드 Js 코드에도 변경을 유발하기 때문에 상호간 강하게 결합됩니다.
  
>UserDto.java

```java
public class UserDto {
	public final long id;
    public final String name;
    public final String email;
    
    //생성자 생략
    
    public static UserDto from(User user) {
    	return new UserDto(user.getId(), user.getName(), user.getEmail());
    }
}
```

>Usercontroller.java

```java
@GetMapping
public ResponseEntity<UserDto> showArticle(@PathVariable Long id) {
	User user = userService.findById(id);
    return ResponseEntity.ok().body(UserDto.from(user));
}
```

반면 이렇게 DTO를 사용하면 앞서 언급된 문제들을 쉽게 해결할 수 있습니다. 도메인 Model을 캡슐화하고, UI화면에서 사용하는 데이터만 선택적으로 보낼 수 있습니다.

정리하면 DTO는 클라이언트 요청에 포함된 데이터를 담아 서버 측에 전달하고, 서버 측의 응답 데이터를 담아 클라이언트에 전달하는 계층간 전달자 역할을 합니다.


 
 
 DTO에 대한 정확한 개념이 잡히지 않아 참고 블로그를 보며 타이핑해서 내 머리에 욱여넣기..
 
 참고 blog: https://tecoble.techcourse.co.kr/post/2021-04-25-dto-layer-scope/
