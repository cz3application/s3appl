package com.czar.czarempire.model;

import com.czar.czarempire.net.Stream;

public interface EntityUpdateMask {
	void appendMask(Entity e, Stream stream);
}