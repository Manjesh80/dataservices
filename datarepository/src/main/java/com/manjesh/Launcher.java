package com.manjesh;

import com.manjesh.operations.BasicOperation;
import com.manjesh.operations.CollectionOperations;
import com.manjesh.operations.RelationalOperations;

import java.util.Random;

/**
 * Author: mg153v (Manjesh Gowda). Creation Date: 3/3/2017.
 */
public class Launcher {

    private static Random rnd = new Random(100);

    public static void main(String[] args) {
        //BasicOperation.addJointAccount();
        //CollectionOperations.storeHashmap();
        RelationalOperations.getManyToManyRelation();
    }
}
