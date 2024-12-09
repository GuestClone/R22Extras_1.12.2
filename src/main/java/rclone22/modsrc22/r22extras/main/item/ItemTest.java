package rclone22.modsrc22.r22extras.main.item;

import rclone22.modsrc22.r22extras.api.items.item.CustomItemTool;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import rclone22.modsrc22.r22extras.api.utils.entityinvuls.EntityInvul;

import java.util.Set;

public class ItemTest extends CustomItemTool implements EntityInvul.IEntityInvulI.IItemIndestruc {

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
