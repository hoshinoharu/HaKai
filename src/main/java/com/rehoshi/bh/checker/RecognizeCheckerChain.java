package com.rehoshi.bh.checker;

import com.rehoshi.bh.domain.RecognizeResult;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class RecognizeCheckerChain implements RecognizeChecker {

    private List<RecognizeChecker> recognizeCheckers;

    private RecognizeCheckerChain(){};

    @Override
    public boolean check(RecognizeResult $) {
        boolean check = true ;
        //空链直接返回失败
        if(recognizeCheckers == null || recognizeCheckers.isEmpty()){
            check = false ;
        }else {
            //所有的checker都返回true 才代表识别完成
            for (Iterator<RecognizeChecker> iterator = recognizeCheckers.iterator();
                 iterator.hasNext() && check;){
                RecognizeChecker next = iterator.next();
                check = next.check($);
            }
        }
        return check;
    }

    public static RecognizeCheckerChain of(RecognizeChecker... checkers){
        RecognizeCheckerChain chain = new RecognizeCheckerChain();
        chain.recognizeCheckers = Arrays.asList(checkers) ;
        return chain ;
    }
}
