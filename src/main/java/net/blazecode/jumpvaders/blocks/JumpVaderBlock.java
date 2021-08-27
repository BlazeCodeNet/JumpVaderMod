package net.blazecode.jumpvaders.blocks;

import net.blazecode.jumpvaders.JumpVaderMod;
import net.blazecode.jumpvaders.ifaces.BlockActionListener;
import net.blazecode.vanillify.api.VanillaUtils;
import net.blazecode.vanillify.api.block.ServerBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class JumpVaderBlock extends ServerBlock implements BlockActionListener
{
    public JumpVaderBlock(  )
    {
        super( Blocks.SEA_LANTERN, new Identifier( JumpVaderMod.MODID, "jumpvader_block" ), FabricBlockSettings.of( Material.STONE ), VanillaUtils.getText( "Jumper Vader", Formatting.LIGHT_PURPLE ) );
    }

    @Override
    public void scheduledTick( BlockState state , ServerWorld world , BlockPos pos , Random random )
    {
        if(world.isClient)
            return;

        world.getBlockTickScheduler().schedule( pos, this, 15 );
        ServerWorld w = (ServerWorld ) world;

        w.spawnParticles( ParticleTypes.END_ROD, pos.getX() + 0.5f, pos.getY() + 1.25f, pos.getZ() + 0.5f, 3, 0.25f, 0.25f, 0.25f, 0.01f );
    }

    @Override
    public void onBlockAdded( BlockState state , World world , BlockPos pos , BlockState oldState , boolean notify )
    {
        super.onBlockAdded( state , world , pos , oldState , notify );
        if(world.isClient)
            return;

        if(!world.getBlockTickScheduler().isTicking( pos, this ))
        {
           world.getBlockTickScheduler().schedule( pos, this, 5 );
        }
    }



    @Override
    public boolean onJump( BlockPos pos , ServerPlayerEntity player )
    {
        pos = pos.up();
        ServerWorld w = player.getServerWorld();
        int count = 0;

        while(count < 100 && pos.getY() < 256)
        {
            Block blk = w.getBlockState( pos ).getBlock();

            if(blk instanceof JumpVaderBlock)
            {
                final BlockPos tpPos = pos.up();

                if(w.getBlockState( tpPos ).getBlock().equals( Blocks.AIR ) && w.getBlockState( tpPos.up() ).getBlock().equals( Blocks.AIR ))
                {
                    player.requestTeleport( tpPos.getX() + 0.5f, tpPos.getY(), tpPos.getZ() + 0.5f );

                    w.playSound( null, tpPos, SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 0.5f, 1.5f );
                    w.spawnParticles( ParticleTypes.END_ROD, tpPos.getX() + 0.5f, tpPos.getY(), tpPos.getZ() + 0.5f, 10, 0, 0, 0, 0.25f );

                    return true;
                }
            }
            pos = pos.up();
            count++;
        }
        return false;
    }

    @Override
    public void onCrouch( BlockPos pos , ServerPlayerEntity player )
    {
        pos = pos.down();
        ServerWorld w = player.getServerWorld();
        int count = 0;

        while(count < 100 && pos.getY() < 256)
        {
            Block blk = w.getBlockState( pos ).getBlock();

            if(blk instanceof JumpVaderBlock)
            {
                final BlockPos tpPos = pos.up();

                if(w.getBlockState( tpPos ).getBlock().equals( Blocks.AIR ) && w.getBlockState( tpPos.up() ).getBlock().equals( Blocks.AIR ))
                {
                    player.requestTeleport( tpPos.getX() + 0.5f, tpPos.getY(), tpPos.getZ() + 0.5f );

                    w.playSound( null, tpPos, SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 0.5f, 1.5f );
                    w.spawnParticles( ParticleTypes.END_ROD, tpPos.getX() + 0.5f, tpPos.getY(), tpPos.getZ() + 0.5f, 10, 0, 0, 0, 0.25f );

                    return;
                }
            }
            pos = pos.down();
            count++;
        }

    }
}
