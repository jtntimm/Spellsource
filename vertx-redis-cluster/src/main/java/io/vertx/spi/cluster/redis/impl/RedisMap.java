/*
 * Copyright (c) 2019 The original author or authors
 * ------------------------------------------------------
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 *
 *     The Eclipse Public License is available at
 *     http://www.eclipse.org/legal/epl-v10.html
 *
 *     The Apache License v2.0 is available at
 *     http://www.opensource.org/licenses/apache2.0.php
 *
 * You may elect to redistribute this code under either of these licenses.
 */
package io.vertx.spi.cluster.redis.impl;

import io.vertx.core.Vertx;
import org.redisson.api.MapOptions;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

class RedisMap<K, V> implements Map<K, V> {

	protected final Vertx vertx;
	protected final RMap<K, V> map;
	protected final String name;
	protected final RedissonClient redisson;

	public RedisMap(Vertx vertx, RedissonClient redisson, String name, Codec codec) {
		Objects.requireNonNull(redisson, "redisson");
		Objects.requireNonNull(name, "name");
		this.redisson = redisson;
		this.vertx = vertx;
		this.name = name;
		this.map = createMap(redisson, this.name, codec);
	}

	/**
	 * Here you can customize(override method) a "Codec"
	 *
	 * @see org.redisson.codec.JsonJacksonCodec
	 */
	protected RMap<K, V> createMap(RedissonClient redisson, String name, Codec codec) {
		if (codec == null) {
			return redisson.getMap(name, MapOptions.defaults());
		} else {
			return redisson.getMap(name, codec, MapOptions.defaults());
		}
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		Objects.requireNonNull(key);
		// Workaround for current HAManager implementation
		if (redisson.isShutdown() || redisson.isShuttingDown()) {
			return false;
		}
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	@Override
	public V get(Object key) {
		Objects.requireNonNull(key);
		return map.get(key);
	}

	@Override
	public V put(K key, V value) {
		if (redisson.isShutdown() || redisson.isShuttingDown()) {
			return null;
		}
		Objects.requireNonNull(key);
		Objects.requireNonNull(value);
		return map.put(key, value);
	}

	@Override
	public V remove(Object key) {
		return map.remove(key);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		map.putAll(m);
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public Set<K> keySet() {
		// "map.keySet()" <b>DOESN'T</b> fetch all of them as {@link #readAllKeySet()} does.
		return map.readAllKeySet();
	}

	@Override
	public Collection<V> values() {
		// "map.values()" <b>DOESN'T</b> fetch all of them as {@link #readAllValues()} does.
		return map.readAllValues();
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		// "map.entrySet()" <b>DOESN'T</b> fetch all of them as {@link #readAllEntrySet()} does.
		return map.readAllEntrySet();
	}

	@Override
	public String toString() {
		return super.toString() + "{name=" + name + "}";
	}
}
