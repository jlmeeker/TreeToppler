/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.meekers.plugins.treetoppler;

import java.util.Random;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;

/**
 *
 * @author jaredm
 */
class TreeTopperListener implements Listener {

    public int brad = 3;

    TreeToppler plugin;

    public TreeTopperListener(TreeToppler plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onTreeChop(BlockDamageEvent event) {
        Player p = event.getPlayer();
        Block bd = event.getBlock();
        Block curblock;
        Random rand = new Random();
        int maxrand = this.plugin.getConfig().getInt("maxrand");
        boolean oneshottopple = rand.nextInt(maxrand) == 0;

        //p.sendMessage(bd.getType().toString());
        if (bd.getType() == Material.LOG && oneshottopple == true) {
            //p.sendMessage("one shot topple triggered");
            if (bd.getRelative(0, -1, 0).getType() == Material.DIRT) {
                //p.sendMessage("Destroying tree");
                this.destroyTree(p, bd);
            }
        }
    }

    private void destroyTree(Player inplayer, Block inblock) {
        Block relative;
        boolean climb = false;
        for (int x = -this.brad; x <= this.brad; x++) {
            for (int z = -this.brad; z <= this.brad; z++) {
                relative = inblock.getRelative(x, 0, z);
                if (relative.getType() == Material.LOG || relative.getType() == Material.LEAVES) {
                    if (relative.getType() == Material.LOG) {
                        //inplayer.sendMessage("found log");
                        climb = true;
                        if (relative == inblock) {
                            continue;
                        }
                    }
                   // inplayer.sendMessage("Breaking a block of " + relative.getType().toString());
                    relative.breakNaturally();
                }
            }
        }
        inblock.breakNaturally();
        if (climb == true) {
            Block nextblock = inblock.getRelative(0, 1, 0);
            this.destroyTree(inplayer, nextblock);
        }
    }
}
