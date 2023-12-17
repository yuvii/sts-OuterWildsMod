package outerwildsmod.cards.QuantumCards.QuantumStrike;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import outerwildsmod.cards.AbstractCards.AbstractQuantumCard;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import outerwildsmod.character.Hearthian;

import java.util.ArrayList;
import java.util.List;

import static outerwildsmod.outerwildsmod.makeID;
import static outerwildsmod.util.TextureLoader.getCardTextureString;

public class QuantumStrikeDouble extends AbstractQuantumCard {


    public static final String ID = makeID("QuantumStrikeDouble");
    public static final String IMG = getCardTextureString(ID, CardType.ATTACK);


    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Hearthian.Enums.CARD_COLOR;

    private static final int COST = 1;

    private static final int BASE_DAMAGE = 5;

    private static final int HITS = 2;
    private static final int UPG_DAMAGE = 7;

    protected static ArrayList<TooltipInfo> toolTips;

    public QuantumStrikeDouble() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        this.baseDamage = BASE_DAMAGE;
        this.baseMagicNumber = this.magicNumber = HITS;
        this.isMainCard = true;

        linkCard(new QuantumStrikeMulti());

    }

//    @Override
//    public List<TooltipInfo> getCustomTooltips() {
//        if (toolTips == null) {
//            toolTips = new ArrayList<>();
//            toolTips.add(new TooltipInfo(TipHelper.capitalize(GameDictionary.WEAK.NAMES[0]), GameDictionary.keywords.get(GameDictionary.WEAK.NAMES[0])));
//        }
//        return toolTips;
//    }

    public void useCard(AbstractPlayer p, AbstractMonster m) {
        for(int i = 1; i < this.magicNumber+1; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
    }

    @Override
    public void onSwapIn() {

    }

    @Override
    public void onSwapOut() {

    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            this.baseDamage = UPG_DAMAGE;
            upgradeName();
            initializeDescription();
            super.upgrade();
        }
    }
}