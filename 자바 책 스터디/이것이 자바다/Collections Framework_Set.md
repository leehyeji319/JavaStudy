## Set 컬렉션

---

**Set 컬렉션은 저장 순서가 유지 되지 않는다. 또한 객체를 중복해서 저장할 수 없고, 하나의 null만 저장할 수 있다.**

[##_Image|kage@8O6Nd/btroZFYwQ19/0N2FF775bQuwPjHfaQtqWK/img.png|CDM|1.3|{"originWidth":262,"originHeight":169,"style":"alignCenter","width":366,"height":236}_##]

Set 컬렉션에는 HashSet, LinkedHashSet, TreeSet 등이 있는데, 다음은 Set 컬렉션에서 공통적으로 사용 가능한 Set 인터페이스의 메소드들이다. 인덱스로 관리하지 않기 때문에 인덱스를 매개값으로 갖는 메소드가 없다.

| 기능 | 메소드 | 설명 |
| --- | --- | --- |
| 객체   추가 | boolean add(E e) | 주어진 객체를 저장, 객체가 성공적으로 저장되면 true를 리턴하고 중복 객체면 false를 리턴 |
| 객체    검색 | boolean contains(Object o) | 주어진 객체가 저장되어 있는지 여부 |
| isEmpty() | 컬렉션이 비어 있는지 조사 |
| Iterator<E> iterator() | 저장된 객체를 한 번씩 가져오는 반복자 리턴 |
| int size() | 저장되어 있는 전체 객체 수 리턴 |
| 객체    삭제 | void clear() | 저장된 모든 객체를 삭제 |
| boolean remove(Object o) | 주어진 객체를 삭제 |

메소드의 매개 변수 타입과 리턴 타입에 E 라는 파라미터는 Set 인터페이스가 제네릭 타입이기 때문이다. 구체적인 타입은 구현 객체를 생성할 때 결정된다.

Set 컬렉션은 인덱스로 객체를 검색해서 가져오는 메소드가 없다. 대신, 전체 객체를 대상으로 한번씩 반복해서 가져오는 반복자(Iterator)를 제공한다. 반복자는 Iterator 인터페이스를 구현한 객체를 말하는데, iterator() 메소드를 호출하면 얻을 수 있다.

```
Set<String> set = ...;
Iterator<String> iterator = set.iterator();
```

Iterator 인터페이스에 선언된 메소드들

| 리턴 타입 | 메소드명 | 설명 |
| --- | --- | --- |
| boolean | hasNext() | 가져올 객체가 있으면 true를 리턴하고 없으면 false를 리턴한다. |
| E | next() | 컬렉션에서 하나의 객체를 가져온다. |
| void  | remove() | Set 컬렉션에서 객체를 제거한다. |

Iterator에서 하나의 객체를 가져올 때는 next() 메소드를 사용한다. next() 메소드를 사용하기 전에 먼저 가져올 객체가 있는지 확인하는 것이 좋다. hasNext() 메소드는 가져올 객체가 있으면 true를 리턴하고 더 이상 가져올 객체가 없으면 false를 리턴한다. 따라서 true가 리턴될 때 next() 메소드를 사용해야 한다. 다음은 Set 컬렉션에서 String 객체들을 반복해서 하나씩 가져오는 코드를 보여준다.

```
Set<String> set = ...;
Iterator<String> iterator = set.iterator();
while(iterator.hasNext()) { // 저장된 객체 수만큼 루핑한다.
	// String 객체 하나를 가져옴
    String str = iterato.next(); 
}
```

Iterator를 사용하지 않더라도 향상된 for문을 이용해서 전체 객체를 대상으로 반복할 수 있다.

```
Set<String> set = ...;
for(String str : set) { // 저장된 객체 수만큼 루핑한다.
}
```

Set 컬렉션에서 Iterator의 next() 메소드로 가져온 객체를 제거하고 싶다면 remove() 메소드를 호출하면 된다. Iterator의 메소드이지만, 실제 Set 컬렉션에서 객체가 제거됨을 알아야 한다.

```
while(iterator.hasNext()) {
	String str = iterator.next();
    if(str.equals("홍길동")) {
    	iterator.remove();
    }
}
```

#### **(1) HashSet**

---

HashSet은 Set 인터페이스와 구현 클래스이다.

**HashSet 생성 방법**

```
Set<E> set = new HashSet<E>();
// 타입 파라미터 E에는 컬렉션에 저장할 객체 타입을 지정하면 된다.
```

HashSet은 객체들을 순서 없이 저장하고 동일한 객체는 중복 저장하지 않는다.

HashSet이 판단하는 동일한 객체란 꼭 같은 인스턴스를 뜻하지는 않는다.

HashSet은 객체를 저장하기 전에 먼저 객체의 hashCode() 메소드를 호출해서 해시코드를 얻어낸다. 그리고 이미 저장되어 있는 객체들의 해시코드와 비교한다. 만약 동일한 해시코드가 있다면 다시 equals() 메소드로 두 객체를 비교해서 true가 나오면 동일한 객체로 판단하고 중복 저장을 하지 않는다. 

[##_Image|kage@r0eqL/btroWowgTD7/rrksDvnX5FjvbFZEODH2w1/img.png|CDM|1.3|{"originWidth":611,"originHeight":238,"style":"alignCenter"}_##]

HashSet에 String 객체를 추가, 검색, 제거하는 방법을 보여주는 예제

```
// String 객체를 중복없이 저장하는 HashSet

import java.util.*;

public class HashSetExample {
	public static void main(String[] args) {
    	Set<String> set = new HashSet<String>();
        
        set.add("Java"); 
        set.add("JDBC");
        set.add("Servlet/JSP");
        set.add("Java"); // "Java" 는 한번만 저장됨 !
        set.add("iBATIS");
        
        int size = set.size();
        System.out.println("총 객체수: " + size);
        
        Iterator<String> iterator = set.iterator();
        while(iterator.hasNext()) {
        	String element = iterator.next();
            System.out.println("\t" + element);
        }
        
        set.remove("JDBC");
        set.remove("iBATIS");
        
        System.out.println("총 객체수: " + set.size());
        
        iterator = set.iterator(); //반복자 얻기 
        while (iterator.hasNext()) {
        	String element = iterator.next();
            System.out.println("\t" + element);
        }
        
        set.clear() // 모든 객체를 제거하고 비움
        if (set.isEmpty()) {
        	System.out.println("비어 있음")
        }
    }
}
```

실행 결과 

```
총 객체수: 4
		iBATIS
        Servlet/JSP
        Java
        JDBC
총 객체수: 2
		Servlet/JSP
        Java
비어있음
```

**사용자 정의 클래스인 Member를 만들고 hashCode()와 equals() 메소드를 오버라이딩하는 예제**. 

인스턴스가 달라도 이름과 나이가 동일하다면 동일한 객체로 간주하여 중복 저장되지 않도록 하기 위해서이다.

```
// hashCode() 와 equals() 메소드 재정의
public class Member {
	public String name;
    public int age;
    
    public Member(String name, int age) {
    	this.name = name;
        this.age = age;
    }
    
    @Override
    public boolean equals(Object obj) { // name 과 age 값이 같으면 true를 리턴
    	if (obj instanceof Member) {
        	Member member = (Member) obj;
            return member.name.equals(name) && (member.age == age);
        } else {
        	return false;
        }
    }
    
    @Override
    public int hashCode() { // name과 age 값이 같으면 동일한 hashCode 리턴
    	return name.hashCode() + age;
    }           //String의 hashCode() 이용
}
```

```
// Member 객체를 중복없이 저장하는 HashSet

import java.util.*;

public class HashSetExample2{
	public static void main(String[] args) {
    	Set<Member> set = new HashSet<Member>();
        
        set.add(new Member("홍길동", 30); // 인스턴스는 다르지만 내부 데이터가 동일하므로 
        set.add(new MEmber("홍길동", 30); // 객체 한개만 저장
        
        System.out.println("총 객체수: " + set.size()); //저장된 객체 수 얻기
        
    }
}
```