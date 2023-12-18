package outerwildsmod.cards.TimeCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import outerwildsmod.cards.AbstractCards.AbstractTimeCard;
import outerwildsmod.character.Hearthian;

import static outerwildsmod.outerwildsmod.makeID;
import static outerwildsmod.util.TextureLoader.getCardTextureString;

public class Gyroscope extends AbstractTimeCard {

    public static final String ID = makeID("Gyroscope");
    public static final String IMG = getCardTextureString(ID, CardType.ATTACK);

    private static final AbstractCard.CardRarity RARITY = CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ALL_ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    public static final AbstractCard.CardColor COLOR = Hearthian.Enums.CARD_COLOR;

    private static final int COST = 0;

    private static final int BASE_DMG = 0;

    public Gyroscope() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = this.damage = BASE_DMG;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
//            this.upgradeName();
            this.exhaust = false;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractGameAction.AttackEffect effect = AbstractGameAction.AttackEffect.BLUNT_LIGHT;

        if (damage >= 20 && damage < 35) {
            effect = AbstractGameAction.AttackEffect.BLUNT_HEAVY;
        } else if (damage >= 35) {
            effect = AbstractGameAction.AttackEffect.SMASH;
        }

        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), effect));
    }

    @Override
    public void update() {
        super.update();
        this.damage = this.baseDamage + this.calculateTime();
    }
}
