package outerwildsmod.cards.common.skills;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import outerwildsmod.cards.BaseCard;
import outerwildsmod.cards.basic.HDefend;
import outerwildsmod.character.Hearthian;
import outerwildsmod.util.CardInfo;

import static outerwildsmod.outerwildsmod.makeID;

public class DozeOff extends BaseCard {

    private static final CardInfo cardInfo = new CardInfo(
            "DozeOff",
            1,
            CardType.SKILL,
            CardTarget.NONE,
            CardRarity.BASIC,
            Hearthian.Enums.CARD_COLOR
    );

    public final static String ID = makeID(cardInfo.baseId);

    public DozeOff() {
        super(cardInfo);

        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new Blink();

    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MakeTempCardInHandAction(new Blink(), this.magicNumber));
    }
    @Override
    public AbstractCard makeCopy() { return new DozeOff(); }

}
