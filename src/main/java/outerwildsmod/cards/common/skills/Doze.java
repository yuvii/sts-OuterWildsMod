package outerwildsmod.cards.common.skills;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import outerwildsmod.cards.BaseCard;
import outerwildsmod.cards.basic.HDefend;
import outerwildsmod.character.Hearthian;
import outerwildsmod.util.CardInfo;

import static outerwildsmod.outerwildsmod.makeID;

public class Doze extends BaseCard {


    private static final CardInfo cardInfo = new CardInfo(
            "Doze",
            0,
            CardType.SKILL,
            CardTarget.NONE,
            CardRarity.BASIC,
            CardColor.COLORLESS
    );

    public final static String ID = makeID(cardInfo.baseId);

    public Doze() {
        super(cardInfo);
        this.exhaust = true;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { return new Doze(); }
}
