package rclone22.modsrc22.r22extras.api.potions;

import net.minecraft.potion.Potion;

public class CustomPotion extends Potion
{


    public CustomPotion(boolean isBadEffectIn, int liquidColorIn, int x, int y) {
        super(isBadEffectIn, liquidColorIn);
        this.setIconIndex(x, y);
    }

}
