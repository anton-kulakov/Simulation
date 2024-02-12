package com.anton_kulakov.action;

import com.anton_kulakov.entity.Tree;

public class TreeCreationEntity extends EntityCreationAction<Tree> {
    @Override
    int getEntityAmount() {
        return random.nextInt(4,11);
    }

    @Override
    Tree generateEntity() {
        return new Tree();
    }
}
