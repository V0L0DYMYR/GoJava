package com.tyomsky.kickstarter.model;

import java.util.ArrayList;
import java.util.Map;

public class QuestionsAndAnswers {
    private int id;
    private Map<Integer, ArrayList<String>> qnA;

    public QuestionsAndAnswers(int id, Map<Integer, ArrayList<String>> qnA) {
        this.id = id;
        this.qnA = qnA;
    }

    public Map<Integer, ArrayList<String>> getQnA() {
        return qnA;
    }

    public void addAnswer(int index, String answer) {
        qnA.get(index).add(answer);
    }

}
