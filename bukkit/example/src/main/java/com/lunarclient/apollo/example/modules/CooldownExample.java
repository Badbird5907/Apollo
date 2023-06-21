/*
 * This file is part of Apollo, licensed under the MIT License.
 *
 * Copyright (c) 2023 Moonsworth
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.lunarclient.apollo.example.modules;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.icon.ItemStackIcon;
import com.lunarclient.apollo.module.cooldown.Cooldown;
import com.lunarclient.apollo.module.cooldown.CooldownModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.time.Duration;
import java.util.Optional;
import org.bukkit.entity.Player;

public class CooldownExample {

    private final CooldownModule cooldownModule = Apollo.getModuleManager().getModule(CooldownModule.class);

    public void displayCooldownExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        apolloPlayerOpt.ifPresent(apolloPlayer -> {
            this.cooldownModule.displayCooldown(apolloPlayer, Cooldown.builder()
                .name("enderpearl-cooldown")
                .duration(Duration.ofSeconds(15))
                .icon(ItemStackIcon.builder()
                    .itemName("ENDER_PEARL")
                    .build()
                )
                .build()
            );
        });
    }

    public void removeCooldownExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        // Removing the cooldown with the name "enderpearl-cooldown" from the player
        apolloPlayerOpt.ifPresent(apolloPlayer -> this.cooldownModule.removeCooldown(apolloPlayer, "enderpearl-cooldown"));
    }

    public void resetCooldownsExample(Player viewer) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(viewer.getUniqueId());

        // Resetting all cooldowns for the player
        apolloPlayerOpt.ifPresent(this.cooldownModule::resetCooldowns);
    }

}
