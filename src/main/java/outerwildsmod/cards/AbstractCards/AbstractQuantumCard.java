package outerwildsmod.cards.AbstractCards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import outerwildsmod.actions.SwapCardsAction;
import outerwildsmod.powers.SuperPositionPower;
import outerwildsmod.util.CardInfo;
import outerwildsmod.util.RngController;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static outerwildsmod.outerwildsmod.makeID;
import static outerwildsmod.util.TextureLoader.getCardTextureString;

abstract public class AbstractQuantumCard extends CustomCardMultiPreview {

    public boolean isLocked = false;

    public boolean isMainCard = false;

    private String initialDescription;

    public AbstractQuantumCard(String id,
                               String img,
                               int cost,
                               CardType type,
                               CardColor color,
                               CardRarity rarity,
                               CardTarget target) {
        super(id, languagePack.getCardStrings(id).NAME, img, cost, languagePack.getCardStrings(id).DESCRIPTION, type, color, rarity, target);
        this.initialDescription = languagePack.getCardStrings(id).DESCRIPTION;
    }
    public AbstractQuantumCard(CardInfo cardInfo) {
        this(cardInfo.baseId, getCardTextureString(makeID(cardInfo.baseId), cardInfo.cardType), cardInfo.baseCost, cardInfo.cardType, cardInfo.cardColor, cardInfo.cardRarity, cardInfo.cardTarget);
    }

    public void linkCard(AbstractQuantumCard linkedCard) {
        if (!this.linkedCards.contains(linkedCard)) {
            this.linkedCards.add(linkedCard);
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            for (AbstractQuantumCard c : this.linkedCards) {
                this.linkedCards.remove(c);
                c.upgrade();
                this.linkedCards.add(c);
            }
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.isLocked = true;
        this.showPreview = false;
        this.useCard(abstractPlayer, abstractMonster);
    }

    public boolean canSwap() { return !this.isLocked;  }

    abstract public void useCard(AbstractPlayer p, AbstractMonster m);

    abstract public void onSwapIn();

    abstract public void onSwapOut();

    public AbstractQuantumCard getRandomLinked() {
        return this.linkedCards.get(RngController.RandInRange(this.linkedCards.size()));
    }

    public AbstractQuantumCard getRandomLinked(boolean includeSelf) {
        if (includeSelf) {
            int range = this.linkedCards.size();
            int res = RngController.RandInRange(range);

            if (res == range) { return this; }
            return this.linkedCards.get(res);
        }

        return this.getRandomLinked();
    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();

        if (!this.linkedCards.isEmpty()) {
            AbstractQuantumCard newCard = this.getRandomLinked(true);

            if (!(newCard == this)) {
                addToBot(new SwapCardsAction(this, newCard, false));
            }
        }
    }

    @Override
    public void update() {
        super.update();

        AbstractPlayer player = AbstractDungeon.player;
        String superposition  = SuperPositionPower.POWER_ID;

        if (player != null && player.hasPower(superposition)) {

            if ( ((SuperPositionPower)player.getPower(superposition)).isActive() ) {
                if (!this.isGlowing) { this.beginGlowing();  }
            } else {
                if (this.isGlowing) { this.stopGlowing();    }
            }

        }
    }

    public String getAllDescriptions() {
        String combined = this.rawDescription;

        if (this.linkedCards != null) {
            for (AbstractQuantumCard qc : this.linkedCards) {
                combined += "NL AND* NL" + qc.rawDescription;
            }
        }

        return combined;
    }
}
