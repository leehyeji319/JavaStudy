## List 컬렉션

---

List 컬렉션은 객체를 일렬로 늘여놓은 구조를 가지고 있다. **객체를 인덱스로 관리**하기 때문에 **객체를 저장하면 자동 인덱스가 부여**되고 **인덱스로 객체를 검색, 삭제할 수 있는 기능을 제공**한다.  
**List 컬렉션은 객체 자체를 저장하는 것이 아니라 객체의 번지를 참조**한다. **동일한 객체를 중복 저장**할 수 있는데, **이 경우 동일한 번지가 참조**된다. null도 저장이 가능한데, 이 경우 해당 인덱스는 객체를 참조하지 않는다.

[##_Image|kage@cSyetI/btroWDtaWye/HEETUZN0hMMFXfNJXebBP0/img.png|CDM|1.3|{"originWidth":567,"originHeight":285,"style":"alignCenter"}_##]

List 컬렉션에는 ArrayList, Vector, LinkedList 등이 있는데, **다음은 List 컬렉션에서 공통적으로 사용가능한 List 인터페이스의 메소드들**이다. 인덱스로 객체를 관리하기 때문에 인덱스를 매개값으로 갖는 메소드가 다수.

| 기능 | 메소드 | 설명 |
| --- | --- | --- |
| 객체   추가 | boolean add(E e) | 주어진 객체를 맨 끝에 추가 |
| void add(int index, E element) | 주어진 인덱스에 객체를 추가 |
| set(int index, E element) | 주어진 인덱스에 저장된 객체를 주어진 객체로 바꿈 |
| 객체   검색 | boolean contains(Object o) | 주어진 객체가 저장되어 있는지 여부 |
| E get(int index) | 주어진 인덱스에 저장된 객체를 리턴  |
| isEmpty() | 컬렉션이 비어 있는지 조사 |
| int size() | 저장되어 있는 전체 객체 수를 리턴 |
| 객체   삭제  | void clear() | 저장된 모든 객체를 삭제 |
| E remove(int index) | 주어진 인덱스에 저장된 객체를 삭제 |
| boolean remove(Object o) | 주어진 객체를 삭제 |

매개 변수 타입과 리턴 타입에 E 라는 파라미터가 있다. 이것은 List 인터페이스가 제네릭 타입이기 때문이다. 구체적인 타입은 구현 객체를 생성할 때 결정된다.

#### **(1) ArrayList** 

---

ArrayList는 List의 인터페이스 구현 클래스로, ArrayList에 객체를 추가하면 객체가 인덱스로 관리된다. **일반 배열과 ArrayList는 인덱스로 객체를 관리하는 점에서는 유사하지만, 배열은 생성할때 크기가 고정되고 사용중에 변경할수 없고, ArrayList는 저장 용량을 초과한 객체들이 들어오면 자동적으로 저장 용량이 늘어난다.**

**즉 객체를 담을 크기가 유동적**

기본 생성자로 ArrayList 객체를 생성하면 내부에 10개의 객체를 저장할 수 있는 초기 용량을 가지게 된다. 

```
// 기본적으로 E 객체 10개를 저장할 수 있는 초기 용량을 가짐
List<String> list = new ArrayList<String>();

// 처음부터 용량을 크게 가지고 싶다면 용량의 크기를 매개값으로 받는 생성자를 이용하면 된다.
List<String> list = new ArrayList<String>(30); // String 객체 30개를 저장할 수 있는 용량을 가짐
```

ArrayList에 객체를 추가하면 인덱스 0부터 차례대로 저장된다. ArrayList에서 특정 인덱스의 객체를 제거하면 바로 뒤 인덱스부터 마지막 인덱스까지 모두 앞으로 1씩 당겨진다. 마찬가지로 특정 인덱스에 객체를 삽입하면 해당 인덱스부터 마지막 인덱스까지 모두 1씩 밀려난다.

**따라서, 빈번한 객체 삭제와 삽입이 일어나는 곳에서는 ArrayList를 사용하는 것이 바람직하지 않다. 이런 경우에는 LinkedList를 사용하는것이 좋다.** 

**그러나 인덱스 검색이나, 맨 마지막에 객체를 추가하는 경우에는 ArrayList가 더 좋은 성능을 발휘한다.**

```
// ArrayList에 String 객체를 추가, 검색, 삭제하는 방법 예시
// String 객체를 저장하는 ArrayList

import java.util.*;

public class ArrayListExample {
	public static void main(String[] args) {
    	List<String> list = new ArrayList<String>();
        
        list.add("Java"); // String 객체를 저장 
        list.add("JDBC");
        list.add("Servlet/JSP");
        list.add(2, "Database");
        list.add("iBATIS");
        
        int size = list.size(); // 저장된 총 객체수 얻기
        System.out.println("총 객체 수: " + size);
        System.out.println();
        
        String skill = list.get(2); // 2번 인덱스의 객체 얻기
        System.out.println("2 : " + skill);
        System.out.println();
        
        for (int i = 0; i < list.size(); i++) {
        	String str = list.get(i);
            System.out.println(i + ":" + str);
        }
        System.out.println();
        
        list.remove(2);
        list.remove(2);
        list.remove("iBATIS");
        
        for (int i = 0; i < list.size(); i++) {
        	String str = list.get(i);
            System.out.println(i + ":" + str);
        }
    }
}
```

ArrayList를 생성하고 런타임 시 필요에 의해 객체들을 추가하는 것이 일반적이지만, 고정된 객체들로 구성된 List를 생성할 때도 있다. 이런 경우에는 Arrays.asList(T... a) 메소드를 사용하는 것이 더 간편하다. 

```
List<T> list = Arrays.asList(T... a);
```

T 타입 파라미터에 맞게 asList()의 매개값을 순차적으로 입력하거나, T\[\] 배열을 매개값으로 주면된다. 다음은 고정된 String 객체를 요소로 갖는 ArrayList 객체를 생성한다.

```
// Arrays.asList() 메소드

import java.util.Arrays;
import java.util.List;

public class ArraysAsListExample {
	public static void main(String[] args) {
    	List<String> list1 = Arrays.asList("홍길동", "이래지", "래지");
        for(String name : list1) {
        	System.out.println(name);
        }
        
        List<Integer> list2 = Arrays.asList(1, 2, 3);
        for (int value : list2) {
        	System.out.println(value);
        }
    }
}
```

#### **(2) Vector** 

---

Vector는 ArrayList와 동일한 내부 구조를 가지고 있다. Vector를 생성하기 위해서는 저장할 객체 타입을 타입 파라미터로 표기하고 기본 생성자를 호출하면 된다.

```
List<E> list = new Vector<E>();
```

ArrayList와 다른 점은 Vector는 동기화된(synchronized) 메소드로 구성되어 있기 때문에 멀티 스레드가 동시에 이 메소드를 실행할 수 없고, 하나의 스레드가 실행을 완료해야만 다른 스레드를 실행할 수 있다. 그래서 멀티 스레드 환경에서 안전하게 객체를 추가, 삭제할 수 있다. 이것을 스레드가 안전(Thread safe)하다라고 말한다.

```
// Board 객체를 저장하는 Vector 

import java.util.*;

public class VectorExample {
	public class void main(String[] args) {
    
    	List<Board> list = new Vector<Board>();
        
    	list.add(new Board("제목1", "내용1", "글쓴이1"));
        list.add(new Board("제목2", "내용2", "글쓴이2"));
        list.add(new Board("제목3", "내용3", "글쓴이3"));
        list.add(new Board("제목4", "내용4", "글쓴이4"));
        list.add(new Board("제목5", "내용5", "글쓴이5"));
        
        list.remove(2); // 2번 인덱스 객체(제목3) 삭제(뒤의 인덱스는 1씩 앞으로 당겨짐)
        list.remove(3); // 3번 인덱스 객체(제목5) 삭제
        
        for (int i = 0; i < list.size(); i++) {
        	Board board = list.get(i);
            System.out.println(board.subject + "\t" + board.content + "\t" + board.writer);
        }
        
    }
}
```

```
// 게시글 정보 객체

public class Board {
	String subject;
    String content;
    String writer;
    
    public Board(String subject, String content, String writer) {
    	this.subject = subject;
        this.content = contetn;
        this.writer = writer;
    }
}
```

**Vector와 ArrayList 비교**

|   | Vector  | ArrayList |
| --- | --- | --- |
| 동기화 | 동기 | 비동기 but, Vector 와 동일한 클래스 구현 가능   (Collections.synchronizedList(new ArrayList()); |
| Thread Safe | 안전, 한 번에 하나의 스레드만 액세스 가능 | 불안전, 여러 스레드가 동시에 액세스 가능 |
| 성능 | 비교적 느림 | 동기화되지 않았기 때문에 비교적 빠름 |
| 크기 증가 | 최대 인덱스 초과 시 현재 배열 크기의 100% 증가 | 최대 인덱스 초과 시 현재 배열 크기의 50% 증가 |
| 사용 | 성능 저하로 사용 지양 | 동기화 처리 시에도 사용 권장 |

#### **(3) LinkedList**

---

LinkedList는 List 구현 클래스이므로 ArrayList와 사용 방법은 똑같지만 내부 구조는 완전 다르다. 

ArrayList는 내부 배열에 객체를 저장해서 인덱스로 관리하지만, **LinkedList는 인접 참조를 링크해서 체인처럼 관리**한다. 

[##_Image|kage@qh8q3/btroSVIv5Nz/hK4JUCwnYPKo5Rek2C7sqK/img.png|CDM|1.3|{"originWidth":777,"originHeight":355,"style":"alignCenter"}_##]

LinkedList에서 특정 인덱스의 객체를 제거하면 앞뒤 링크만 변경되고 나머지 링크는 변경되지 않는다.

특정 인덱스에 객체를 삽입할 때도 마찬가지다.

[##_Image|kage@chzpjd/btroXNoZXf0/FBFVQwj4r0oCiUrtghu841/img.png|CDM|1.3|{"originWidth":1353,"originHeight":728,"style":"alignCenter","width":400,"height":215}_##]

LinkedList 생성 방법

```
List<E> list = new LinkedList<E>();
// 처음 생성될 때에는 어떠한 링크도 만들어지지 않기 때문에 내부는 비어있다.
```

끝에서부터 순차적으로 추가/삭제하는 경우는 ArrayList가 빠르지만, 중간에 추가 또는 삭제할 경우는 앞뒤 링크 정보만 변경하면 되는 LinkedList가 더 빠르다. ArrayList는 뒤쪽 인덱스들을 모두 1씩 증가 또는 감소시키는 시간이 필요하므로 속도가 느리다.

**ArrayList와 LinkedList 비교**

| 구분 | 순차적으로 | 중간에 추가/삭제 | 검색 |
| --- | --- | --- | --- |
| ArrayList | 빠르다 | 느리다 | 빠르다 |
| LinkedList | 느리다 | 빠르다 | 느리다 |