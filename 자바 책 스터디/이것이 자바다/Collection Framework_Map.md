## Map 컬렉션 

---

Map 컬렉션은 **키(key)와 값(value)으로 구성된 Entry 객체를 저장하는 구조**를 가지고 있다.

여기서 **키와 값은 모두 객체**이다.

**키는 중복 저장될 수 없지만, 값은 중복 저장할 수 있다**.

만약 기존에 저장된 키와 동일한 키로 값을 저장하면 기존의 값은 없어지고 새로운 값으로 대치된다.

[##_Image|kage@brXbU1/btroZE66UR6/3XfcND8DEwWP9alpeWaMO1/img.png|CDM|1.3|{"originWidth":1920,"originHeight":738,"style":"alignCenter"}_##]

Map 컬렉션에는 HashMap, Hashtable, LinkedHashMap, Properties,TreeMap 등이 있다.

Map 컬렉션에서 공통적으로 사용가능한 Map 인터페이스의 메소드.

키로 객체들을 관리하기 때문에 키를 매개값으로 갖는 메소드가 많다.

| 기능 | 메소드 | 설명 |
| --- | --- | --- |
| 객체   추가 | V put(K key, V value)  | 주어진 키와 값을추가, 저장되면 값을 리턴 |
| 객체    검색 | boolean containsKey(Object key) | 주어진 키가 있는지 여부 |
| boolean containsValue(Object value) | 주어진 값이 있는지 여부 |
| Set<Map.Entry<K,V>> entrySet() | 키와 값의 쌍으로 구성된 모든 Map.Entry 객체를 Set에 담아서 리턴 |
| V.get(Object key) | 주어진 키가 있는 값을 리턴 |
| boolean isEmpty() | 컬렉션이 비어 있는지 여부 |
| Set<K> keySet() | 모든 키를 Set객체에 담아서 리턴 |
| int size() | 저장된 키의 총 수를 리턴  |
| Collection<V> values() | 저장된 모든 값을 Collection에 담아서 리턴 |
| 객체   삭제 | void clear() | 모든 Map.Entry(키와 값)를 삭제 |
| V remove(Object key) | 주어진 키와 일치하는 Map.Entry를 삭제하고 값을 리턴 |

위 표에서 매개 변수 타입과 리턴 타입에 K와 V라는 타입 파라미가 있다. 이것은 Map 인터페이스가 제네릭 타입이기 때문이다. 구체적인 타입은 구현 객체를 생성할 때 결정된다.

객체 추가는 put(), 키로 객체를 찾아올 때에는 get() 메소드를 사용한다. 객체 삭제는 remove() 메소드 사용

키를 알고 있을땐 get() 메소드로 간단하게 객체를 찾아올수 있다.

전체 객체를 대상으로 하나씩 얻고싶을때 방법은 2가지가 있다.

**1\. keySet() 메소드로 모든 키를 Set 컬렉션으로 얻은 다음, 반복자를 통해 키를 하나씩 얻고 get() 메소드를 통해 값 얻기**

```
Map<K, V> map = ~;
Set<K> keySet = map.keySet();
Iterator<K> keyIterator = keySet.iterator();
while(keyIterator.hasNext()) {
	K key = keyIterator.next();
    V value = map.get(key);
}
```

**2\. entrySet() 메소드로 모든 Map.Entry를 Set 컬렉션으로 얻은 다음, 반복자를 통해 Map.Entry를 하나씩 얻고 getKey() 와 getValue() 메소드를 이용해 키와 값을 얻기**

```
Set<Map.Entry<K, V>> entrySet = map.entrySet();
Iterator<Map.Entry<K, V>> entryIterator = entrySet.iterator();
while(entryIterator.hasNext()) {
	Map.Entry<K, V> entry = entryIterator.next();
    K key = entry.getKey();
    V value = entry.getValue();
}
```

#### **(1) HashMap**

---

Map 인터페이스를 구현한 대표적은 Map 컬렉션.

**HashMap의 키로 사용할 객체는 hashCode() 와 equals() 메소드를 재정의해서 동등 객체가 될 조건을 정해야 한다**.

동등 객체, 즉 **동일한 키가 될 조건은 hashCode()의 리턴값이 같아야 하고, equals() 메소드가 true를 리턴해야한다**.

[##_Image|kage@bUz92f/btroZD1xsFE/s3ARz72XgQrtsumCmNRmE0/img.png|CDM|1.3|{"originWidth":611,"originHeight":238,"style":"alignCenter"}_##]

**HashMap 생성 방법**

```
Map<K, V> map = new HashMap<K, V>();
```

**키와 값의 타입은 기본타입을 사용할 수 없다.**

이름을 키로, 점수를 값으로 저장하는 HashMap 예제

```
// 이름을 키로 점수를 값으로 저장하기
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMapExample1 {
	public static void main(String[] args) {
    	// Map 컬렉션 생성
        Map<String, Integer> map = new HashMap<String, Iteger>();
        
        // 객체 저장
        map.put("이래지", 85);
        map.put("이왜지", 90);
        map.put("이돼지", 80);
        map.put("이왜지", 95); // "이왜지" 키가 같기 때문에 제일 마지막에 저장한 값으로 대치된다.
       	System.out.println("총 Entry 수: " + map.size());
        
        // 객체 찾기
        System.out.println("\t이왜지 : " + map.get("이왜지")); // 이름(키)으로 점수(값)을 선택
        System.out.println();
        
        // 객체를 하나씩 처리 
        Set<String> keySet = map.keySet(); // Key Set 얻기
        Iterator<String> keyIterator = keySet.iterator(); // 반복해서 키를 얻고 값을 Map에서 얻어냄
        while(keyIterator.hasNext()) {
        	String key = keyIterator.next();
            Integer value = map.get(key);
            System.out.println("\t" + key + " : " + value);
        }
        System.out.println();
        
        // 객체 삭제
        map.remove("이왜지");
        System.out.println("총 Entry 수: " + map.szie());
        
        //객체를 하나씩 처리
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet(); // Map.EntrySet 얻기
        Iterator<Map.Entry<String, Integer>> entryIterator = entrySet.iterator();
        
        while(entryIterator.hasNext()) { // 반복해서 Map.Entry를 얻고 키와 값을 얻어냄
        	Map.Entry<String, Integer> entry = entryIterator.next();
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("\t" + key + " : " + vlaue);
        }
        System.out.println();
        
        // 객체 전체 삭제 
        map.clear(); // 모든 Map.Entry 삭제
        System.out.println("총 Entry 수: " + map.size());
    }
}
```

실행 결과

```
총 Entry 수 : 3
	이왜지 : 95
    
    이왜지 : 95
    이래지 : 85
    이돼지 : 80

총 Entry 수 : 2
	이래지 : 85
    이돼지 : 80
 
총 Entry 수 : 0
```

#### **(2) Hashtable**

---

Hashtable은 HashMap과 동일한 내부 구조를 가지고 있다. 

**Hashtable도 키로 사용할 객체는 hashCode() 와 equals() 메소드를 재정의해서 동등 객체가 될 조건을 정해야한다.**

**차이점**은 **Hashtable은 동기화된(synchronized) 메소드로 구성되어 있기 때문에 멀티 스레드가 동시에 이 메소드들을 실행할 수는 없고, 하나의 스레드가 실행을 완료해야만 다른 스레드를 실행할 수 있다. 그래서 멀티 스레드 환경에서 안전하게 객체를 추가, 삭제 할 수 있다.** **스레드가 안전하다고** 할 수 있다.

**생성 방법**

```
Map<K, V> map = new Hashtable<K, V>();
```

키보드로 아이디와 비밀번호를 입력받아, Hashtable에 저장되어 있는 키와 값으로 비교한 후 로그인 여부 출력 예제

```
// 아이디와 비밀번호 검사하기
import java.util.*;

public class HashTableExample {
	public static void main(String[] args) {
    	Map<String, String> map = new Hashtable<String, String>();
        
        map.put("spring", "12"); //아이디 비번 미리 저장시키기
        map.put("summer", "123");
        map.put("fall", "1234");
        map.put("winter", "12345"); 
        
        Scanner scanner = new Scanner(Syetem.in);
        
        while(true) {
        	System.out.println("아이디와 비밀번호를 입력해주세요.");
            System.out.print("아이디: ");
            String id = scanner.nextLine();
            
            System.out.print("비밀번호: ");
            String password = scanner.nextLine();
            System.out.println();
            
            if (map.containsKey(id)) { // 아이디가 존재하는지 비교
            	if (map.get(id).equals(password)) { // 비밀번호를 비교
                	System.out.println("로그인 되었습니다.");
                    break;
                } else {
                	System.out.println("비밀번호가 일치하지 않습니다.");
                }
            } else {
            	Systme.out.println("입력하신 아이디가 존재하지 않습니다.);
            }
        }
    }
}
```