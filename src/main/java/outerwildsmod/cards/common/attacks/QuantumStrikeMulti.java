package outerwildsmod.cards.common.attacks;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import outerwildsmod.cards.abstractCards.AbstractQuantumCard;
import outerwildsmod.character.Hearthian;

import java.util.ArrayList;
import java.util.List;

import static outerwildsmod.outerwildsmod.makeID;
import static outerwildsmod.util.TextureLoader.getCardTextureString;

public class QuantumStrikeMulti extends AbstractQuantumCard {

    public static final String ID = makeID("QuantumStrikeMulti");
    public static final String IMG = getCardTextureString(ID, CardType.ATTACK);


    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    public static final AbstractCard.CardColor COLOR = Hearthian.Enums.CARD_COLOR;

    private static final int COST = 1;

    private static final int HITS = 3;

    protected static ArrayList<TooltipInfo> toolTips;

    // /STAT DECLARATION/

    public QuantumStrikeMulti() {
        this(null);
    }

    public QuantumStrikeMulti(AbstractQuantumCard linkedCard) {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        this.baseDamage = 3;
        this.baseMagicNumber = HITS;
        this.magicNumber = this.baseMagicNumber;

        if (linkedCard == null) {
            setLinkedCard(new QuantumStrikeSingle(this));
        } else {
            setLinkedCard(linkedCard);
        }
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        if (toolTips == null) {
            toolTips = new ArrayList<>();
            toolTips.add(new TooltipInfo(TipHelper.capitalize(GameDictionary.WEAK.NAMES[0]), GameDictionary.keywords.get(GameDictionary.WEAK.NAMES[0])));
        }
        return toolTips;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 1; i < this.magicNumber+1; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
            super.upgrade();
        }
    }

}
