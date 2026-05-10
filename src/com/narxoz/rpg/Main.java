package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.council.CouncilEngine;
import com.narxoz.rpg.council.CouncilRunResult;
import com.narxoz.rpg.guild.*;
import com.narxoz.rpg.quest.Quest;
import com.narxoz.rpg.quest.QuestLog;
import com.narxoz.rpg.quest.QuestPriority;

import java.util.List;

/**
 * Entry point for Homework 10 — The Adventurers' Guild: Iterator + Mediator.
 *
 * The scaffold prints the banner only; students fill in the guild demo.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 10 Demo: Iterator + Mediator ===");

        QuestLog questLog = new QuestLog();

        questLog.addQuest(new Quest("Clear Cellar", QuestPriority.LOW, 100, false));
        questLog.addQuest(new Quest("Kill Dragon", QuestPriority.HIGH, 5000, true));
        questLog.addQuest(new Quest("Find Ring", QuestPriority.NORMAL, 500, false));
        questLog.addQuest(new Quest("Scout Forest", QuestPriority.LOW, 50, false));
        questLog.addQuest(new Quest("Defend Gates", QuestPriority.HIGH, 2000, true));

        GuildHall hall = new GuildHall();
        Captain cap = new Captain("Aragorn", hall);
        Scout scout = new Scout("Legolas", hall);
        Healer healer = new Healer("Elrond", hall);
        Quartermaster qm = new Quartermaster("Gimli", hall);

        hall.register(cap);
        hall.register(scout);
        hall.register(healer);
        hall.register(qm);

        CouncilEngine engine = new CouncilEngine();
        List<Hero> party = List.of(
                new Hero("Frodo", 50, 5, 10),
                new Hero("Sam", 70, 8, 15)
        );

        CouncilRunResult result = engine.runCouncil(party, questLog, hall);

        System.out.println("--- Results ---");
        System.out.println("Quests Traversed: " + result.getQuestsTraversed());
        System.out.println("Messages Routed: " + result.getMessagesRouted());
        System.out.println("Total Notifications: " + result.getMembersNotified());
    }
}