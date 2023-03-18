package outerwildsmod.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import static outerwildsmod.outerwildsmod.logger;
import static outerwildsmod.outerwildsmod.makeID;

public class DoubleTimePower extends BasePower implements CloneablePowerInterface {

    public static final String POWER_ID = makeID("DoubleTimePower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public int cardsPlayed;

    private int modifier;

    public DoubleTimePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
        this.cardsPlayed = AbstractDungeon.actionManager.cardsPlayedThisCombat.size() + 1;
        this.modifier    = 2;
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        this.cardsPlayed += this.modifier;
        this.modifier    *= 2;
    }

    public void updateDescription() {
        this.description = String.format("%s %d %s", DESCRIPTIONS[0], this.modifier, DESCRIPTIONS[1]);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        super.onUseCard(card, action);
        this.cardsPlayed += this.modifier;
    }

    @Override
    public AbstractPower makeCopy() {
        return new DoubleTimePower(owner, amount);
    }
}
