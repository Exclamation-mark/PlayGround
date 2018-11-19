package com.company.character;

import com.company.Hero;

public class ADHero extends Hero implements AD,Mortal {
    @Override
    public void physicalAttack() {
        System.out.println("进行物理攻击");
    }

    @Override
    public void die() {
        System.out.println("ADHero死掉了");
    }

    @Override
    public void attack() {

    }
}
