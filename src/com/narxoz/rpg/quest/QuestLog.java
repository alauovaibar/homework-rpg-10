package com.narxoz.rpg.quest;

import java.util.ArrayList;
import java.util.List;

public class QuestLog {
    private final List<Quest> quests = new ArrayList<>();

    public void addQuest(Quest quest) {
        if (quest != null) {
            quests.add(quest);
        }
    }

    public List<Quest> snapshot() {
        return new ArrayList<>(quests);
    }

    public QuestIterator getOrderedIterator() {
        return new OrderedQuestIterator(this);
    }

    public QuestIterator getReverseIterator() {
        return new ReverseQuestIterator(this);
    }

    public QuestIterator getPriorityIterator(QuestPriority threshold) {
        return new PriorityQuestIterator(this, threshold);
    }
}