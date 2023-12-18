package outerwildsmod.cards.AbstractCards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import outerwildsmod.powers.DoubleTimePower;
import outerwildsmod.util.CardInfo;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static java.lang.Math.abs;
import static outerwildsmod.util.TextureLoader.getCardTextureString;

public abstract class AbstractTimeCard extends CustomCard {

    public int y = 0;
    public int z = 0;

    public AbstractTimeCard(String id,
                               String img,
                               int cost,
                               CardType type,
                               CardColor color,
                               CardRarity rarity,
                               CardTarget target) {
        super(id, languagePack.getCardStrings(id).NAME, img, cost, languagePack.getCardStrings(id).DESCRIPTION, type, color, rarity, target);
    }
    public AbstractTimeCard(CardInfo cardInfo) {
        this(cardInfo.baseId, getCardTextureString(cardInfo.baseId, cardInfo.cardType), cardInfo.baseCost, cardInfo.cardType, cardInfo.cardColor, cardInfo.cardRarity, cardInfo.cardTarget);
    }

    public int calculateTime() {
        int cardsPlayedThisCombat;

        AbstractPower doubleTime = AbstractDungeon.player.getPower(DoubleTimePower.POWER_ID);
        if (doubleTime != null) {            cardsPlayedThisCombat = ((DoubleTimePower)doubleTime).cardsPlayed; }
        else                    {            cardsPlayedThisCombat = AbstractDungeon.actionManager.cardsPlayedThisCombat.size(); }

        return cardsPlayedThisCombat;
    }

    public int calculateShiftingTime(int baseValue, int modifier, int max) {
        int cardsPlayedThisCombat = this.calculateTime();
        return max - abs( max - ( cardsPlayedThisCombat % (max * 2)));
    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        this.y += 1;
    }

    @Override
    public void triggerAtStartOfTurn() {
        super.triggerAtStartOfTurn();
        this.z += 1;
    }
}
