package com.company.character;

import com.company.Hero;

public class Support extends Hero implements Healer {
    @Override
    public void heal() {
        System.out.println("执行了治疗");
    }

    @Override
    public void attack() {

    }
}
