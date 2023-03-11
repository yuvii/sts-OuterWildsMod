package outerwildsmod.cards.quantumOptions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.PummelDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import outerwildsmod.cards.basic.HStrike;
import outerwildsmod.util.CardInfo;

import static outerwildsmod.outerwildsmod.makeID;
import static outerwildsmod.util.TextureLoader.getCardTextureString;

public class QuantumStrikeMultiDamageOption extends AbstractCard {

    public static final String ID = "QuantumStrikeMultiDamageOption";
    private static final String NAME = "";
    private static final String DESCRIPTION = "Deal !D! hits !M! Times";
//    private static final String IMG_PATH = "red/attack/quantum_strike.png";
    private static final int COST = -2;
    private static final int DAMAGE = 3;
    private static final int HITS = 3;

    public QuantumStrikeMultiDamageOption() {
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
        this.baseMagicNumber = HITS;
        this.magicNumber = this.baseMagicNumber;

        this.rawDescription = DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption(p, m);
    }

    public void onChoseThisOption(AbstractPlayer p, AbstractMonster m) {
        for(int i = 1; i < this.magicNumber+1; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }

    @Override
    public AbstractCard makeCopy() { return new QuantumStrikeMultiDamageOption(); }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.initializeDescription();
        }
    }
}