package com.company.character;

import com.company.Hero;

public class APHero extends Hero implements AP,Mortal {
    @Override
    public void magicAttack() {
        System.out.println("进行魔法攻击");
    }

    @Override
    public void die() {
        System.out.println("APHero死掉了");
    }

    @Override
    public void attack() {

    }
}
