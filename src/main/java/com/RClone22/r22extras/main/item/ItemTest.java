package com.RClone22.r22extras.main.item;

import com.RClone22.r22extras.api.items.item.CustomItemTool;
import com.RClone22.r22extras.api.utils.EntityInvul;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Set;

public class ItemTest extends CustomItemTool implements EntityInvul.IItemIndestruc {

    public ItemTest(float attackDamageIn, float attackSpeedIn, ToolMaterial materialIn, Set<Block> effectiveBlocksIn) {
        super(attackDamageIn, attackSpeedIn, materialIn, effectiveBlocksIn);
    }

    public ItemTest(ToolMaterial materialIn, Set<Block> effectiveBlocksIn) {
        super(materialIn, effectiveBlocksIn);
    }

    @Override
    public boolean isItemIndestruc(ItemStack stack)
    {
        return true;
    }


}
