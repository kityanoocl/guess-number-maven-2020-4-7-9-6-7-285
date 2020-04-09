package com.oocl;

import java.util.*;

public class RandomAnswerGenerator implements AnswerGenerator{
    final int ANSWER_LENGTH = 4;
    public HashMap<Character, Integer> generate() {
        HashMap<Character, Integer> answer = new HashMap<Character, Integer>();
        List<Character> characterList = new ArrayList<Character>();
        for (Character integerCharacter = '0'; integerCharacter <= '9'; integerCharacter++)
        {
            characterList.add(integerCharacter);
        }
        Collections.shuffle(characterList);

        characterList = characterList.subList(0, ANSWER_LENGTH);

        for (Character digit : characterList) {
            answer.put(digit, characterList.indexOf(digit));
        }
        return answer;
    }
}
