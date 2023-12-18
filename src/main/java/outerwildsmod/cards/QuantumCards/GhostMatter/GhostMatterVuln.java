package outerwildsmod.cards.QuantumCards.GhostMatter;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import outerwildsmod.cards.AbstractCards.AbstractQuantumCard;
import outerwildsmod.character.Hearthian;

import java.util.ArrayList;

import static outerwildsmod.outerwildsmod.makeID;
import static outerwildsmod.util.TextureLoader.getCardTextureString;

public class GhostMatterVuln extends AbstractQuantumCard {


    public static final String ID = makeID("GhostMatterVuln");
    public static final String IMG = getCardTextureString(ID, CardType.SKILL);

    private static final AbstractCard.CardRarity RARITY = CardRarity.BASIC;
    private static final AbstractCard.CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    public static final AbstractCard.CardColor COLOR = Hearthian.Enums.CARD_COLOR;

    private static final int COST = 0;

    private static final int MAGIC = 1;

    private static final int MAGIC_UPG = 2;

    protected static ArrayList<TooltipInfo> toolTips;

    public GhostMatterVuln() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        this.baseMagicNumber = MAGIC;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;

        this.isMainCard = true;

        linkCard(new GhostMatterWeak());
    }

    public void useCard(AbstractPlayer p, AbstractMonster m) {

        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            this.addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }

    }

    @Override
    public void onSwapIn() {

    }

    @Override
    public void onSwapOut() {

    }
}
