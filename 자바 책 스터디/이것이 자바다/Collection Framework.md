애플리케이션을 개발하다 보면 다수의 객체를 저장해 두고 필요할 때마다 꺼내서 사용하는 경우가 많다. 

가장 간단한 방법은 배열을 이용하는 것인데, 비효율적이다.

**배열보다 효율적이고 널리 알려져 있는 것은 자료구조를 바탕으로 객체들을 효율적으로 추가, 삭제, 검색할 수 있도록 java.util 패키지에 컬렉션과 관련된 인터페이스와 클래스들을 포함시켜 놓았다.**

이를 **컬렉션 프레임워크(Collection Framework)**라고 부른다. 

컬렉션(Collection)이란 사전적 의미로 요소를 수집해서 저장하는 것으로, 자바 컬렉션은 객체를 수집해서 저장하는 역할을 한다.   
프레임워크란 사용 방법을 미리 정해 놓은 라이브러리를 말한다.

[##_Image|kage@kIGLw/btroZD7pdQe/kOD3KZlXV2CCJP2CekJ0m1/img.png|CDM|1.3|{"originWidth":1400,"originHeight":1009,"style":"alignCenter","width":700,"height":505}_##]

List와 Set은 객체를 추가, 삭제, 검색하는 방법에 많은 공통점이 있기 때문에 이 인터페이스들의 공통된 메소드들만 모아 Collection 인터페이스로 정의해 두고 있다. 

Map은 키와 값을 하나의 쌍으로 묶어서 관리하는 구조로 되어 있어, List와 Set과는 사용방법이 완전히 다르다.

| 인터페이스 분류 |  | 특징 | 구현 클래스 |
| --- | --- | --- | --- |
| Collection | List | \- 순서를 유지하고 저장   \- 중복 저장 기능 | ArrayList, Vector, LinkedList |
| Set | \- 순서를 유지하지 않고 저장   \- 중복 저장 안됨 | HashSet, TreeSet |
| Map |  | \- 키와 값의 쌍으로 저장   \- 키는 중복 저장 안 됨 | HashMap, Hashtable   TreeMap, Properties |