package outerwildsmod.cards.QuantumCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import outerwildsmod.cards.BaseCard;
import outerwildsmod.character.Hearthian;
import outerwildsmod.powers.DoubleTimePower;
import outerwildsmod.powers.InfiniteBlinksPower;
import outerwildsmod.util.CardInfo;

import static outerwildsmod.outerwildsmod.makeID;

public class InfiniteBlinks extends BaseCard {

    private static final CardInfo cardInfo = new CardInfo(
            "InfiniteBlinks",
            1,
            CardType.POWER,
            CardTarget.NONE,
            CardRarity.UNCOMMON,
            Hearthian.Enums.CARD_COLOR
    );

    public final static String ID = makeID(cardInfo.baseId);

    public InfiniteBlinks() {
        super(cardInfo);
        this.baseMagicNumber = this.magicNumber = 1;
        this.setInnate(false, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new InfiniteBlinksPower(p, this.magicNumber)));
    }

}
