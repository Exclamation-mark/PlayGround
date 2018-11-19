package com.company.property;

import com.company.property.Item;

public class MagicPotion extends Item  {
    @Override
    public boolean disposable() {
        System.out.println("蓝瓶消失一个");
        return true;
    }

    @Override
    public void effect() {
        System.out.println("蓝瓶使用后 可以恢复魔法值");
    }
}
