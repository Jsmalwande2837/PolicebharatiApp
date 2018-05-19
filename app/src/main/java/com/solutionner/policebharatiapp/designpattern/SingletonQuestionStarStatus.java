package com.solutionner.policebharatiapp.designpattern;


import java.util.ArrayList;

/**
 * Created by satishmarkad on 17/01/17.
 */

public class SingletonQuestionStarStatus {

    private static SingletonQuestionStarStatus gsonInstance;

    public ArrayList<GetQuestionStarModel> question_star=new ArrayList<>();


    public static SingletonQuestionStarStatus getGsonInstance() {

        if (gsonInstance == null) {
            gsonInstance = new SingletonQuestionStarStatus();
        }
        return gsonInstance;
    }
}
