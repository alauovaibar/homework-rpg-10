package com.narxoz.rpg.guild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Topic-based mediator for the Adventurers' Guild war council.
 */
public class GuildHall implements GuildMediator {
    private final Map<String, List<GuildMember>> membersByTopic = new HashMap<>();
    private int totalMessagesRouted = 0;
    private int totalNotificationsSent = 0;

    @Override
    public void register(GuildMember member) {
        if (member instanceof Captain) addSubscriber("COMMAND", member);
        if (member instanceof Scout) addSubscriber("SCOUTING", member);
        if (member instanceof Healer) addSubscriber("RECOVERY", member);
        if (member instanceof Quartermaster) addSubscriber("SUPPLY", member);

        addSubscriber("QUEST_REPORT", member);
    }

    @Override
    public void dispatch(String topic, GuildMember from, String payload) {
        List<GuildMember> recipients = subscribersFor(topic);
        totalMessagesRouted++;
        for (GuildMember member : recipients) {
            if (member != from) {
                member.receive(topic, from, payload);
                totalNotificationsSent++;
            }
        }
    }

    public int getMessagesRouted() { return totalMessagesRouted; }
    public int getNotificationsSent() { return totalNotificationsSent; }

    protected void addSubscriber(String topic, GuildMember member) {
        membersByTopic.computeIfAbsent(topic, key -> new ArrayList<>()).add(member);
    }

    protected List<GuildMember> subscribersFor(String topic) {
        return membersByTopic.getOrDefault(topic, List.of());
    }
}
