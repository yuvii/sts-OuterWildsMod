package outerwildsmod.cards.Simple;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import outerwildsmod.cards.BaseCard;
import outerwildsmod.character.Hearthian;
import outerwildsmod.util.CardInfo;

import static outerwildsmod.outerwildsmod.makeID;

public class Defend extends BaseCard {

    private static final CardInfo cardInfo = new CardInfo(
            "Defend",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.BASIC,
            Hearthian.Enums.CARD_COLOR
    );

    public final static String ID = makeID(cardInfo.baseId);

    private final static int BLOCK = 5;

    private final static int UPG_BLOCK = 8;

    public Defend() {
        super(cardInfo);
        setBlock(BLOCK, UPG_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
    }

    @Override
    public AbstractCard makeCopy() { return new Defend(); }
}
