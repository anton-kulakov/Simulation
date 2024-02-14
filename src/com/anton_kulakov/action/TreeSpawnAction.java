package com.anton_kulakov.action;

import com.anton_kulakov.entity.Tree;

public class TreeSpawnAction extends EntitySpawnAction<Tree> {
    @Override
    int getEntityAmount() {
        return random.nextInt(4,8);
    }

    @Override
    Tree generateEntity() {
        return new Tree();
    }
}
