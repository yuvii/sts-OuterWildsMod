package outerwildsmod.cards.quantumOptions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import outerwildsmod.cards.basic.HStrike;
import outerwildsmod.util.CardInfo;

import static outerwildsmod.outerwildsmod.makeID;
import static outerwildsmod.util.TextureLoader.getCardTextureString;

public class QuantumStrikeSingleDamageOption extends AbstractCard {

    public static final String ID = "QuantumStrikeSingleDamageOption";
    private static final String NAME = "";

    private static final String DESCRIPTION = "Deal !D! damage";
    private final static int COST = -2;
    private static final int DAMAGE = 9;
    private static final int UPG_DAMAGE = 3;

    public QuantumStrikeSingleDamageOption() {
        super(makeID(ID),
                NAME,
                "",
                COST,
                getCardTextureString(ID, CardType.ATTACK),
                CardType.ATTACK,
                CardColor.COLORLESS,
                CardRarity.SPECIAL,
                CardTarget.ENEMY);

        this.baseDamage = DAMAGE;

        this.rawDescription = DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void upgrade() {
        this.upgradeDamage(UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption(p, m);
    }

    public void onChoseThisOption(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

    @Override
    public AbstractCard makeCopy() { return new QuantumStrikeSingleDamageOption(); }

}

