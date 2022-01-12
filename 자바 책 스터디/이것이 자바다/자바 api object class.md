클래스 선언할 때 extends 로 다른 클래스를 상속하지 않으면 암시적으로 java.lang.Object 클래스를 상속한다.

자바의 모든 클래스는 Object 클래스의 자식이거나 자손 클래스.

**즉, Object 클래스는 자바의 최상위 부모 클래스이다.**

### Object 클래스의 메소드

---

## 1\. 객체 비교 equals()

```
public boolean equals(Object object){ }
```

-   매개 타입 : Object 로 모든 객체가 매개 값으로 대입 가능
-   리턴 타입 : boolean 두 객체가 동일한 객체면 true, 아니면 false 반환

비교 연산자 "==" 와 같은 기능을 함

Object 클래스의 equals() 메소드는 직접 사용되지 않고 하위 클래스에서 재정의 되어 논리적으로 동등비교시 사용된다.  
equals() 메소드를 재정의할 시,  
비교 객체가 기준 객체와 동일한지 확인해야 하므로, instanceof 연산자로 미리 확인해야 한다.

```
public Class Member {
    public String id;

    public Member(String id) {
        this.id = id;
       }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Member) {
            Member member = (Member) obj;
            if(id.equals(member.id)) {
                return true;
            }
        }
        return false;
    }
}
```

---

## 2\. 객체 해시 코드 hashCode()

객체 해시코드 : 객체를 식별할 하나의 정수값

Object의 hashCode() 메소드는 객체의 메모리 번지를 이용해서 해시코드를 만들어 리턴한다.  
객체마다 다른값을 가짐

논리적 동등 비교시 hashCode()를 오버라이딩해야한다.  
컬렉션 프레임워크에서 HashSet, HashMap, Hashtable은 아래와 같은 방법으로 두 객체가 동등한지 비교한다.

[##_Image|kage@n8hTP/btrorPoS0zy/tdJrJKMsb6PrOsUl0p8As1/img.png|CDM|1.3|{"originWidth":611,"originHeight":238,"style":"alignCenter"}_##]

hashCode()의 리턴 값이 우선 일치하고 equals() 의 리턴값이 true 여야 논리적으로 같은 객체라고 판단한다.

이때문에 hashCode를 equals와 함께 재정의하지 않으면 java collection framework를 사용할 때, 코드가 예상과 다르게 동작할 수 있다.

# hashCode 재정의

```
public class Car {
    private final String name;

    public Car(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Object.equals(name, car.name);
    }

    @Override
    public int hashCode() { //Object.hash 메소드를 호출하는 로직으로 hashCode 메소드가 재정의 
    return Object.hash(name);
    }
}
```