package outerwildsmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.TransformCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import outerwildsmod.cards.AbstractCards.AbstractQuantumCard;

public class SwapCardsAction extends AbstractGameAction {
    private final AbstractCard toReplace;
    private final AbstractCard newCard;

    private boolean effect;

    public SwapCardsAction(AbstractCard toReplace, AbstractCard newCard, boolean effect) {
        this.actionType = ActionType.SPECIAL;
        this.duration = Settings.ACTION_DUR_FASTER;
        this.toReplace = toReplace;
        this.newCard = newCard;
        this.effect = effect;
    }

    public SwapCardsAction(AbstractCard toReplace, AbstractCard newCard) {
        this(toReplace, newCard, true);
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        int index = 0;
        boolean found = false;
        for (AbstractCard card : p.hand.group)
        {
            if (card == toReplace) {
                found = true;
                break;
            }
            index++;
        }
        if(found && toReplace != null) {
            if (toReplace instanceof AbstractQuantumCard && newCard instanceof AbstractQuantumCard) {

                if (!((AbstractQuantumCard) toReplace).canSwap()) {
                    this.isDone = true;
                    return;
                }

                ((AbstractQuantumCard) toReplace).onSwapOut();
                ((AbstractQuantumCard) newCard).onSwapIn();

                ((AbstractQuantumCard) newCard).linkCard((AbstractQuantumCard) toReplace);
                for (AbstractQuantumCard qc : ((AbstractQuantumCard) toReplace).linkedCards) {
                    if (qc.getClass() != newCard.getClass()) {
                        ((AbstractQuantumCard) newCard).linkCard(qc);
                    }
                }
            }
//            newCard.cardsToPreview = toReplace.makeStatEquivalentCopy();
            newCard.applyPowers();

//            newCard.cardsToPreview.applyPowers();

            if (AbstractDungeon.player.hoveredCard == toReplace) {
                AbstractDungeon.player.releaseCard();
            }
            AbstractDungeon.actionManager.cardQueue.removeIf(q -> q.card == toReplace);
            this.addToTop(new UpdateAfterTransformAction(newCard, this.effect));
            this.addToTop(new TransformCardInHandAction(index, newCard));
            //p.hand.group.remove(index);
            //p.hand.group.add(index, newCard);
        }
        this.isDone = true;
    }
}