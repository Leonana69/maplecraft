package net.maplecraft.procedures;

import net.maplecraft.utils.AllSkillList;
import net.maplecraft.utils.SkillDamageInstance;
import net.maplecraft.utils.SkillItem;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

@Mod.EventBusSubscriber
public class DelayedDamageHandler {
    public static Queue<SkillDamageInstance> damageQueue = new PriorityQueue<>(new SkillDamageInstance.SkillDamageInstanceComparator());
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (!damageQueue.isEmpty()) {
            SkillDamageInstance instance = damageQueue.peek();
            if (instance.tick <= event.player.level.getGameTime()) {
                instance = damageQueue.remove();
                SkillItem skill = (SkillItem) AllSkillList.SKILLS.get(instance.skillID).asItem();
                skill.dealDamage(event.player, instance.targets);
                instance.attackCount -= 1;
                if (instance.attackCount > 0) {
                    instance.tick += instance.delay;
                    damageQueue.add(instance);
                }
            }
        }
    }
}
