package io.github.seggan.slimefunwarfare.items.blocks;

import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.RecipeTypes;
import io.github.seggan.slimefunwarfare.lists.items.Items;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Meteor extends SlimefunItem {

    public Meteor(SlimefunItemStack item) {
        super(Categories.RESOURCES, item, RecipeTypes.SPACE, null);

        addItemHandler(new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(BlockBreakEvent e, ItemStack itemStack, List<ItemStack> drops) {
                if (itemStack.containsEnchantment(Enchantment.SILK_TOUCH)) {
                    drops.add(getItem().clone());
                } else {
                    SlimefunItemStack stack = getItem().equals(Items.OSMIUM_METEOR) ? Items.OSMIUM_DUST : Items.SEGGANESSON;

                    drops.add(stack.clone());
                    int fortune = itemStack.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
                    if (fortune == 0) return;

                    for (int i = 0; i < fortune; i++) {
                        if (ThreadLocalRandom.current().nextBoolean()) {
                            drops.add(stack.clone());
                        }
                    }
                }
            }
        });
    }

    @Override
    public Collection<ItemStack> getDrops() {
        return new ArrayList<>();
    }
}
