package com.narxoz.rpg.council;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.guild.GuildHall;
import com.narxoz.rpg.guild.GuildMediator;
import com.narxoz.rpg.quest.Quest;
import com.narxoz.rpg.quest.QuestIterator;
import com.narxoz.rpg.quest.QuestLog;
import com.narxoz.rpg.quest.QuestPriority;

import java.util.List;

/**
 * Orchestrates a planning session that uses both Iterator and Mediator.
 */
public class CouncilEngine {
    public CouncilRunResult runCouncil(List<Hero> party, QuestLog questLog, GuildMediator hall) {
        int questsTraversed = 0;

        QuestIterator ordered = questLog.getOrderedIterator();
        while (ordered.hasNext()) {
            Quest q = ordered.next();
            questsTraversed++;
            hall.dispatch("QUEST_REPORT", null, "New quest: " + q.getTitle());
        }

        QuestIterator priority = questLog.getPriorityIterator(QuestPriority.HIGH);
        while (priority.hasNext()) {
            Quest q = priority.next();
            questsTraversed++;
            hall.dispatch("COMMAND", null, "URGENT: " + q.getTitle());
        }

        GuildHall gh = (GuildHall) hall;
        return new CouncilRunResult(
                questsTraversed,
                gh.getMessagesRouted(),
                gh.getNotificationsSent()
        );
    }
}