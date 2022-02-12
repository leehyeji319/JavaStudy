## @ToString 
* toString() 메소드를 자동으로 생성한다.
* exclude를 통하여 제외할 필드를 지정할 수 있다.
* callSuper값을 true로 할 경우 상속받은 클래스의 정보까지 출력된다.(Default = false)
```java
@ToString(exclude = "password")
public class User {
	private String id;
    private String password;
}
```
* ToString에 토큰값, 비밀번호 등과 같은 민감한 정보를 exclude를 통하여 제외할 수 있다. 


## @Builder 빌더 자동 생성 
다수의 필드를 가지는 복잡한 클래스의 경우, 생성자 대신에 빌더를 사용하는 경우가 만핟. 빌더 패턴을 직접 작성해보면 의외로 코딩량이 상당하다. 이 때, ```@Builer``` 어노테이션을 사용하면 자동으로 해당 클래스에 빌더를 추가해주기 때문에 매우 편리합니다.
```java
@Builder
public class User {
	private Long id;
    private String username;
    private String password;
    @Singular
    private List<Integer> scores;
}
```

컬렉션으로 된 필드에는 ```@Singular``` 어노테이션을 선언해주면 모든 원소를 한 번에 넘기지 않고 원소를 하나씩 추가 할 수 있습니다.
```java
User user = User.builder()
	.id(1L)
    .username("abc")
    .password("1234")
    .score(70)
    .socre(80)
    .build();
// User(id=1, username=dale, password=1234, scores=[70, 80])
```

## @NoArgsConstructor
* parameter가 없는 기본 생성자를 생성해준다.
* AccessLevel을 통하여 접근제한자를 지정할수 있다.
권장 사용법
* @NoArgsConstructor(access = AccessLevel.PROTECTED)를 사용하여 객체 생성시 안전성을 보장해주는것을 권장한다.
* 기본 생성자를 public(default)로 열어두면 안전성이 심각하게 저하된다.
## @RequiredArgsConstructor (사용 금지 권장)
* final 혹은 @NonNull인 변수만 parameter로 받는 생성자를 생성해준다.
* AccessLevel을 통하여 접근제한자를 지정할수 있다.
>Use

```java
@NoArgsConstructor
public class User{
  @NonNull
  private String id;
  private String password;
}
```
>Unuse

```
public class User{
  private String id;
  private String password;
  
  public User(String id){
      this.id = id
  }
}
```
## @AllArgsConstructor (사용 금지 권장)
* 모든 변수를 parameter로 받는 생성자를 생성해준다.
* AccessLevel을 통하여 접근제한자를 지정할 수 있다.

>권장 사용법 

```java
@AllArgsConstructor
public class User {
	private String id;
    private String pwd;
}

User user = new User("userId", "userPwd");
```
이런식으로 초기 설정을 했다면
```java
@AllArgsConstructor
public class User {
	private String pwd;
    private String id;
}

User user = new User("userId", "userPwd"); //pwd,id의 위치가 바꼈지만 입력 매게변수는 위치가 바뀌지 않았다.
```
* 하지만 개발자가 갑자기 변수의 위치를 바꿔버리면 생성자의 위치도 바뀌지만 입력에는 둘다 String이라 오류가 뜨지 않는다. 이를 개발자가 인지하지 못하면 치명적인 실수를 일으키게 된다.
* 그러므로 @AllArgsConstructor, @RequiredArgsConstrucor이 Annotation들을 사용하지 않는것을 권장한다.

>권장 사용법

```java
public static class User {
	private String pwd;
    private String id;
    
    @Builder
    public User(String pwd, String id) {
    	this.pwd = pwd;
        this.id = id;
    }
}

User user = User.builder().pwd("userPwd").id("userId").build();
```
* 이런식으로 직접 생성한 생성자 위에 @Builder Annotation을 사용함으로 아래와 같이 개발자가 실수하는 것을 최대한 방지할 수 있다.
* builder는 순서가 아닌 이름으로 입력받기 때문이다.

## @Data (사용 금지 권장)
* @ToString, @EqualsAndHashCode, @Getter, @Setter, @RequiredArgsConstructor를 모두 포함한 Annotation입니다.

>권장 사용법

* 아예 사용을 금지하고 Getter/Setter 등 필요한 Annotation을 각각 선언하는것을 권장한다.

## @Value (사용 지양)
* 불변의 객체를 선언한다.
* 해당 Annotation을 사용할 경우 setter 메소드는 사용이 불가능하다.

```java
@Value
@Setter //ignore
public class User {
	public String id;
    public String password;
}
```
* Setter를 선언해도 사용이 불가능하다.
> 권장 사용법 

* @Value 역시 @EqualsAndHashCode, @AllArgsConstructor를 포함한다.
* @Getter, @ToString 만 사용하는 것을 권장한다.

## 로거 자동 생성
클래스마다 일일이 로거를 생성해주는 여간 귀찮은 일이 아닙니다. 이 때, ```@Log```어노테이션을 사용하면 자동으로 log필드를 만들고, 해당 클래스의 이름으로 로거 객체를 생성하여 할당해줍니다. ```@Log```뿐만 아니라, ```@Slf4j```나```@Log4j2```등 다양한 로깅 프레임워크에 대응하는 어노테이션을 제공하오니 참고바랍니다.
```java
@Log
public class LogExample {
	//자동 생성
    public static void main(String... args) {
    	log.info("TEST");
    }
}
```

## @NonNull Null 체크
```@NonNull```어노테이션을 변수에 붙이면 자동으로```null```체크를 해줍니다. 즉, 해당 변수가 ```null```로 넘어온 경우, ```NullPointerException``` 예외를 일으켜 줍니다.
```java
@NonNull @Setter
private String id;
```
```java
obj.setId(null); // NullPointerException 발생
```

## 자동 자원 닫기
IO 처리나 JDBC 코딩을 할 때, ```try-catch-finally```문의 ```finally```절을 통해서```close()```메소드를 호출하는 게 여간 번거로운 일이 아니었는데, ```@Cleanup```어노테션을 사용하면 해당 자원이 자동으로 닫히는 것이 보장됩니다.
```java
@Cleanup Connection con = DriverManager.getConnection(url, user, password);
```

## 예외 처리 생략
Checked Exception 때문에 반드시 ```throws```나 ```try-catch```구문을 통해서 번거롭게 명시적으로 예외 처리를 해줘야할 때가 있습니다. 이럴 때, ```@SneakyThrows```어노테이션을 사용하면 명시적인 예외 처리를 생략할 수 있습니다. (호불호가 갈릴수있다.)
```java
@SneakyThrows(IOException.class)
public void printLines() {
	BufferedReader reader = new BufferedReader(...);
    for (String line; (line = reader.readLine()) != null; ) {
    	System.out.println(line);
    }
}
```

## 동기화 
자바의 ```synchronized``` 키워드를 메소드에 선언하면 객체 레벨에서 락이 걸려서 여러가지 동기화 관련 문제들이 발생할 수 있습니다. 대신에 ```@Synchronized```어노테이션을 사용하면 가상의 필드 레벨에서 좀더 안전하게 락을 걸어줍니다.
```java
@Synchronized
public void hello() {
	System.out.println("world");
}
```


----
출처
https://www.daleseo.com/lombok-useful-annotations/
https://ksshlee.github.io/spring/java/lombok/#tostring