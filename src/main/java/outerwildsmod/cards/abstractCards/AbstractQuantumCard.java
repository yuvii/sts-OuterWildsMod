package outerwildsmod.cards.abstractCards;
import com.evacipated.cardcrawl.mod.stslib.patches.HitboxRightClick;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import outerwildsmod.actions.SwapCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static outerwildsmod.outerwildsmod.makeID;

public abstract class AbstractQuantumCard extends AbstractModdedCard {
    private AbstractGameAction action;
    private static boolean looping;

    public AbstractQuantumCard(String id, String img, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, img, cost, type, color, rarity, target);
    }

    protected void setLinkedCard(AbstractQuantumCard linkedCard) {
        if (linkedCard != null) {
            this.cardsToPreview = linkedCard;
            this.cardsToPreview.cardsToPreview = this;
        }
    }

    @Override
    public void upgrade() {
        if (cardsToPreview != null && !looping) {
            looping = true;
            cardsToPreview.upgrade();
            looping = false;
        }
    }

    public void onRightClick() {
        if (canSwap() && action == null && cardsToPreview != null) {
            CardCrawlGame.sound.play("CARD_SELECT", 0.1F);
            action = new SwapCardsAction(this, cardsToPreview);
            this.addToTop(action);
        }
    }

    @Override
    public void update() {
        super.update();
        if (AbstractDungeon.player != null) {
            clickUpdate();
        }
        if (action != null && action.isDone) {
            action = null;
        }
    }

    public void clickUpdate() {
        if (!AbstractDungeon.isScreenUp && HitboxRightClick.rightClicked.get(this.hb) && !AbstractDungeon.actionManager.turnHasEnded) {
            onRightClick();
        }
    }

    public boolean canSwap() {
        return true;
    }

    public void onSwapOut() {}

    public void onSwapIn() {}
}