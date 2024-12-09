package rclone22.modsrc22.r22extras.main.event.initevents;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import rclone22.modsrc22.r22extras.api.utils.NBTList;
import rclone22.modsrc22.r22extras.main.Constantr22Extras;
import rclone22.modsrc22.r22extras.main.registry.InitRegistryHandler;

import java.util.List;


public class InitItemToolTip implements InitRegistryHandler.IInitEvents
{

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onItemTooltipOne(ItemTooltipEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            ItemStack stack = event.getItemStack();
            player.inventory.markDirty();
            if (!stack.isEmpty() && stack.hasTagCompound()) {
                NBTTagCompound tagCompound = stack.getTagCompound();
                if (tagCompound != null) {
                    if (tagCompound.hasKey(NBTList.ITEM_INDESTRUC) && tagCompound.getBoolean(NBTList.ITEM_INDESTRUC)) {
                        oneNbt(stack, event.getToolTip());
                        player.inventory.markDirty();
                    }
                    if (tagCompound.hasKey(NBTList.Unbreakable) && tagCompound.getBoolean(NBTList.Unbreakable)) {
                        twoNbt(stack, event.getToolTip());
                        player.inventory.markDirty();
                    }
                }
            }
        }
    }

    private static void oneNbt(ItemStack stack, List<String> tooltip) {
        tooltip.add(TextFormatting.BLUE + "Invulnerable Modification");
        tooltip.add(TextFormatting.DARK_GRAY + "NBT: " + NBTList.ITEM_INDESTRUC + " : Active");
    }

    private static void twoNbt(ItemStack stack, List<String> tooltip) {
        tooltip.add(TextFormatting.BLUE + "Invulnerable Modification");
        tooltip.add(TextFormatting.DARK_GRAY + "NBT: " + NBTList.Unbreakable + " : Active");
    }

}
