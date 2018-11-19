package com.company;

import com.company.character.Mortal;

public abstract class Hero {
    private String name;
    private int BloodVolume;
    private int Armor;//护甲
    private int movingSpeed;

    public Hero() {
        this("sadasd",45,54,45);
    }

    public Hero(String name, int bloodVolume, int armor, int movingSpeed) {
        this.name = name;
        BloodVolume = bloodVolume;
        Armor = armor;
        this.movingSpeed = movingSpeed;
    }
    public void kill(Mortal m){
        m.die();
    }
    public abstract void attack();
    @Override
    protected void finalize() throws Throwable {
        System.out.println("正在被回收");
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", BloodVolume=" + BloodVolume +
                ", Armor=" + Armor +
                ", movingSpeed=" + movingSpeed +
                '}';
    }

    // 非静态内部类，只有一个外部类对象存在的时候，才有意义
    // 战斗成绩只有在一个英雄对象存在的时候才有意义
    class BattleScore {
        int kill;
        int die;
        int assit;

        public void legendary() {
            if (kill >= 8)
                System.out.println(name + "超神！");
            else
                System.out.println(name + "尚未超神！");
        }
    }
}
