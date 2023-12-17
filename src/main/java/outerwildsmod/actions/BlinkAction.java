package outerwildsmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import outerwildsmod.cards.AbstractCards.AbstractQuantumCard;
import outerwildsmod.util.RngController;

import java.util.Iterator;

public class BlinkAction extends AbstractGameAction {
    private final AbstractPlayer p;

    public BlinkAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        AbstractCard c;

        for (AbstractCard abstractCard : this.p.hand.group) {
            c = abstractCard;
            if (c instanceof AbstractQuantumCard && !((AbstractQuantumCard) c).linkedCards.isEmpty()) {
                AbstractQuantumCard newCard = ((AbstractQuantumCard) c).getRandomLinked();

                addToBot(new SwapCardsAction(c, newCard));

                newCard.superFlash();
                newCard.applyPowers();
            }
        }

        this.isDone = true;
    }

}
