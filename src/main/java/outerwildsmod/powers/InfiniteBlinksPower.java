package outerwildsmod.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import outerwildsmod.cards.common.skills.Blink;

import static outerwildsmod.outerwildsmod.makeID;

public class InfiniteBlinksPower extends BasePower implements CloneablePowerInterface {

    public static final String POWER_ID = makeID("InfiniteBlinks");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public InfiniteBlinksPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }
    public void updateDescription() {
        if (this.amount > 1) {
            this.description = String.format("%s %d %s", DESCRIPTIONS[0], this.amount, DESCRIPTIONS[1]);
        } else {
            this.description = String.format("%s %d %s", DESCRIPTIONS[0], this.amount, DESCRIPTIONS[2]);
        }
    }
    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            this.addToBot(new MakeTempCardInHandAction(new Blink(), this.amount, false));
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new InfiniteBlinksPower(owner, amount);
    }
}
