/*
 * This file is part of BlueMap, licensed under the MIT License (MIT).
 *
 * Copyright (c) Blue (Lukas Rieger) <https://bluecolored.de>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package de.bluecolored.bluemap.core.world;

import de.bluecolored.bluemap.core.util.ConfigUtils;
import de.bluecolored.bluemap.core.util.math.Color;
import org.spongepowered.configurate.ConfigurationNode;

public class Biome {

	public static final Biome DEFAULT = new Biome();
	
	private String id = "ocean";
	private int numeralId = 0;
	private float humidity = 0.5f;
	private float temp = 0.5f;
	private Color waterColor = new Color().set(4159204).premultiplied();

	private Color overlayFoliageColor = new Color().premultiplied();
	private Color overlayGrassColor = new Color().premultiplied();
	
	private Biome() {}
	
	public Biome(String id, int numeralId, float humidity, float temp, Color waterColor) {
		this.id = id;
		this.numeralId = numeralId;
		this.humidity = humidity;
		this.temp = temp;
		this.waterColor = waterColor;
	}
	
	public Biome(String id, int numeralId, float humidity, float temp, Color waterColor, Color overlayFoliageColor, Color overlayGrassColor) {
		this (id, numeralId, humidity, temp, waterColor);
		
		this.overlayFoliageColor = overlayFoliageColor;
		this.overlayGrassColor = overlayGrassColor;
	}

	public String getId() {
		return id;
	}

	public int getNumeralId() {
		return numeralId;
	}

	public float getHumidity() {
		return humidity;
	}

	public float getTemp() {
		return temp;
	}

	public Color getWaterColor() {
		return waterColor;
	}

	public Color getOverlayFoliageColor() {
		return overlayFoliageColor;
	}

	public Color getOverlayGrassColor() {
		return overlayGrassColor;
	}
	
	public static Biome create(String id, ConfigurationNode node) {
		Biome biome = new Biome();
		
		biome.id = id;
		biome.numeralId = node.node("id").getInt(biome.numeralId);
		biome.humidity = node.node("humidity").getFloat(biome.humidity);
		biome.temp = node.node("temp").getFloat(biome.temp);
		try { biome.waterColor = new Color().set(ConfigUtils.readColorInt(node.node("watercolor"))).premultiplied(); 				} catch (NumberFormatException ignored) {}
		try { biome.overlayFoliageColor = new Color().set(ConfigUtils.readColorInt(node.node("foliagecolor"))).premultiplied(); 	} catch (NumberFormatException ignored) {}
		try { biome.overlayGrassColor = new Color().set(ConfigUtils.readColorInt(node.node("grasscolor"))).premultiplied(); 		} catch (NumberFormatException ignored) {}
		
		return biome;
	}
	
	@Override
	public String toString() {
		return "Biome{" +
			   "id='" + id + '\'' +
			   ", numeralId=" + numeralId +
			   ", humidity=" + humidity +
			   ", temp=" + temp +
			   ", waterColor=" + waterColor +
			   ", overlayFoliageColor=" + overlayFoliageColor +
			   ", overlayGrassColor=" + overlayGrassColor +
			   '}';
	}
	
}
