/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.meekers.plugins.treetoppler;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author jaredm
 */
public class TreeToppler extends JavaPlugin {

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new TreeTopperListener(), this);
    }
}
