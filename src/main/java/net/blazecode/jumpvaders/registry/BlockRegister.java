package net.blazecode.jumpvaders.registry;

import net.blazecode.jumpvaders.JumpVaderMod;
import net.blazecode.jumpvaders.blocks.JumpVaderBlock;
import net.blazecode.vanillify.api.item.ServerBlockItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegister
{
    public static void init()
    {
        Registry.register( Registry.BLOCK, JUMP_VADER_BLOCK.getId(), JUMP_VADER_BLOCK);

        Registry.register( Registry.ITEM, JUMP_VADER_BLOCK.getId() ,new ServerBlockItem( JUMP_VADER_BLOCK, Items.SEA_LANTERN, JUMP_VADER_BLOCK.getId(), new FabricItemSettings().maxCount( 64 ) , JUMP_VADER_BLOCK.getDisplayName() ) );
    }

    public static final JumpVaderBlock JUMP_VADER_BLOCK = new JumpVaderBlock();
}
