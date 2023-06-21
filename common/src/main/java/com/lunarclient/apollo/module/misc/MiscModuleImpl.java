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
package com.lunarclient.apollo.module.misc;

import com.lunarclient.apollo.common.ApolloEntity;
import com.lunarclient.apollo.common.v1.EntityId;
import com.lunarclient.apollo.misc.v1.DisplayVignetteMessage;
import com.lunarclient.apollo.misc.v1.FlipEntityMessage;
import com.lunarclient.apollo.misc.v1.OverrideRainbowSheepMessage;
import com.lunarclient.apollo.misc.v1.ResetFlipedEntityMessage;
import com.lunarclient.apollo.misc.v1.ResetRainbowSheepMessage;
import com.lunarclient.apollo.misc.v1.ResetVignetteMessage;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.player.AbstractApolloPlayer;
import com.lunarclient.apollo.player.ApolloPlayer;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.NonNull;

/**
 * Provides the misc module.
 *
 * @since 1.0.0
 */
public final class MiscModuleImpl extends MiscModule {

    @Override
    public void displayVignette(@NonNull ApolloPlayer viewer, @NonNull Vignette vignette) {
        ((AbstractApolloPlayer) viewer).sendPacket(DisplayVignetteMessage.newBuilder()
            .setResourceLocation(vignette.getResourceLocation())
            .setOpacity(vignette.getOpacity())
            .build());
    }

    @Override
    public void resetVignette(@NonNull ApolloPlayer viewer) {
        ((AbstractApolloPlayer) viewer).sendPacket(ResetVignetteMessage.getDefaultInstance());
    }

    @Override
    public void overrideRainbowSheep(@NonNull ApolloPlayer viewer, @NonNull List<ApolloEntity> sheepEntities) {
        Set<EntityId> sheepUuidsProto = sheepEntities.stream()
            .map(NetworkTypes::toProtobuf)
            .collect(Collectors.toSet());

        ((AbstractApolloPlayer) viewer).sendPacket(OverrideRainbowSheepMessage.newBuilder()
            .addAllEntityIds(sheepUuidsProto)
            .build());
    }

    @Override
    public void resetRainbowSheep(@NonNull ApolloPlayer viewer, @NonNull List<ApolloEntity> sheepEntities) {
        Set<EntityId> sheepUuidsProto = sheepEntities.stream()
            .map(NetworkTypes::toProtobuf)
            .collect(Collectors.toSet());

        ((AbstractApolloPlayer) viewer).sendPacket(ResetRainbowSheepMessage.newBuilder()
            .addAllEntityIds(sheepUuidsProto)
            .build());
    }

    @Override
    public void flipEntity(@NonNull ApolloPlayer viewer, @NonNull List<ApolloEntity> entities) {
        Set<EntityId> entityUuidsProto = entities.stream()
            .map(NetworkTypes::toProtobuf)
            .collect(Collectors.toSet());

        ((AbstractApolloPlayer) viewer).sendPacket(FlipEntityMessage.newBuilder()
            .addAllEntityIds(entityUuidsProto)
            .build());
    }

    @Override
    public void resetFlippedEntity(@NonNull ApolloPlayer viewer, @NonNull List<ApolloEntity> entities) {
        Set<EntityId> entityUuidsProto = entities.stream()
            .map(NetworkTypes::toProtobuf)
            .collect(Collectors.toSet());

        ((AbstractApolloPlayer) viewer).sendPacket(ResetFlipedEntityMessage.newBuilder()
            .addAllEntityIds(entityUuidsProto)
            .build());
    }

}
