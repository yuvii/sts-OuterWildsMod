package outerwildsmod.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import outerwildsmod.cards.BaseCard;

//public class HourglassTwins extends BaseCard {
//
//    private static final CardInfo cardInfo = new CardInfo(
//            "HourglassTwins",
//            1,
//            CardType.ATTACK,
//            CardTarget.ENEMY,
//            CardRarity.BASIC,
//            Hearthian.Enums.CARD_COLOR
//    );
//
//    public final static String ID = makeID(cardInfo.baseId);
//
//    private static int DAMAGE = 10;
//
//    private static int UPG_DAMAGE = 15;
//
//    private static int BLOCK = 10;
//
//    private static int UPG_BLOCK = 15;
//
//    private static int POLARITY = 10;
//
//    public HourglassTwins() {
//        super(cardInfo);
//
//        setDamage(DAMAGE, UPG_DAMAGE);
//        setBlock(BLOCK, UPG_BLOCK);
//    }
//
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        int cardsPlayedThisCombat = AbstractDungeon.actionManager.cardsPlayedThisCombat.size();
//        int damage = this.baseDamage;
//        int block = this.baseBlock;
//
//        if (cardsPlayedThisCombat > MAX_CARDS) {
//            damage = -1 * damage;
//            block = -1 * block;
//        } else {
//            int delta = cardsPlayedThisCombat - 1;
//            damage += delta;
//            block -= delta;
//        }
//        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn)));
//        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
//
//
//
//        int currentStepCounter = getStepCounter();
//        int damageChange = currentStepCounter % (2 * STEPS_PER_LOOP) < STEPS_PER_LOOP ? DAMAGE_CHANGE_PER_STEP : -DAMAGE_CHANGE_PER_STEP;
//        int blockChange = currentStepCounter % (2 * STEPS_PER_LOOP) < STEPS_PER_LOOP ? BLOCK_CHANGE_PER_STEP : -BLOCK_CHANGE_PER_STEP;
//        int newDamageAmount = Math.max(damageAmount + damageChange, 0);
//        int newBlockAmount = Math.max(blockAmount + blockChange, 0);
//        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, newDamageAmount, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
//        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, newBlockAmount));
//        setStepCounter(currentStepCounter + 1);
//        this.baseDamage = newDamageAmount;
//        this.baseBlock = newBlockAmount;
//        this.initializeDescription();
//    }
//
//
//    @Override
//    public AbstractCard makeCopy() { return new HourglassTwins(); }
//}


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import outerwildsmod.character.Hearthian;
import outerwildsmod.outerwildsmod;
import outerwildsmod.util.CardInfo;

import static outerwildsmod.outerwildsmod.makeID;


public class HourglassTwins extends BaseCard {

    private static final int BASE_DAMAGE = 10;
    private static final int BASE_BLOCK = 0;
    private int damageModifier = -1;
    private int blockModifier = 1;
    private boolean reversed = false;

    private int MAX = 10;

//    public static final Logger logger = LogManager.getLogger(outerwildsmod.modID);

    private static final CardInfo cardInfo = new CardInfo(
            "HourglassTwins",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.BASIC,
            Hearthian.Enums.CARD_COLOR
    );

    public final static String ID = makeID(cardInfo.baseId);

    public HourglassTwins() {
        super(cardInfo);

        this.baseDamage = BASE_DAMAGE;
        this.baseBlock = BASE_BLOCK;
    }


    public void triggerWhenDrawn() {
        outerwildsmod.logger.info("drawn trigger!");
        this.setStats();
    }


    public void triggerOnOtherCardPlayed(AbstractCard c) {
        outerwildsmod.logger.info("other card triggered!");
        this.setStats();
    }

    private void setStats() {

        int damage = this.calculateTime(this.baseDamage, this.damageModifier);
        int block  = this.calculateTime(this.baseBlock, this.blockModifier);

        int cardsPlayed = AbstractDungeon.actionManager.cardsPlayedThisCombat.size();

        outerwildsmod.logger.info("---------");
        outerwildsmod.logger.info(String.format("cards played: %d", cardsPlayed));
        outerwildsmod.logger.info(String.format("damage is: %d", damage));
        outerwildsmod.logger.info(String.format("block is: %d", block));
        outerwildsmod.logger.info("---------");


        this.baseDamage = damage;
        this.baseBlock = block;
    }

    private int calculateTime(int baseValue, int modifier) {
        int cardsPlayedThisCombat = AbstractDungeon.actionManager.cardsPlayedThisCombat.size();
        int modo = cardsPlayedThisCombat % MAX;
        int div  = (cardsPlayedThisCombat - modo) / MAX;

        if (div % 2 == 0) {
            return baseValue + (modo * modifier);
        } else {
            return (baseValue + MAX) - (modo * modifier);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

//        outerwildsmod.logger.info("balabam bam!");
        // Deal damage to the target
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));

        // Gain block
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));

    }

    @Override
    public AbstractCard makeCopy() {
        return new HourglassTwins();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }
}

