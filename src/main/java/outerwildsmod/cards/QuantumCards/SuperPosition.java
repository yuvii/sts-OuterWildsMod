package outerwildsmod.cards.QuantumCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import outerwildsmod.actions.BlinkAction;
import outerwildsmod.cards.BaseCard;
import outerwildsmod.character.Hearthian;
import outerwildsmod.powers.InfiniteBlinksPower;
import outerwildsmod.powers.SuperPositionPower;
import outerwildsmod.util.CardInfo;

import static outerwildsmod.outerwildsmod.makeID;

public class SuperPosition extends BaseCard {

    private static final int BASE_COST = 3;
    private static final int UPG_COST = 2;

    private static final CardInfo cardInfo = new CardInfo(
            "Superposition",
            BASE_COST,
            CardType.POWER,
            CardTarget.NONE,
            CardRarity.RARE,
            Hearthian.Enums.CARD_COLOR
    );

    public final static String ID = makeID(cardInfo.baseId);

    public SuperPosition() {
        super(cardInfo);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.setCostUpgrade(UPG_COST);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SuperPositionPower(p, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { return new SuperPosition(); }
}
