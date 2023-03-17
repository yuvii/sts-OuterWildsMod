package outerwildsmod.cards.common.attacks;
////
////import basemod.abstracts.CustomCard;
////import basemod.helpers.ModalChoice;
////import basemod.helpers.ModalChoiceBuilder;
////import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
////import com.megacrit.cardcrawl.cards.AbstractCard;
////import com.megacrit.cardcrawl.characters.AbstractPlayer;
////import com.megacrit.cardcrawl.monsters.AbstractMonster;
////import outerwildsmod.cards.BaseCard;
//////import outerwildsmod.cards.quantumOptions.QuantumStrikeMultiDamageOption;
//////import outerwildsmod.cards.quantumOptions.QuantumStrikeSingleDamageOption;
////import basemod.abstracts.CustomCard;
////import com.megacrit.cardcrawl.actions.AbstractGameAction;
////import com.megacrit.cardcrawl.actions.common.DamageAction;
////import com.megacrit.cardcrawl.cards.AbstractCard;
////import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
////import com.megacrit.cardcrawl.cards.DamageInfo;
////import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
////import outerwildsmod.cards.quantumOptions.QuantumStrikeMultiDamageOption;
////import outerwildsmod.cards.quantumOptions.QuantumStrikeSingleDamageOption;
////import outerwildsmod.character.Hearthian;
//////import outerwildsmod.util.CardInfo;
////
////import java.util.ArrayList;
////import java.util.Iterator;
//
////import static com.megacrit.cardcrawl.cards.AbstractCard.*;
////
////public class QuantumStrike extends BaseCard {
////
////
////
////    private static final CardInfo cardInfo = new CardInfo(
////            "QuantumStrike",
////            1,
////            CardType.ATTACK,
////            CardTarget.NONE,
////            CardRarity.COMMON,
////            Hearthian.Enums.CARD_COLOR
////    );
////
////
////    public final static String ID = makeID(cardInfo.baseId);
////
////    private boolean hasChosenAction = false;
////
////    private ArrayList<AbstractCard> choices = new ArrayList();
////
////    private String description = "";
////
//////
////    public QuantumStrike() {
////        super(cardInfo);
////
////        choices.add(new QuantumStrikeSingleDamageOption());
////        choices.add(new QuantumStrikeMultiDamageOption());
////
////        Iterator var4 = choices.iterator();
////        while (var4.hasNext()) {
////            AbstractCard c = (AbstractCard) var4.next();
////
////            if (this.upgraded) {
////                c.upgrade();
////            }
////            c.initializeDescription();
////            this.description += c.rawDescription + " NL ||";
////        }
////
////        this.rawDescription = this.description.substring(0, this.description.length() - 2);
////        this.initializeDescription();
////    }
////
////    @Override
////    public void use(AbstractPlayer player, AbstractMonster monster) {
////
//////        ArrayList<AbstractCard> choices = new ArrayList<>();
//////        choices.add(new SingleDamageCard(CHOICE_1_DAMAGE));
//////        choices.add(new MultiDamageCard(3));
//////        AbstractDungeon.gridSelectScreen.open(choices, 1, "Choose one:", false);
//////        // Wait for the player to make a choice
//////        AbstractDungeon.actionManager.addToBottom(new WaitAction(0.25f));
//////        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
//////            AbstractCard selected = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
//////            AbstractDungeon.gridSelectScreen.selectedCards.clear();
//////            // Use the selected card
//////            selected.use(player, monster);
//////        }
////
////        this.addToBot(new ChooseOneAction(choices));
////    }
////
////    @Override
////    public AbstractCard makeCopy() {
////        return new QuantumStrike();
////    }
////
//////    private class SingleDamageCard extends BaseCard {
//////        private final int damage;
//////
//////        public SingleDamageCard(int damage) {
//////            super(ID, NAME, IMG_PATH, COST, "Deal " + damage + " damage.", CardType.ATTACK, CardColor.RED, CardRarity.UNCOMMON, CardTarget.ENEMY);
//////            this.damage = damage;
//////            this.baseDamage = damage;
//////        }
//////
//////        @Override
//////        public void use(AbstractPlayer player, AbstractMonster monster) {
//////            AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(player, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
//////        }
//////
//////        @Override
//////        public AbstractCard makeCopy() {
//////            return new SingleDamageCard(damage);
//////        }
//////    }
//////
//////    private class MultiDamageCard extends BaseCard {
//////        private final int hits;
//////
//////        public MultiDamageCard(int hits) {
//////            super(ID, NAME, IMG_PATH, COST, "Deal " + hits + " hits of 3 damage each.", CardType.ATTACK, CardColor.RED, CardRarity.UNCOMMON, CardTarget.ENEMY);
//////            this.hits = hits;
//////            this.baseDamage = 3;
//////            this.isMultiDamage = true;
//////        }
//////
//////        @Override
//////        public void use(AbstractPlayer player, AbstractMonster monster) {
//////            AbstractDungeon.actionManager.addToBottom(new VFXAction(player, new MindblastEffect(player.dialogX, player.dialogY, player.flipHorizontal), 0.1F));
//////            for (int i = 0; i < hits; i++) {
//////                AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(player, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
//////            }
//////            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
//////                AbstractDungeon.actionManager.clearPostCombatActions();
//////            }
//////        }
//////
//////        @Override
//////        public AbstractCard makeCopy() {
//////            return new MultiDamageCard(hits);
//////        }
//////    }
////}
//
//// VERSION 2
//import basemod.BaseMod;
//import basemod.abstracts.CustomCard;
//import basemod.helpers.ModalChoice;
//import basemod.helpers.ModalChoiceBuilder;
////import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
////import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
////import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.cards.DamageInfo;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.localization.CardStrings;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import outerwildsmod.cards.BaseCard;
//import outerwildsmod.cards.quantumOptions.QuantumStrikeMultiDamageOption;
//import outerwildsmod.cards.quantumOptions.QuantumStrikeSingleDamageOption;
//import outerwildsmod.character.Hearthian;
//import outerwildsmod.util.CardInfo;
//
//import static outerwildsmod.outerwildsmod.makeID;
//
//
//public class QuantumStrike extends BaseCard {
//
//    private static final CardInfo cardInfo = new CardInfo(
//            "QuantumStrike",
//            1,
//            CardType.ATTACK,
//            CardTarget.ENEMY,
//            CardRarity.BASIC,
//            Hearthian.Enums.CARD_COLOR
//    );
//
//    public final static String ID = makeID(cardInfo.baseId);
//
//    private static final int DAMAGE = 9;
//    private static final int UPG_DAMAGE = 12;
//
//
//
//    public QuantumStrike() {
//        super(cardInfo);
//        setDamage(DAMAGE, UPG_DAMAGE);
//    }
//
//
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
//    }
//
//    @Override
//    public AbstractCard makeCopy() { return new QuantumStrike(); }
//}
//
//
////public class QuantumStrike extends CustomCard implements ModalChoice.Callback {
////
////    protected CardStrings cardStrings;
////
////    public static final String ID = "QuantumStrike";
////    public static final String NAME = "Quantum Strike";
////    public static final String DESCRIPTION = "Deal 9 damage NL ${modID}:OR NL deal 3 damage 3 Times";
////    private static final int COST = 1;
////
////    private static final int HITS = 3;
////
////
////    private ModalChoice modal;
////
////    public QuantumStrike()
////    {
////        super(ID,
////                NAME,
////                (String) null,
////                COST,
////                DESCRIPTION,
////                CardType.ATTACK,
////                Hearthian.Enums.CARD_COLOR,
////                CardRarity.COMMON,
////                CardTarget.NONE);
////
////        loadStrings();
////
////        initializeTitle();
////        initializeDescription();
////
////        this.baseMagicNumber = this.magicNumber = HITS;
////
////        modal = new ModalChoiceBuilder()
////                .setCallback(this) // Sets callback of all the below options to this
////                .addOption(new QuantumStrikeSingleDamageOption())
////                .addOption(new QuantumStrikeMultiDamageOption())
////                .create();
////    }
////
////    @Override
////    public void upgrade()
////    {
////        if (!upgraded) {
////            upgradeName();
////        }
////    }
////    @Override
////    public void use(AbstractPlayer p, AbstractMonster m)
////    {
////        modal.open();
////    }
////
////    private void loadStrings() {
////        cardStrings = CardCrawlGame.languagePack.getCardStrings(cardID);
////
////        this.rawDescription = cardStrings.DESCRIPTION;
////        this.originalName = cardStrings.NAME;
////        this.name = originalName;
////    }
//////    public List<TooltipInfo> getCustomTooltips()
//////    {
////////        return modal.generateTooltips();
//////    }
////
////
////    @Override
////    public void optionSelected(AbstractPlayer p, AbstractMonster m, int i) {
////
//////        switch (i) {
//////            case 0:
//////                this.baseDamage = DAMAGE;
//////                AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
//////                break;
//////
//////            case 1:
//////                this.baseDamage = MULTIDAMAGE;
//////                for(int j = 1; j < this.magicNumber; ++j) {
//////                    AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
//////                }
//////                break;
//////        }
////    }
////
////    @Override
////    public AbstractCard makeCopy()
////    {
////        return new QuantumStrike();
////    }
////
////}
//
//// Version 3
////
////import com.megacrit.cardcrawl.cards.AbstractCard;
////import com.megacrit.cardcrawl.characters.AbstractPlayer;
////import com.megacrit.cardcrawl.monsters.AbstractMonster;
////import outerwildsmod.cards.BaseCard;
////import outerwildsmod.cards.basic.HStrike;
////import outerwildsmod.cards.quantumOptions.EasyModalChoiceAction;
////import outerwildsmod.cards.quantumOptions.QuantumStrikeMultiDamageOption;
////import outerwildsmod.cards.quantumOptions.QuantumStrikeSingleDamageOption;
////import outerwildsmod.character.Hearthian;
////import outerwildsmod.util.CardInfo;
////
////import java.util.ArrayList;
////
////import static outerwildsmod.outerwildsmod.makeID;
////
////public class QuantumStrike extends BaseCard {
////
////    private static final CardInfo cardInfo = new CardInfo(
////            "QuantumStrike",
////            1,
////            CardType.ATTACK,
////            CardTarget.NONE,
////            CardRarity.COMMON,
////            Hearthian.Enums.CARD_COLOR
////    );
////
////
////    public final static String ID = makeID(cardInfo.baseId);
////
////    public QuantumStrike() {
////        super(cardInfo);
////    }
////
////    @Override
////    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
////        ArrayList<AbstractCard> list = new ArrayList<>();
////        list.add(new QuantumStrikeSingleDamageOption());
////        list.add(new QuantumStrikeMultiDamageOption());
////        addToBot(new EasyModalChoiceAction(list));
////    }
////
////    @Override
////    public AbstractCard makeCopy() { return new QuantumStrike(); }
////
////}




import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import outerwildsmod.cards.abstractCards.AbstractQuantumCard;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import outerwildsmod.character.Hearthian;

import java.util.ArrayList;
import java.util.List;

import static outerwildsmod.outerwildsmod.makeID;
import static outerwildsmod.util.TextureLoader.getCardTextureString;

public class QuantumStrikeSingle extends AbstractQuantumCard {


    public static final String ID = makeID("QuantumStrikeSingle");
    public static final String IMG = getCardTextureString(ID, CardType.ATTACK);


    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Hearthian.Enums.CARD_COLOR;

    private static final int COST = 1;

    protected static ArrayList<TooltipInfo> toolTips;

    // /STAT DECLARATION/

    public QuantumStrikeSingle() {
        this(null);
    }

    public QuantumStrikeSingle(AbstractQuantumCard linkedCard) {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        this.baseDamage = 9;
        if (linkedCard == null) {
            setLinkedCard(new QuantumStrikeMulti(this));
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
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
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