package com.company.property;

public class LifePotion extends Item{
    @Override
    public boolean disposable() {
        System.out.println("血瓶消失一个");
        return true;
    }

    @Override
    public void effect() {
        System.out.println("血瓶使用后  可以恢复生命值");
    }
}
