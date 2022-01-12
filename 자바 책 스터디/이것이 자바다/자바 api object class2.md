### Obejct 클래스의 메소드

---

## 1\. 객체 문자 정보 toString()

Object 클래스의 toString() 메소드는 "클래스명@16진수해시코드"로 구성된 문자 정보를 리턴한다.

Object 하위 클래스는 toString() 메소드를 재정의하여 간결하고 유익한 정보를 리턴하도록 되어있다.

(ex. Date 클래스 )

```
public class ToStringExample {
    public static void main(String[] args) {
        Obejct ob1 = new Object();
        Date obj2 = new Date();
        System.out.println(obj1.toString());
        System.out.println(obj2.toString());
    }
}
```

출력 결과

```
java.lang.Object@1b15692
Wed Nov 13 09:33.06 KST 2013
```

우리가 만드는 클래스도 toString() 메소드를 재정의해서 좀 더 유용한 정보를 리턴하도록 할 수 있다.

```
public class SmartPhone { 
    private String company;
    private String os;

    public SmartPhone(String company, String os) {
        this.company = company;
        this.os = os;
    }

    @Override
    public String toString() { //toString() 재정의
        return company + ", " + os;
    }
}
```

```
public class SmartPhoneExample { 
    public static void main(String[] args) {
        SmartPhone myPhone = new SmartPhone("애플", "맥");

           String strObj = myPhone.toString();
        System.out.println(strObj);
           System.out.println(myPhone); // myPhone.toStrint()을 자동 호출해서 리턴값을 얻은 후 출력
    }
}
```

출력 결과

```
애플, 맥
애플, 맥
```

---

## 2\. 객체 복제 clone()

**원본 객체의 필드값과 동일한 값을 가지는 새로운 객체를 생성하는 것**

객체를 복제하는 이유 : 원본 객체를 안전하게 보호하기 위해서이다.  
신뢰하지 않는 영역으로 원본 객체를 넘겨서 작업할 경우, 원본 객체의 데이터가 훼손될 수 있다.

얕은 복제(shallow clone), 깊은 복제(deep clone) 이 있다.

-   **얕은 복제** : 단순히 필드값을 복사해서 객체를 복제하는 것. 필드값만 복제하기 때문에 필드가 기본 타입일 경우 값 복사가 일어나고, 필드가 참조 타입일 경우에는 객체의 번지가 복사된다. (단점 : **만약 복제 객체에서 참조 객체를 변경하면 원본 객체도 변경된 객체를 가지게 된다.**)

[##_Image|kage@xfLs8/btrozyenVda/Shg33R7c2sQZBqkJ9Psui0/img.png|CDM|1.3|{"originWidth":543,"originHeight":270,"style":"alignCenter"}_##]

Object의 clone() 메소드는 자신과 동일한 필드값을 가진 얕은 복제된 객체를 리턴한다. **이 메소드로 객체를 복제하려면 원본 객체는 반드시 java.lang.Cloneable 인터페이스를 구현하고 있어야한다.** Cloneable 인터페이스를 구현하지 않으면 CloneNotSupportedException이 발생하여 복제가 실패된다. 따라서 clone()은 예외처리가 필요한 메소드이기 때문에 try-catch 구문이 필요하다.

```
// clone() 의 try-catch 구문 
try {
    Object obj = clone();
} catch (CloneNotSupportedException e) { }
```

다음 예제에선 Member 클래스가 Cloneable 인터페이스를 구현했기 때문에 getMember() 메소드에서 clone() 메소드로 자신을 복제한 후, 복제한 객체를 외부로 리턴할 수 있다.

```
//복제할 수 있는 클래스를 선언
public class Member implements Cloneable { // 복제를 할 수 있다는 표시
    public String id
    public String name;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Member getMember() {
        Member cloned = null;
        try {
            cloned = (Member) clone(); // clone() 메소드의 리턴타입은 Object 이므로 Member 로 캐스팅해야됨 
        } catch (ClonedNotSupportedException e) { }
        return cloned;
    }

}
```

**얕은 복사는 원본 객체를 복사한 후, 복사된 객체의 필드값을 변경하더라도 원본객체의 필드값은 변경이 되지 않는다.**

```
// 복제 객체를 변경하더라도 원본 객체는 변함이 없다 
public class MemberExample {
    public static void main(String[] args) {
        //원본 객체 생성
        Member original = new Member("blue", "홍길동");

        //복제 객체를 얻은 후에 패스워드 변경
        Member cloned = original.getMember();
        cloned.name = "이래지";

        System.out.println("[복제 객체의 필드값]")
        System.out.println("id : " + cloned.id)
        System.out.println("name : " + cloned.name);

        System.out.println()

        System.out.println("[원본 객체의 필드값]")
        System.out.println("id : " + original.id);
        System.out.println("name : " + original.name); // 원본 객체의 이름은 변함 없다
    }
}
```

실행 결과

```
[복제 객체의 필드값]
id : blue
name : 홍길동

[원본 객체의 필드값]
id : blue
name : 이래지
```

---

```
**깊은 복제** : 참조하고 있는 객체도 복제하는 것을 말한다. 깊은 복제를 하려면 Object의 clone() 메소드를 재정의해서 참조 객체를 복제하는 코드를 직접 작성해야 한다.
```

[##_Image|kage@celydo/btror1Xk0sD/XPiKAOtLDG6LKOBqM6BN40/img.png|CDM|1.3|{"originWidth":648,"originHeight":248,"style":"alignCenter"}_##]

깊은 복제를 하려면 Object의 clone() 메소드를 재정의해서 참조 객체를 복제하면 코드를 직접 작성해야 한다.  
다음 예제는 Member 클래스에 int\[\] 배열과 Car 타입의 필드가 있다. 이 필드들은 모두 참조 타입이므로 깊은 복제 대상이 된다. Member 클래스는 Object의 clone() 메소드를 재정의해서 int\[\] 배열과 Car 객체를 복제한다.

```
//clone() 을 재정의해서 깊은 복제로 변경
public class Member implements Cloneable {
    public String name;
    public int age;
    public int[] scores; //참조타입 필드 (깊은 복제 대상)
    public Car car; //참조타입 필드 (깊은 복제 대상)

    pulbic Member(String name, int age, int[] scores, Car car) {
        this.name = name;
        this.age = age;
        this.scores = scores;
        this.car = car;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //먼저 얕은 복사를 해서 name, age를 복제한다
        Member cloned = (Member) super.clone; // Object의 clone() 호출
        //scores를 깊은 복제한다.
        cloned.car = new Car(this.model);
        //깊은 복제된 Member객체를 리턴
        return cloned;
    }

    public Member getMember() {
        Member cloned = null;
        try {
            cloned = (Member) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return cloend;
    }
}
```

아래는 Car 클래스이다.

```
public class Car {
    public String model;

    public Car(String model) {
        this.model = model;
    }
}
```

**원본 Member 객체의 scores 배열 항목값과 car 객체의 모델은 변함이 없는 것을 볼 수 있다.  
원본과 복제본이 각각 참조하는 scores 배열과 car 객체는 서로 다르기 때문이다.**

**깊은 복사(Deep Copy)**는 '실제 값'을 새로운 메모리 공간에 복사하는 것을 의미하고,

**얕은 복사(Shallow Copy)**는 '주소 값'을 복사한다는 의미한다.

---

## 3\. 객체 소멸자 finalize()

참조하지 않는 배열이나 객체는 쓰레기 수집기가 힙 영역에서 자동적으로 소멸시킨다. 쓰레기 수집기는 객체를 소멸하기 직전에 마지막으로 객체의 소멸자를 실행시킨다 (finalize()) 소멸자는 Object의 finalize() 메소드를 말하는데, 기본적으로 실행 내용이 없다.

만약 객체가 소멸되기 전에 마지막으로 사용했던 자원을 닫고 싶거나, 중요한 데이터를 저장하고 싶다면 Object의 finalize()를 재정의하면 된다.