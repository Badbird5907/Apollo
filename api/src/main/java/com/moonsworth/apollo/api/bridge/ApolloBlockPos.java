package com.moonsworth.apollo.api.bridge;

import com.moonsworth.apollo.api.protocol.BlockPosition;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public record ApolloBlockPos(int x, int y, int z) {

    public BlockPosition toProto() {
        return BlockPosition.newBuilder().setX(this.x).setY(this.y).setZ(this.z).build();
    }
}
