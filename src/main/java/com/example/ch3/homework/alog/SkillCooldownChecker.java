package com.example.ch3.homework.alog;

public class SkillCooldownChecker {
    public static void main(String[] args) {
        String[][] skills = {
                {"Fireball", "-3"},
                {"Heal", "5"},
                {"Shield", "0"}
        };

        System.out.println("사용 가능한 스킬: " + UsableSkills(skills));
    }

    public static String UsableSkills(String[][] skills) {
        StringBuilder usableSkill = new StringBuilder();

        for (String[] skill : skills) {
            String skillName = skill[0];
            int cooldown = Integer.parseInt(skill[1]);

            if (cooldown <= 0) {
                if (usableSkill.length() > 0) {
                    usableSkill.append(", ");
                }
                usableSkill.append(skillName);
            }
        }

        return usableSkill.toString();
    }
}

