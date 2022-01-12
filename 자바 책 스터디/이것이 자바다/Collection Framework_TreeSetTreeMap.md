컬렉션 프레임워크는 **검색 기능을 강화시킨 TreeSet과 TreeMap을 제공하고** 있다.

TreeSet은 Set 컬렉션이고, TreeMap은 Map 컬렉션이다.

이 컬렉션들은 **이진트리를 이용해서 계층적 구조를 가지면서 객체를 저장한다**.

### **(1) TreeSet**

---

**TreeSet은 이진트리를 기반으로한 Set 컬렉션**이다.

하나의 노드는 노드값은 **value와 왼쪽과 오른쪽 자식노드를 참고하기 위한 두개의 변수로 구성**된다.

**TreeSet에 객체를 저장하면 자동으로 정렬**된다.

부모값과 비교해서 **낮은 것은 왼쪽 자식 노드에, 높은 것은 오른쪽 자식 노드에 저장**

[##_Image|kage@bFhvqf/btro7gxvb9t/cnqxEbskqh0VentktSfcwk/img.png|CDM|1.3|{"originWidth":1780,"originHeight":854,"style":"alignCenter"}_##]

**생성 방법**

```
TreeSet<E> treeSet = new TreeSet<E>();
```

Set 인터페이스 타입 변수가 아니라 TreeSet 클래스 타입으로 입한 이유는

객체를 찾거나 범위 검색과 관련된 메소드를 사용하기 위해서이다.

**TreeSet 검색 관련 메소드들**

| 리턴 타입 | 메소드 | 설명 |
| --- | --- | --- |
| E | first() | 제일 낮은 객체를 리턴 |
| E | last() | 제일 높은 객체를 리턴 |
| E | lower(E e) | 주어진 객체보다 바로 아래 객체를 리턴 |
| E | higher(E e) | 주어진 객체보다 바로 위 객체를 리턴 |
| E | floor(E e) | 주어진 객체와 동등한 객체가 있으면 리턴, 만약 없다면 주어진 객체의 바로 아래 객체를 리턴 |
| E | ceiling(E e) | 주어진 객체와 동등한 객체가 있으면 리턴, 만약 없다면 주어진 객체의 바로 위의 객체를 리턴 |
| E | pollFirst() | 제일 낮은 객체를 꺼내오고 컬렉션에서 제거함 |
| E | pollLast() | 제일 높은 객체를 꺼내오고 컬렉션에서 제거함 |

**TreeSet 정렬 관련 메소드들**

| 리턴 타입 | 메소드 | 설명 |
| --- | --- | --- |
| Iterator<E> | descendingIterator()  | 내림차순으로 정렬된 Iterator를 리턴 |
| NavigableSet<E> | descendingSet() | 내림차순으로 정렬된 NavigableSet을 반환 |

**TreeSet 범위 검색 관련 메소드들**

| 리턴 타입 | 메소드 | 설명 |
| --- | --- | --- |
| NavigableSet<E> | headSet(       E toElement,       boolean inclusive   ) | 주어진 객체보다 낮은 객체들을 NavigableSet으로 리턴.    주어진 객체 포함 여부는 두 번째 매개값에 따라 달라짐 |
| NavigableSet<E> | tailSet(       E toElement,       boolean inclusive   ) | 주어진 객체보다 높은 객체들을 NavigableSet으로 리턴.   주어진 객체 포함 여부는 두 번재 매개값에 따라 달라짐 |
| NavigableSet<E> | subSet(       E fromElement,       boolean fromInclusive,       E toElement,       boolean toInclusive   ) | 시작과 끝으로 주어진 객체 사이의 객체들을 NavigableSet으로 리턴. 시작과 끝 객체 포함 여부는 두 번째, 네 번째 매개값에 따라 달라짐 |

### **(2) TreeMap**

---

TreeMap은 이진 트리를 기반으로 한 Map 컬렉션이다. **TreeSet과의 차이점은 키와 값이 저장된 Map.Entry를 저장한다는 점**

**TreeMap에 객체를 저장하면 자동으로** **정렬**된다. 기본적으로 부모 키값과 비교해서 키 값이 낮은 것은 왼쪽 자식 노드, 키 값이 높은 것은 오른쪽 자식 노드에 Map.Entry 객체를 저장한다.

[##_Image|kage@nou9I/btroZElSlJr/7dnqSqcc5kPxZnkIvSOkok/img.png|CDM|1.3|{"originWidth":890,"originHeight":427,"style":"alignCenter"}_##]

**생성 방법**

```
TreeMap<K, V> treeMap = new TreeMap<K, V>();
```

**TreeMap 검색 관련 메소드들**

| 리턴 타입 | 메소드 | 설명 |
| --- | --- | --- |
| Map.Entry<K,V> | firstEntry() | 제일 낮은 Map.Entry를 리턴 |
| Map.Entry<K,V> | lastEntry() | 제일 높은 Map.Entry를 리턴 |
| Map.Entry<K,V> | lowerEntry(K key) | 주어진 키보다 바로 아래  Map.Entry를 리턴 |
| Map.Entry<K,V> | higherEntry(K key) | 주어진 키보다 바로 위 Map.Entry를 리턴 |
| Map.Entry<K,V> | floorEntry(K key) | 주어진 키와 동등한 키가 있으면 해당 Map.Entry를 리턴.   없다면 주어진 키 바로 아래의 Map.Entry를 리턴 |
| Map.Entry<K,V> | ceilingEntry(K key) | 주어진 키와 동등한 키가 있으면 해당 Map.Entry를 리턴.   없다면 주어진 키 바로 위의 Map.Entry를 리턴 |
| Map.Entry<K,V> | pollFirstEntry() | 제일 낮은 Map.Entry를 꺼내오고 컬렉션에서 제거함 |
| Map.Entry<K,V> | pollLastEntry() | 제일 높은 Map.Entry를 꺼내오고 컬렉션에서 제거함 |

**TreeMap 정렬 관련 메소드들**

| 리턴 타입 | 메소드 | 설명 |
| --- | --- | --- |
| NavigableSet<K> | descendingKeySet() | 내림차순으로 정렬된 키의 NavigableSet을 리턴 |
| NavigableMap<K,V> | descendingMap() | 내림차순으로 정렬된 Map.Entry의 NavigableMap을 리턴 |

**TreeMap 범위 검색 관련 메소드들**

| 리턴 타입 | 메소드 | 설명 |
| --- | --- | --- |
| NavigableMap<K,V> | headMap(       K toKey,       boolean inclusive   ) | 주어진 키보다 낮은 Map.Entry들을 NavigableMap으로 리턴.   주어진 키의 Map.Entry 포함 여부는 두 번째 매개값에 따라 달라짐 |
| NavigableMap<K,V> | tailMap(       K fromKey,       boolean inclusive   ) | 주어진 객체보다 높은 Map.Entry들을 NavigableMap으로 리턴.   주어진 객체 포함 여부는 두 번째 매개값에 따라 달라짐 |
| NavigableMap<K,V> | subMap(       K fromKey,        boolean fromInclusive,       K toKey,       boolean toInclusive   ) | 시작과 끝으로 주어진 키 사이의 Map.Entry들을 NavigableMap 컬렉션으로 반환.   시작과 끝 키의 Map.Entry 포함 여부는 두 번째, 네 번째 매개값에 따라 달라짐 |