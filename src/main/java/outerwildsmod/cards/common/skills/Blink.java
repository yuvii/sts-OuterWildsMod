package outerwildsmod.cards.common.skills;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import outerwildsmod.actions.BlinkAction;
import outerwildsmod.cards.BaseCard;
import outerwildsmod.util.CardInfo;

import static outerwildsmod.outerwildsmod.makeID;

public class Blink extends BaseCard {

    private static final CardInfo cardInfo = new CardInfo(
            "Blink",
            0,
            CardType.SKILL,
            CardTarget.NONE,
            CardRarity.BASIC,
            CardColor.COLORLESS
    );

    public final static String ID = makeID(cardInfo.baseId);

    public Blink() {
        super(cardInfo);
        this.exhaust = true;
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        if (!this.upgraded) {
            this.upgrade();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, this.magicNumber));
        addToBot(new BlinkAction());
    }
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public AbstractCard makeCopy() { return new Blink(); }
}
