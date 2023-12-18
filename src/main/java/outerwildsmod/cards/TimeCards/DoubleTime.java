package outerwildsmod.cards.TimeCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import outerwildsmod.cards.BaseCard;
import outerwildsmod.character.Hearthian;
import outerwildsmod.powers.DoubleTimePower;
import outerwildsmod.util.CardInfo;

import static outerwildsmod.outerwildsmod.makeID;

public class DoubleTime extends BaseCard {

    private static final CardInfo cardInfo = new CardInfo(
            "DoubleTime",
            2,
            CardType.POWER,
            CardTarget.NONE,
            CardRarity.UNCOMMON,
            Hearthian.Enums.CARD_COLOR
    );

    public final static String ID = makeID(cardInfo.baseId);

    public DoubleTime() {
        super(cardInfo);
        this.baseMagicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DoubleTimePower(p, 1)));
    }
}
