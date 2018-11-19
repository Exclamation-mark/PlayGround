package com.company.character;

import com.company.Hero;

public class ADAPHero extends Hero implements AP,AD,Mortal {
    @Override
    public void physicalAttack() {
        System.out.println("进行物理攻击");
    }

    @Override
    public void magicAttack() {
        System.out.println("进行魔法攻击");
    }

    @Override
    public void die() {
        System.out.println("AdApHero死掉了");
    }

    @Override
    public void attack() {

    }
}
