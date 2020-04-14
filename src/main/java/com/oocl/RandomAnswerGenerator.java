package com.oocl;

import java.util.*;

public class RandomAnswerGenerator implements AnswerGenerator{
    private static final int ANSWER_LENGTH = 4;
    private static final char STARTING_CHARACTER = '0';
    public static final char ENDING_CHARACTER = '9';

    public HashMap<Character, Integer> generate() {
        HashMap<Character, Integer> answer = new HashMap<Character, Integer>();
        List<Character> characterList = new ArrayList<Character>();
        for (char integerCharacter = STARTING_CHARACTER; integerCharacter <= ENDING_CHARACTER; integerCharacter++)
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
