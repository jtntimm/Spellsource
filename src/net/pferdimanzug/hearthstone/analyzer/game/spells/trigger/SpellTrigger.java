package net.pferdimanzug.hearthstone.analyzer.game.spells.trigger;

import net.pferdimanzug.hearthstone.analyzer.game.Environment;
import net.pferdimanzug.hearthstone.analyzer.game.GameContext;
import net.pferdimanzug.hearthstone.analyzer.game.entities.Entity;
import net.pferdimanzug.hearthstone.analyzer.game.events.GameEvent;
import net.pferdimanzug.hearthstone.analyzer.game.events.GameEventType;
import net.pferdimanzug.hearthstone.analyzer.game.spells.Spell;
import net.pferdimanzug.hearthstone.analyzer.game.spells.SpellSource;
import net.pferdimanzug.hearthstone.analyzer.game.targeting.EntityReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpellTrigger implements Cloneable {
	private final static Logger logger = LoggerFactory.getLogger(SpellTrigger.class);

	private GameEventTrigger primaryTrigger;
	private GameEventTrigger secondaryTrigger;
	private Spell spell;
	private EntityReference hostReference;
	private final boolean oneTime;
	private boolean expired;
	private TriggerLayer layer = TriggerLayer.DEFAULT;

	public SpellTrigger(GameEventTrigger primaryTrigger, GameEventTrigger secondaryTrigger, Spell spell, boolean oneTime) {
		this.primaryTrigger = primaryTrigger;
		this.secondaryTrigger = secondaryTrigger;
		this.spell = spell;
		spell.setSource(SpellSource.SPELL_TRIGGER);
		this.oneTime = oneTime;
	}

	public SpellTrigger(GameEventTrigger trigger, Spell spell) {
		this(trigger, spell, false);
	}

	public SpellTrigger(GameEventTrigger trigger, Spell spell, boolean oneTime) {
		this(trigger, null, spell, oneTime);
	}

	public SpellTrigger clone() {
		try {
			SpellTrigger clone = (SpellTrigger) super.clone();
			clone.primaryTrigger = primaryTrigger.clone();
			if (secondaryTrigger != null) {
				clone.secondaryTrigger = secondaryTrigger.clone();
			}
			clone.spell = spell.clone();
			return clone;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public EntityReference getHostReference() {
		return hostReference;
	}

	public TriggerLayer getLayer() {
		return layer;
	}

	public int getOwner() {
		return primaryTrigger.getOwner();
	}

	protected Spell getSpell() {
		return spell;
	}

	public boolean interestedIn(GameEventType eventType) {
		boolean result = primaryTrigger.interestedIn() == eventType;
		if (secondaryTrigger != null) {
			result |= secondaryTrigger.interestedIn() == eventType;
		}
		return result;
	}

	public boolean isExpired() {
		return expired;
	}

	public void onAdd(GameContext context) {
	}

	protected void onFire(int ownerId, Spell spell, GameEvent event) {
		event.getGameContext().getLogic().castSpell(ownerId, spell);
	}

	public void onGameEvent(GameEvent event) {
		int ownerId = primaryTrigger.getOwner();
		Entity host = event.getGameContext().resolveSingleTarget(ownerId, hostReference);
		try {
			if (!expired && (triggerFires(primaryTrigger, event, host) || triggerFires(secondaryTrigger, event, host))) {
				if (oneTime) {
					expired = true;
				}
				
				event.getGameContext().getEnvironment().put(Environment.EVENT_TARGET, event.getEventTarget());
				onFire(ownerId, spell, event);
				event.getGameContext().getEnvironment().remove(Environment.EVENT_TARGET);
			}
		} catch (Exception e) {
			logger.error("SpellTrigger cannot be executed; GameEventTrigger: {} Spell: {}", primaryTrigger, spell);
			throw e;
		}
	}

	public void onRemove(GameContext context) {
	}

	public void reset() {
		expired = false;
	}

	public void setHost(Entity host) {
		this.hostReference = host.getReference();
		spell.setSourceEntity(hostReference);
	}

	protected void setLayer(TriggerLayer layer) {
		this.layer = layer;
	}

	public void setOwner(int playerIndex) {
		primaryTrigger.setOwner(playerIndex);
		if (secondaryTrigger != null) {
			secondaryTrigger.setOwner(playerIndex);
		}
	}

	@Override
	public String toString() {
		return "[SpellTrigger primaryTrigger=" + primaryTrigger + ", secondaryTrigger=" + secondaryTrigger + ", spell=" + spell
				+ ", hostReference=" + hostReference + ", oneTime=" + oneTime + ", expired=" + expired + ", layer=" + layer + "]";
	}

	private boolean triggerFires(GameEventTrigger trigger, GameEvent event, Entity host) {
		if (trigger == null) {
			return false;
		}
		if (trigger.interestedIn() != event.getEventType()) {
			return false;
		}
		return trigger.fire(event, host);
	}
	
	

}
