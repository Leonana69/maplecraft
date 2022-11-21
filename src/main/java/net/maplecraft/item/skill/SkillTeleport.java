package net.maplecraft.item.skill;

import net.maplecraft.utils.JobCategory;
import net.maplecraft.utils.SkillBaseData;
import net.maplecraft.utils.SkillItem;

public class SkillTeleport extends SkillItem {
    public static String itemName = "skill_teleport";
    public SkillTeleport() {
        super(itemName, new SkillBaseData()
                .jobReq(JobCategory.MAGICIAN)
                .manaCost(2, 0.2));
    }
}
