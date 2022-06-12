package com.oop;

//클래스 정의
class Npc {
    //필드 - 데이터
    String name;
    int hp;
    //메서드 - 동작
    void say() {
        System.out.println("안녕하세요.");
    }
}

public class MyNpc {

    public static void main(String[] args) {
        Npc person1 = new Npc();

        person1.name = "경비";
        person1.hp = 100;

        System.out.println(person1.name + ":" +
    person1.hp);
        person1.say();
    }
}
