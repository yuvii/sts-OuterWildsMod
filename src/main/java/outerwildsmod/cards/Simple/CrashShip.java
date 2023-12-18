package outerwildsmod.cards.Simple;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import outerwildsmod.cards.BaseCard;
import outerwildsmod.character.Hearthian;
import outerwildsmod.util.CardInfo;

import static outerwildsmod.outerwildsmod.makeID;

public class CrashShip extends BaseCard {

    private static final CardInfo cardInfo = new CardInfo(
            "CrashShip",
            1,
            CardType.SKILL,
            CardTarget.SELF_AND_ENEMY,
            CardRarity.BASIC,
            Hearthian.Enums.CARD_COLOR
    );

    public final static String ID = makeID(cardInfo.baseId);

    private final static int DAMAGE = 12;

    private final static int UPG_DAMAGE = 17;

    public CrashShip() {
        super(cardInfo);
        setDamage(DAMAGE, UPG_DAMAGE);
        this.magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));
        addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        addToBot(new ApplyPowerAction(p, p, new VulnerablePower(p, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));

    }

    @Override
    public AbstractCard makeCopy() { return new CrashShip(); }


}
