package outerwildsmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class UpdateAfterTransformAction extends AbstractGameAction {
    private final AbstractCard cardToUpdate;

    private boolean glow;

    public UpdateAfterTransformAction(AbstractCard cardToUpdate, boolean glow) {
        this.cardToUpdate = cardToUpdate;
        this.glow = glow;
    }

    public UpdateAfterTransformAction(AbstractCard cardToUpdate) {
        this(cardToUpdate, true);
    }

    @Override
    public void update() {
        AbstractDungeon.player.hand.applyPowers();
        AbstractDungeon.player.hand.glowCheck();
        if (this.glow) {
            cardToUpdate.superFlash();
        }
        cardToUpdate.initializeDescription();
        this.isDone = true;
    }
}