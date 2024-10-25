package com.RClone22.r22extras.api.items.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemTool;

import java.util.Set;

public class CustomItemTool extends ItemTool
{

    public CustomItemTool(float attackDamageIn, float attackSpeedIn, ToolMaterial materialIn, Set<Block> effectiveBlocksIn) {
        super(attackDamageIn, attackSpeedIn, materialIn, effectiveBlocksIn);
    }

    public CustomItemTool(ToolMaterial materialIn, Set<Block> effectiveBlocksIn) {
        super(materialIn, effectiveBlocksIn);
    }
}
