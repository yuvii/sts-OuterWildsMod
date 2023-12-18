package outerwildsmod.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import outerwildsmod.cards.AbstractCards.AbstractQuantumCard;

import static outerwildsmod.outerwildsmod.makeID;

public class SuperPositionPower extends BasePower implements CloneablePowerInterface {


    public static final String POWER_ID = makeID("SuperPositionPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    private int cardsSuperedThisTurn;

    public SuperPositionPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void atStartOfTurn() {
        this.cardsSuperedThisTurn = 0;
    }

    public int quantumCardsPlayedThisTurn() {
        int total = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (c instanceof AbstractQuantumCard) {
                total += 1;
            }
        }

        return total;
    }

    public boolean isActive() {
        return ( this.amount > 0 && this.quantumCardsPlayedThisTurn() - this.cardsSuperedThisTurn <= this.amount );
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof AbstractQuantumCard && !card.purgeOnUse && this.isActive()) {
            ++this.cardsSuperedThisTurn;
            this.flash();
            AbstractMonster m = null;
            if (action.target != null) {
                m = (AbstractMonster)action.target;
            }

            for (AbstractQuantumCard qc : ((AbstractQuantumCard) card).linkedCards) {
                this.playLinkedCard(qc, m);
            }
        }
    }

    public void playLinkedCard(AbstractQuantumCard card, AbstractMonster m) {
        AbstractCard tmp = card.makeSameInstanceOf();
        AbstractDungeon.player.limbo.addToBottom(tmp);
        tmp.current_x = card.current_x;
        tmp.current_y = card.current_y;
        tmp.target_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
        tmp.target_y = (float)Settings.HEIGHT / 2.0F;
        if (m != null) {
            tmp.calculateCardDamage(m);
        }

        tmp.purgeOnUse = true;
        AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
    }

    @Override
    public AbstractPower makeCopy() {
        return new SuperPositionPower(owner, amount);
    }
}
