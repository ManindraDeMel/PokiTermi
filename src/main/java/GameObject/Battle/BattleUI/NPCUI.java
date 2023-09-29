package GameObject.Battle.BattleUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class NPCUI {


    /**
     * chose random dialogue when talk to NPC
     *
     * @throws IOException if there's an error during rendering.
     * @author Zhangheng Xu
     */
    public static String NPCTalk() throws IOException {
        // create an array list of dialogues
        ArrayList<String> dialogueChoices= new ArrayList<>();
        dialogueChoices.add("Ohayo, professor Oak!");
        dialogueChoices.add("Ah ha!");
        dialogueChoices.add("Nice weather today.");
        dialogueChoices.add("Where is the best chest?");
        dialogueChoices.add("Nice to see a fellow adventurer.");
        dialogueChoices.add("Have you visited the nearby village yet?");
        dialogueChoices.add("I've heard rumors of a powerful enemy lurking nearby.");
        dialogueChoices.add("The weather in this land is quite unpredictable, isn't it?");

        // random choice one of dialogues
        Random random = new Random();
        int randomIndex = random.nextInt(dialogueChoices.size());
        return dialogueChoices.get(randomIndex);
    }
}
